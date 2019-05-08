package io.github.mylyed.lessdoc.controllers;

import io.github.mylyed.lessdoc.persist.entity.Itemset;
import io.github.mylyed.lessdoc.persist.entity.ItemsetExample;
import io.github.mylyed.lessdoc.persist.mapper.ItemsetMapper;
import io.github.mylyed.lessdoc.response.Pagination;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static io.github.mylyed.lessdoc.common.Const.PAGE_SIZE;

/**
 * 项目空间列表
 *
 * @author lilei
 * created at 2019/5/1
 */
@Controller
@RequestMapping("/items")
public class ItemsetsController extends BaseController {

    @Resource
    ItemsetMapper itemsetMapper;

    @GetMapping({"", "/", "/index"})
    public String index(Model model, Integer page) {

        int pageIndex = Optional.ofNullable(page).orElse(1);

        ItemsetExample itemsetExample = new ItemsetExample();
        itemsetExample.page(pageIndex - 1, PAGE_SIZE);

        List<Itemset> itemsets = itemsetMapper.selectByExample(itemsetExample);
        long count = itemsetMapper.countByExample(itemsetExample);

        model.addAttribute("itemsets", itemsets);
        Pagination pagination = new Pagination(pageIndex, PAGE_SIZE, count);
        model.addAttribute("pagination", pagination);
        return "items/index";
    }
}
