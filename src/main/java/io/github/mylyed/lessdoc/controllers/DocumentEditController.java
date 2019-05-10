package io.github.mylyed.lessdoc.controllers;

import io.github.mylyed.lessdoc.persist.entity.Book;
import io.github.mylyed.lessdoc.persist.entity.Document;
import io.github.mylyed.lessdoc.response.JsonResponse;
import io.github.mylyed.lessdoc.service.BookService;
import io.github.mylyed.lessdoc.service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 文档编辑
 *
 * @author lilei
 * created at 2019/5/4
 */

@Controller
@RequestMapping("doc/edit")
public class DocumentEditController {
    Logger log = LoggerFactory.getLogger(DocumentEditController.class);

    @Resource
    DocumentService documentService;

    @Resource
    BookService bookService;


    /**
     * 跳转到辑页面
     *
     * @return
     */
    @GetMapping("/{bookIdentify}")
    public String editPage(Model model,
                           @PathVariable("bookIdentify") String bookIdentify) {
        Book book = bookService.findBookByIdentify(bookIdentify);
        Assert.notNull(book, "项目不存在");
        model.addAttribute("book", book);

        return "document/edit/markdown_edit_template";
    }


    /**
     * 保存或者修改文档信息
     *
     * @param document 文档信息
     * @param cover    是否强制覆盖
     * @return
     */
    @PostMapping()
    @ResponseBody
    public JsonResponse saveOrUpdate(Document document, boolean cover) {
        log.debug("document={}", document);
        log.debug("cover={}", cover);
        documentService.saveOrUpdate(document, cover);
        JsonResponse response = new JsonResponse();
        response.setData(document);
        return response;
    }


    @DeleteMapping()
    @ResponseBody
    public JsonResponse delete(Document document) {
        log.debug("document={}", document);
        documentService.deleteDoc(document);
        JsonResponse response = new JsonResponse();
        return response;
    }

}
