package io.github.mylyed.lessdoc.controllers;

import io.github.mylyed.lessdoc.persist.entity.Book;
import io.github.mylyed.lessdoc.persist.entity.Document;
import io.github.mylyed.lessdoc.persist.entity.DocumentExample;
import io.github.mylyed.lessdoc.persist.mapper.DocumentMapper;
import io.github.mylyed.lessdoc.response.DocumentTree;
import io.github.mylyed.lessdoc.response.JsonResponse;
import io.github.mylyed.lessdoc.service.BookService;
import io.github.mylyed.lessdoc.service.DocumentService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文档
 *
 * @author lilei
 * created at 2019/5/4
 */

@Controller
@RequestMapping("docs")
public class DocumentController {
    Logger log = LoggerFactory.getLogger(DocumentController.class);

    @Resource
    DocumentMapper documentMapper;


    @Resource
    DocumentService documentService;

    @Resource
    BookService bookService;

    @GetMapping({"/{bookIdentify}", "/{bookIdentify}/{docIdentify}"})
    public String index(Model model, @PathVariable("bookIdentify") String bookIdentify, @PathVariable(value = "docIdentify", required = false) String docIdentify) {
        log.debug("bookIdentify={}", bookIdentify);
        log.debug("docIdentify={}", docIdentify);
        Assert.hasText(bookIdentify, "参数错误");
        Book book = bookService.findBookByIdentify(bookIdentify);
        Assert.notNull(book, "项目不存在");
        model.addAttribute("book", book);


        List<DocumentTree> documentTrees = documentService.findDocsTreeByBook(book);
        model.addAttribute("documentTrees", documentTrees);

        if (StringUtils.isNotBlank(docIdentify)) {
            model.addAttribute("docIdentify", docIdentify);
            DocumentExample documentExample = new DocumentExample();
            documentExample.createCriteria().andBookIdEqualTo(book.getBookId()).andIdentifyEqualTo(docIdentify);
            documentExample.limit(1);
            Document document = documentMapper.selectOneByExampleWithBLOBs(documentExample);

            Assert.notNull(document, "文档不存在");

            model.addAttribute("title", document.getDocumentName());
            model.addAttribute("content", document.getRelease());
        } else if (book.getUseFirstDocument()) {
            DocumentExample documentExample = new DocumentExample();
            documentExample.createCriteria().andBookIdEqualTo(book.getBookId()).andParentIdEqualTo(0);
            documentExample.setOrderByClause("order_sort");
            documentExample.limit(1);
            Document document = documentMapper.selectOneByExampleWithBLOBs(documentExample);

            model.addAttribute("title", document.getDocumentName());
            model.addAttribute("content", document.getRelease());

        } else {
            model.addAttribute("title", "概要");
            model.addAttribute("content", book.getDescription());

        }

        return "document/default_read";
    }


    /**
     * 读取已经发了的文档
     *
     * @param bookIdentify
     * @param docIdentify
     * @return
     */
    @RequestMapping(value = "/{bookIdentify}/{docIdentify}", headers = {"X-Requested-With=XMLHttpRequest"})
    @ResponseBody
    public JsonResponse readReleaseDoc(@PathVariable("bookIdentify") String bookIdentify,
                                       @PathVariable("docIdentify") String docIdentify) {

        Assert.hasText(bookIdentify, "bookIdentify参数错误");
        Assert.hasText(docIdentify, "docIdentify参数错误");
        Book book = bookService.findBookByIdentify(bookIdentify);
        Assert.notNull(book, "项目不存在");

        DocumentExample documentExample = new DocumentExample();
        documentExample.createCriteria().andBookIdEqualTo(book.getBookId()).andIdentifyEqualTo(docIdentify);
        documentExample.limit(1);
        Document document = documentMapper.selectOneByExampleWithBLOBs(documentExample);
        Assert.notNull(document, "文档不存在");

        Map<String, Object> data = new HashMap<>();

        data.put("body", document.getRelease());
        data.put("doc_title", document.getDocumentName());
        data.put("title", document.getDocumentName());
        data.put("version", document.getVersion());


        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setData(data);

        return jsonResponse;
    }


    /**
     * 获取文档的原始数据
     *
     * @param docId
     * @return
     */
    @RequestMapping(value = "/raw/{document_id}", headers = {"X-Requested-With=XMLHttpRequest"})
    @ResponseBody
    public JsonResponse readDocRaw(@PathVariable("document_id") Integer docId) {


        Document document = documentService.findDocById(docId);
        Assert.notNull(document, "文档不存在");
        Map<String, Object> data = new HashMap<>();
        data.put("doc_id", document.getDocumentId());
        data.put("doc_name", document.getDocumentName());
        data.put("parent_id", document.getParentId());
        data.put("identify", document.getIdentify());
        data.put("version", document.getVersion());
        data.put("markdown", document.getMarkdown());
        data.put("attach", new ArrayList<>());
        //附件

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setData(data);
        return jsonResponse;
    }


}
