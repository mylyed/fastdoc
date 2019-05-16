package io.github.mylyed.lessdoc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mylyed.lessdoc.model.DocumentEvent;
import io.github.mylyed.lessdoc.persist.entity.DocumentExample;
import io.github.mylyed.lessdoc.persist.mapper.DocumentMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 搜索服务
 *
 * @author lilei
 * created at 2019/5/16
 */
@Slf4j
@Service
public class SearchService {


    @EventListener
    @Async
    public void handEvent(DocumentEvent documentEvent) {
        log.debug("收到事件：{}", documentEvent);
        io.github.mylyed.lessdoc.persist.entity.Document document = documentEvent.getDocument();
        switch (documentEvent.getEventType()) {
            case CREATE:
                create(document);
                break;
            case UPDATE:
                delete(document);
                create(document);
                break;
            case DELETE:
                delete(document);
                break;
        }
    }

    private IndexWriter getIndexWriter() throws IOException {
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        //创建
        iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        IndexWriter writer = new IndexWriter(directory, iwc);
        return writer;
    }

    public void create(io.github.mylyed.lessdoc.persist.entity.Document document) {
        try (IndexWriter writer = getIndexWriter()) {
            writer.addDocument(buildLuceneDocument(document));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(io.github.mylyed.lessdoc.persist.entity.Document document) {
        try (IndexWriter writer = getIndexWriter()) {
            writer.deleteDocuments(IntPoint.newExactQuery("documentId", document.getDocumentId()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Autowired
    Analyzer analyzer;

    @Autowired
    Directory directory;

    @Resource
    DocumentMapper documentMapper;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * 初始化 lucene 的索引
     */
    public void initSearch(boolean update) throws IOException {


        List<io.github.mylyed.lessdoc.persist.entity.Document> documents = documentMapper.selectByExampleWithBLOBs(new DocumentExample());

        try (IndexWriter writer = getIndexWriter()) {
            for (io.github.mylyed.lessdoc.persist.entity.Document document : documents) {
                Document doc = buildLuceneDocument(document);
                if (update) {
                    //数值类型 不能删除
//                    writer.updateDocument(new Term("documentId", document.getDocumentId().toString()), doc);
                    writer.deleteDocuments(IntPoint.newExactQuery("documentId", document.getDocumentId()));
                }
                writer.addDocument(doc);
            }
        }
    }


    private Document buildLuceneDocument(io.github.mylyed.lessdoc.persist.entity.Document document) {
        Document doc = new Document();
        doc.add(new IntPoint("documentId", document.getDocumentId()));
        doc.add(new IntPoint("bookId", document.getBookId()));
        doc.add(new StringField("documentNameStr", document.getDocumentName(), Field.Store.YES));
        doc.add(new StringField("releaseStr", document.getRelease(), Field.Store.YES));

        doc.add(new TextField("documentName", document.getDocumentName(), Field.Store.YES));
        doc.add(new TextField("release", document.getRelease(), Field.Store.YES));

        document.setContent(null);
        document.setRelease(null);
        document.setMarkdown(null);
        try {
            doc.add(new StringField("json", objectMapper.writeValueAsString(document), Field.Store.YES));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return doc;
    }

    /**
     * 搜索
     *
     * @param key    关键字
     * @param bookId bookId
     * @return
     */
    public List<io.github.mylyed.lessdoc.persist.entity.Document> search(String key, Integer bookId) {
        try (IndexReader reader = DirectoryReader.open(directory);) {
            List<io.github.mylyed.lessdoc.persist.entity.Document> list = new ArrayList<>();
            IndexSearcher searcher = new IndexSearcher(reader);
            String ketWord = "*" + key + "*";

            Query query1 = new WildcardQuery(new Term("releaseStr", ketWord));

            Query query2 = new WildcardQuery(new Term("documentNameStr", ketWord));


            Query query3 = IntPoint.newExactQuery("bookId", bookId);

            BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();

            booleanQuery.add(query1, BooleanClause.Occur.SHOULD);
            booleanQuery.add(query2, BooleanClause.Occur.SHOULD);


            BooleanQuery.Builder booleanQuery2 = new BooleanQuery.Builder();
            booleanQuery2.add(query3, BooleanClause.Occur.MUST);
            booleanQuery2.add(booleanQuery.build(), BooleanClause.Occur.MUST);

            TopDocs docs = searcher.search(booleanQuery2.build(), Integer.MAX_VALUE);

            for (ScoreDoc scoreDoc : docs.scoreDocs) {
                Document d = searcher.doc(scoreDoc.doc);
                String json = d.getField("json").stringValue();
                io.github.mylyed.lessdoc.persist.entity.Document document = objectMapper.readValue(json, io.github.mylyed.lessdoc.persist.entity.Document.class);
                list.add(document);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
