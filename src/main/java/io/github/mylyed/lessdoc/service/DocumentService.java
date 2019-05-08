package io.github.mylyed.lessdoc.service;

import io.github.mylyed.lessdoc.persist.entity.Book;
import io.github.mylyed.lessdoc.persist.entity.Document;
import io.github.mylyed.lessdoc.persist.entity.DocumentExample;
import io.github.mylyed.lessdoc.persist.mapper.DocumentMapper;
import io.github.mylyed.lessdoc.response.DocumentTree;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lilei
 * created at 2019/5/5
 */
@Service
public class DocumentService {
    @Resource
    DocumentMapper documentMapper;

    public List<DocumentTree> findDocsTreeByBook(final Book book) {
        return findDocsByBookIdAndParentId(book, 0);
    }


    public List<Document> findDocsByBook(final Book book) {
        DocumentExample documentExample = new DocumentExample();
        documentExample.createCriteria()
                .andBookIdEqualTo(book.getBookId());
        documentExample.setOrderByClause("order_sort");
        List<Document> documents = documentMapper.selectByExample(documentExample);

        return documents;
    }

    private List<DocumentTree> findDocsByBookIdAndParentId(final Book book, Integer parentId) {

        DocumentExample documentExample = new DocumentExample();
        documentExample.createCriteria()
                .andBookIdEqualTo(book.getBookId())
                .andParentIdEqualTo(parentId);
        documentExample.setOrderByClause("order_sort");
        List<Document> documents = documentMapper.selectByExample(documentExample);
        if (documents == null || documents.isEmpty()) {
            return null;
        }
        List<DocumentTree> documentTrees = new ArrayList<>();
        for (Document document : documents) {
            DocumentTree documentTree = new DocumentTree();
            documentTree.setDocumentId(document.getDocumentId());
            documentTree.setDocumentName(document.getDocumentName());
            documentTree.setParentId(document.getParentId());
            documentTree.setIdentify(document.getIdentify());
            documentTree.setBookIdentify(book.getIdentify());
            documentTree.setVersion(document.getVersion());
            documentTree.setOpen(document.getIsOpen());
            documentTree.setChildren(findDocsByBookIdAndParentId(book, document.getDocumentId()));
            documentTrees.add(documentTree);
        }

        return documentTrees;
    }


    public Document findDocById(Integer docId) {
        Assert.notNull(docId, "docId错误");
        DocumentExample documentExample = new DocumentExample();
        documentExample.createCriteria().andDocumentIdEqualTo(docId);
        Document document = documentMapper.selectOneByExampleWithBLOBs(documentExample);
        return document;
    }

    /**
     * 保存或修改
     *
     * @param document
     */
    public void saveOrUpdate(Document document, boolean cover) {

        document.setVersion(System.currentTimeMillis() / 1000);
        if (document.getDocumentId() != null && document.getDocumentId() != 0) {
            document.setModifyTime(new Date());
            int i = documentMapper.updateByPrimaryKeySelective(document);
            Assert.isTrue(i == 1, "修改失败");
            //日志
        } else {
            //清理下主键
            document.setDocumentId(null);
            document.setCreateTime(new Date());
            document.setModifyTime(new Date());
            document.setModifyAt(0);
            //新增
            documentMapper.insertSelective(document);
        }

    }
}
