package io.github.mylyed.lessdoc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mylyed.lessdoc.common.Const;
import io.github.mylyed.lessdoc.persist.entity.Book;
import io.github.mylyed.lessdoc.persist.entity.Document;
import io.github.mylyed.lessdoc.persist.mapper.DocumentMapper;
import io.github.mylyed.lessdoc.response.JsonResponse;
import io.github.mylyed.lessdoc.response.Pagination;
import io.github.mylyed.lessdoc.service.BookService;
import io.github.mylyed.lessdoc.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author lilei
 * created at 2019/5/1
 */
@Controller
@RequestMapping("search")
public class SearchController {


    @Autowired
    Analyzer analyzer;

    @Autowired
    Directory directory;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    SearchService searchService;

    @RequestMapping({"", "/", "/index"})
    public String index(Model model, String keyword, Integer page) {
        page = Optional.ofNullable(page).orElse(1);
        model.addAttribute("keyword", keyword);

        try (IndexReader reader = DirectoryReader.open(directory);) {
            List<io.github.mylyed.lessdoc.persist.entity.Document> list = new ArrayList<>();
            IndexSearcher searcher = new IndexSearcher(reader);

            Query query1 = new QueryParser("documentNameStr", analyzer).parse(keyword);
            Query query2 = new QueryParser("releaseStr", analyzer).parse(keyword);

            BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();

            booleanQuery.add(query1, BooleanClause.Occur.SHOULD);
            booleanQuery.add(query2, BooleanClause.Occur.SHOULD);

            TopDocs docs = searcher.search(booleanQuery.build(), Const.PAGE_SIZE);

            for (ScoreDoc scoreDoc : docs.scoreDocs) {
                org.apache.lucene.document.Document d = searcher.doc(scoreDoc.doc);
                String json = d.getField("json").stringValue();
                io.github.mylyed.lessdoc.persist.entity.Document document = objectMapper.readValue(json, io.github.mylyed.lessdoc.persist.entity.Document.class);
                list.add(document);
            }

            //分页问题
            model.addAttribute("lists", list);
            Pagination pagination = new Pagination(page, Const.PAGE_SIZE, docs.totalHits.value);

            model.addAttribute("pagination", pagination);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return "search/index";
    }


    @RequestMapping("/init")
    @ResponseBody
    public JsonResponse init(boolean update) throws IOException {
        searchService.initSearch(update);
        return JsonResponse.builder().build();
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
            Assert.notNull(book, "项目不存在");
            List<Document> documents = searchService.search(keyword, book.getBookId());
            jsonResponse.setData(documents);

        }
        return jsonResponse;


    }
}
