package io.github.mylyed.lessdoc;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;

import java.nio.file.Paths;

/**
 * @author lilei
 * created at 2019/5/16
 */
public class LuceneTest {
    public static void main(String[] args) throws Exception {
        IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get("lucene_index")));
        System.out.println(reader.getDocCount("documentId"));
        IndexSearcher searcher = new IndexSearcher(reader);

//        Query query = new MatchAllDocsQuery();
//        Query query = new TermQuery(new Term("bookId", "5"));

        Query query = TermRangeQuery.newStringRange("documentId", "1", "5", true, true);


        TopDocs docs = searcher.search(query, 100);

        System.out.println("查询到" + docs.totalHits + "条记录");

        //遍历查询结果
        for (ScoreDoc scoreDoc : docs.scoreDocs) {
            Document doc = searcher.doc(scoreDoc.doc);
//            System.out.println(doc);
            System.out.print(doc.getField("documentId").stringValue() + "  ");
            System.out.println(doc.getField("documentName").stringValue());
        }
    }
}
