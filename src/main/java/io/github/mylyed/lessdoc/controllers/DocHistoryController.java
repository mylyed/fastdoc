package io.github.mylyed.lessdoc.controllers;

import io.github.mylyed.lessdoc.common.Const;
import io.github.mylyed.lessdoc.persist.entity.Book;
import io.github.mylyed.lessdoc.persist.entity.Document;
import io.github.mylyed.lessdoc.persist.entity.DocumentHistory;
import io.github.mylyed.lessdoc.persist.entity.DocumentHistoryExample;
import io.github.mylyed.lessdoc.persist.mapper.DocumentHistoryMapper;
import io.github.mylyed.lessdoc.response.JsonResponse;
import io.github.mylyed.lessdoc.response.Pagination;
import io.github.mylyed.lessdoc.service.BookService;
import io.github.mylyed.lessdoc.service.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 文档历史
 *
 * @author lilei
 * created at 2019/5/12
 */
@Slf4j
@Controller
@RequestMapping("doc_history")
public class DocHistoryController {

    @Resource
    DocumentHistoryMapper documentHistoryMapper;

    @Autowired
    BookService bookService;

    @Autowired
    DocumentService documentService;

    /**
     * 文档历史
     */
    @GetMapping
    public String list(Model model, @RequestParam("identify") String bookIdentify, @RequestParam("doc_id") Integer docId, Integer page) {

        log.debug("bookIdentify={}", bookIdentify);
        log.debug("docId={}", docId);
        log.debug("page={}", page);

        Book book = bookService.findBookByIdentify(bookIdentify);
        Assert.notNull(book, "项目不存在！");
        model.addAttribute("book", book);

        Document document = documentService.findDocById(docId);

        Assert.isTrue(Objects.equals(document.getBookId(), book.getBookId()), "文档所属项目错误");


        int pageIndex = page == null ? 1 : page;
        int pageSize = Const.PAGE_SIZE;

        DocumentHistoryExample documentHistoryExample = new DocumentHistoryExample();
        documentHistoryExample.createCriteria().andDocumentIdEqualTo(document.getDocumentId());
        documentHistoryExample.page(pageIndex - 1, pageSize);
        List<DocumentHistory> documentHistories = documentHistoryMapper.selectByExample(documentHistoryExample);

        model.addAttribute("historys", documentHistories);


        long count = documentHistoryMapper.countByExample(documentHistoryExample);

        Pagination pagination = new Pagination(pageIndex, pageSize, count);
        model.addAttribute("pagination", pagination);

        return "document/history/list";
    }


    private Book getAndCheckBook(String bookIdentify, Integer historyId) {
        Book book = bookService.findBookByIdentify(bookIdentify);
        Assert.notNull(book, "项目不存在！");
        DocumentHistory documentHistory = documentHistoryMapper.selectByPrimaryKey(historyId);
        Document document = documentService.findDocById(documentHistory.getDocumentId());
        Assert.isTrue(Objects.equals(document.getBookId(), book.getBookId()), "文档所属项目错误");
        return book;
    }

    /**
     * 文档对比
     */
    @GetMapping("compare")
    public String compare(Model model, @RequestParam("identify") String bookIdentify, @RequestParam("history_id") Integer historyId) {

        Book book = getAndCheckBook(bookIdentify, historyId);

        DocumentHistory documentHistory = documentHistoryMapper.selectByPrimaryKey(historyId);
        Document document = documentService.findDocById(documentHistory.getDocumentId());
        Assert.isTrue(Objects.equals(document.getBookId(), book.getBookId()), "文档所属项目错误");


        model.addAttribute("historyContent", documentHistory.getMarkdown());
        model.addAttribute("content", document.getMarkdown());

        return "document/history/compare";
    }


    @DeleteMapping
    @ResponseBody
    public JsonResponse deleteHistory(@RequestParam("identify") String bookIdentify, @RequestParam("history_id") Integer historyId) {
        getAndCheckBook(bookIdentify, historyId);
        int count = documentHistoryMapper.deleteByPrimaryKey(historyId);
        Assert.isTrue(count <= 1, "删除失败");
        return JsonResponse.builder().build();
    }

    /**
     * 回复指定版本
     *
     * @param bookIdentify
     * @param historyId
     * @return
     */
    @PostMapping
    @ResponseBody
    public JsonResponse restoreHistory(@RequestParam("identify") String bookIdentify, @RequestParam("history_id") Integer historyId) {
        getAndCheckBook(bookIdentify, historyId);


        DocumentHistory documentHistory = documentHistoryMapper.selectByPrimaryKey(historyId);
        Document document = documentService.findDocById(documentHistory.getDocumentId());

        document.setMarkdown(documentHistory.getMarkdown());
        document.setContent(documentHistory.getContent());
        document.setModifyTime(new Date());

        //TODO 构造修改文档的枚举

        return JsonResponse.builder().build();
    }

}
