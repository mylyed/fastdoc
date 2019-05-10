package io.github.mylyed.lessdoc.controllers;

import io.github.mylyed.lessdoc.persist.entity.Document;
import io.github.mylyed.lessdoc.persist.entity.DocumentExample;
import io.github.mylyed.lessdoc.persist.mapper.DocumentMapper;
import io.github.mylyed.lessdoc.response.JsonResponse;
import io.github.mylyed.lessdoc.service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文档编辑
 *
 * @author lilei
 * created at 2019/5/4
 */

@Controller
@RequestMapping("doc")
public class DocumentController {
    Logger log = LoggerFactory.getLogger(DocumentController.class);

    @Resource
    DocumentMapper documentMapper;

    @Resource
    DocumentService documentService;

    /**
     * 目录结构
     *
     * @param bookId
     * @return
     */
    @RequestMapping(value = "/catalog/{bookId}", headers = {"X-Requested-With=XMLHttpRequest"})
    @ResponseBody
    public JsonResponse docTree(@PathVariable("bookId") Integer bookId) {
        DocumentExample documentExample = new DocumentExample();
        documentExample.createCriteria().andBookIdEqualTo(bookId);
        documentExample.setOrderByClause("order_sort asc");
        List<Document> documents = documentMapper.selectByExample(documentExample);
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setData(documents);
        return jsonResponse;

    }

    /**
     * 获取文档的markdown原始信息
     *
     * @param docId
     * @return
     */
    @GetMapping("/markdown/{docId}")
    @ResponseBody
    public JsonResponse getMarkdownRaw(@PathVariable("docId") Integer docId) {
        Document document = documentService.findDocById(docId);
        Assert.notNull(document, "文档不存在");
        JsonResponse jsonResponse = new JsonResponse();
        document.setRelease(null);
        document.setContent(null);
        jsonResponse.setData(document);
        return jsonResponse;
    }


    /**
     * 文档排序
     *
     * @param documents 文档的排序信息
     * @return
     */
    @PostMapping("/sort")
    @ResponseBody
    public JsonResponse docSort(@RequestBody List<Document> documents) {

        documentService.saveDocSort(documents);

        return new JsonResponse();
    }

}
