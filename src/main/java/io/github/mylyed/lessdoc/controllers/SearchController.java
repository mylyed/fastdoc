package io.github.mylyed.lessdoc.controllers;

import io.github.mylyed.lessdoc.common.Const;
import io.github.mylyed.lessdoc.response.Pagination;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
