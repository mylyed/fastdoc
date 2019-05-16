package io.github.mylyed.lessdoc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mylyed.lessdoc.model.DocumentEvent;
import io.github.mylyed.lessdoc.persist.entity.DocumentExample;
import io.github.mylyed.lessdoc.persist.mapper.DocumentMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.BytesRef;
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
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        //创建
        iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

        List<io.github.mylyed.lessdoc.persist.entity.Document> documents = documentMapper.selectByExampleWithBLOBs(new DocumentExample());

        try (IndexWriter writer = new IndexWriter(directory, iwc)) {
            for (io.github.mylyed.lessdoc.persist.entity.Document document : documents) {
                Document doc = buildLuceneDocument(document);
                if (update) {
                    writer.updateDocument(new Term("documentId", new BytesRef(document.getDocumentId())), doc);
                } else {
                    writer.addDocument(doc);
                }

            }

        }
    }


    private Document buildLuceneDocument(io.github.mylyed.lessdoc.persist.entity.Document document) {
        Document doc = new Document();
        doc.add(new StringField("documentId", String.valueOf(document.getDocumentId()), Field.Store.YES));
        doc.add(new StringField("bookId", String.valueOf(document.getBookId()), Field.Store.YES));
        doc.add(new StringField("documentNameStr", document.getDocumentName(), Field.Store.YES));

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
            QueryParser parse = new QueryParser("documentName", analyzer);
            Query query1 = parse.parse(key);

            QueryParser parse2 = new QueryParser("release", analyzer);
            Query query2 = parse2.parse(key);

            Term term = new Term("bookId", String.valueOf(bookId));
            TermQuery termQuery = new TermQuery(term);

            BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();

//            booleanQuery.add(query1, BooleanClause.Occur.MUST);
            booleanQuery.add(query2, BooleanClause.Occur.MUST);
            booleanQuery.add(termQuery, BooleanClause.Occur.MUST);


            TopDocs docs = searcher.search(booleanQuery.build(), Integer.MAX_VALUE);
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
