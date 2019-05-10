package io.github.mylyed.lessdoc.service;

import io.github.mylyed.lessdoc.exception.DocumentVersionException;
import io.github.mylyed.lessdoc.persist.entity.Document;
import io.github.mylyed.lessdoc.persist.entity.DocumentExample;
import io.github.mylyed.lessdoc.persist.mapper.DocumentMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.IntStream;

/**
 * @author lilei
 * created at 2019/5/5
 */
@Service
public class DocumentService {
    @Resource
    DocumentMapper documentMapper;


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
        if (document.getDocumentId() != null && document.getDocumentId() != 0) {
            update(document, cover);
        } else {
            save(document);
        }
    }

    //文档标识 保留字段
    static final List<String> DOCUMENT_IDENTIFY_KEYWORD = Arrays.asList("", "");

    /**
     * 新增
     *
     * @param document
     */
    private void save(Document document) {
        Assert.hasText(document.getDocumentName(), "文档名称未填写");
        Assert.notNull(document.getBookId(), "bookId参数是空");
        //新增
        //清理下主键
        document.setDocumentId(null);
        document.setCreateTime(new Date());
        document.setModifyTime(new Date());
        document.setModifyAt(0);
        document.setParentId(Optional.ofNullable(document.getParentId()).orElse(0));

        //找到最大的序号
        DocumentExample documentExample = new DocumentExample();
        documentExample.createCriteria().andBookIdEqualTo(document.getBookId())
                .andParentIdEqualTo(document.getParentId());
        //
        int order = documentMapper.selectByExample(documentExample).stream().flatMapToInt(p -> IntStream.of(p.getOrderSort())).max().orElse(0);

        document.setOrderSort(order + 1);
        document.setIsOpen(Optional.ofNullable(document.getIsOpen()).orElse(true));
        String version = DigestUtils.md5Hex(document.getDocumentName());
        document.setVersion(version);

        //todo 当前登录人
        if (StringUtils.isEmpty(document.getIdentify())) {
            //用户没有自定义
            document.setIdentify(UUID.randomUUID().toString());
        } else {
            if (DOCUMENT_IDENTIFY_KEYWORD.contains(document.getIdentify())) {
                throw new IllegalArgumentException("不能使用文档标识" + document.getIdentify());
            }
            documentExample = new DocumentExample();
            documentExample.createCriteria().andBookIdEqualTo(document.getBookId())
                    .andIdentifyEqualTo(document.getIdentify());

            long count = documentMapper.countByExample(documentExample);
            Assert.isTrue(count == 0, "文档标识重复");

        }
        //新增
        documentMapper.insertSelective(document);

        documentExample = new DocumentExample();
        documentExample.createCriteria().andBookIdEqualTo(document.getBookId())
                .andIdentifyEqualTo(document.getIdentify());

        Document docSaveEd = documentMapper.selectOneByExample(documentExample);
        document.setDocumentId(docSaveEd.getDocumentId());
    }

    /**
     * 修改
     *
     * @param document
     * @param cover    是否覆盖
     */
    private void update(Document document, boolean cover) {
        Assert.notNull(document.getDocumentId(), "文档ID为空");
        Document exist = documentMapper.selectByPrimaryKey(document.getDocumentId());
        Assert.notNull(exist, "要修的文档不存在");
        if (!cover && StringUtils.isNotEmpty(document.getVersion())) {
            String v1 = exist.getVersion();
            String v2 = document.getVersion();
            if (!Objects.equals(v1, v2)) {
                throw new DocumentVersionException("版本不一致");
            }
        }
        String version = DigestUtils.md5Hex(Optional.ofNullable(document.getMarkdown()).orElse(document.getDocumentName()));
        //修改
        document.setModifyTime(new Date());
        document.setVersion(version);
        document.setModifyAt(exist.getModifyAt() + 1);
        int i = documentMapper.updateByPrimaryKeySelective(document);
        Assert.isTrue(i == 1, "修改失败");
        //日志
    }


    /**
     * 删除文档
     *
     * @param document
     */
    public void deleteDoc(Document document) {
        Assert.notNull(document.getDocumentId(), "文档ID为空");
        int i = documentMapper.deleteByPrimaryKey(document.getDocumentId());
        Assert.isTrue(i == 1, "删除失败");

        //TODO 还要删除相关日志和历史文档
    }

    /**
     * 保存文档的排序
     *
     * @param documents
     */
    public void saveDocSort(List<Document> documents) {
        for (Document document : documents) {
            if (document.getDocumentId() == null) {
                continue;
            }
            if (document.getOrderSort() == null) {
                continue;
            }
            if (document.getParentId() == null) {
                continue;
            }
            documentMapper.updateByPrimaryKeySelective(document);

        }
    }
}
