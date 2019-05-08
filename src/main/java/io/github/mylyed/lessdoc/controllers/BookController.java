package io.github.mylyed.lessdoc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lilei
 * created at 2019/5/4
 */

@Controller
@RequestMapping("/book")
public class BookController {

    @GetMapping({"", "/", "/index"})
    public String index(Model model) {

        return "book/index";

    }

}
