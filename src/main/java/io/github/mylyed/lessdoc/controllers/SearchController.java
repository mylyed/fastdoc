package io.github.mylyed.lessdoc.controllers;

import io.github.mylyed.lessdoc.common.Const;
import io.github.mylyed.lessdoc.persist.entity.Book;
import io.github.mylyed.lessdoc.persist.entity.Document;
import io.github.mylyed.lessdoc.persist.entity.DocumentExample;
import io.github.mylyed.lessdoc.persist.mapper.DocumentMapper;
import io.github.mylyed.lessdoc.response.JsonResponse;
import io.github.mylyed.lessdoc.response.Pagination;
import io.github.mylyed.lessdoc.service.BookService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author lilei
 * created at 2019/5/1
 */
@Controller
@RequestMapping("search")
public class SearchController {


    @RequestMapping({"", "/", "/index"})
    public String index(Model model, String keyword, Integer page) {
        page = Optional.ofNullable(page).orElse(1);

        model.addAttribute("keyword", keyword);
        Pagination pagination = new Pagination(page, Const.PAGE_SIZE, 100L);
        model.addAttribute("pagination", pagination);
        return "search/index";
    }


    @Resource
    BookService bookService;

    @Resource
    DocumentMapper documentMapper;


    /**
     * 目录搜索
     *
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/{bookIdentify}", headers = {"X-Requested-With=XMLHttpRequest"})
    @ResponseBody
    public JsonResponse search(@PathVariable("bookIdentify") String bookIdentify, String keyword) {
        JsonResponse jsonResponse = new JsonResponse();
        if (StringUtils.isNotEmpty(keyword)) {
            Book book = bookService.findBookByIdentify(bookIdentify);

            DocumentExample example = new DocumentExample();
            //TODO
            example.createCriteria().andBookIdEqualTo(book.getBookId())
                    .andDocumentNameLike("%" + keyword.trim() + "%");

            List<Document> documents = documentMapper.selectByExample(example);
            jsonResponse.setData(documents);

        }
        return jsonResponse;


    }
}
