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
import java.util.List;

/**
 * 文档
 *
 * @author lilei
 * created at 2019/5/4
 */

@Controller
@RequestMapping("doc/read")
public class DocumentReadController {
    Logger log = LoggerFactory.getLogger(DocumentReadController.class);

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
        String title, content;
        Integer documentId = -1;

        if (StringUtils.isNotBlank(docIdentify)) {
            model.addAttribute("docIdentify", docIdentify);
            DocumentExample documentExample = new DocumentExample();
            documentExample.createCriteria().andBookIdEqualTo(book.getBookId()).andIdentifyEqualTo(docIdentify);
            documentExample.limit(1);
            Document document = documentMapper.selectOneByExampleWithBLOBs(documentExample);

            Assert.notNull(document, "文档不存在");
            documentId = document.getDocumentId();
            title = document.getDocumentName();
            content = document.getRelease();
        } else if (book.getUseFirstDocument()) {
            DocumentExample documentExample = new DocumentExample();
            documentExample.createCriteria().andBookIdEqualTo(book.getBookId()).andParentIdEqualTo(0);
            documentExample.setOrderByClause("order_sort");
            documentExample.limit(1);
            Document document = documentMapper.selectOneByExampleWithBLOBs(documentExample);
            documentId = document.getDocumentId();
            title = document.getDocumentName();
            content = document.getRelease();
        } else {
            title = "概要";
            content = book.getDescription();


        }
        model.addAttribute("title", title);
        model.addAttribute("content", content);
        //当前文档ID
        model.addAttribute("currentDocumentId", documentId);
        return "document/read/default_read";
    }


    /**
     * 读取已经发布的文档片段
     *
     * @param bookIdentify book
     * @param docIdentify  doc
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
        //为了减少传输体积
        document.setMarkdown(null);
        document.setContent(null);
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setData(document);

        return jsonResponse;
    }


}
