package io.github.mylyed.lessdoc.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    ObjectMapper objectMapper;

    /**
     * 跳转到辑页面
     *
     * @return
     */
    @GetMapping("/{bookIdentify}")
    public String editPage(Model model,
                           @PathVariable("bookIdentify") String bookIdentify) throws JsonProcessingException {
        Book book = bookService.findBookByIdentify(bookIdentify);
        Assert.notNull(book, "项目不存在");
        model.addAttribute("book", book);
        List<Document> documents = documentService.findDocsByBook(book);

        List<Map<String, Object>> tree = new ArrayList<>(documents.size());
        for (Document document : documents) {
            Map<String, Object> node = new LinkedHashMap<>(6);
            node.put("id", document.getDocumentId());
            node.put("text", document.getDocumentName());
            node.put("parent", document.getParentId() == 0 ? "#" : document.getParentId());
            node.put("identify", document.getIdentify());
            node.put("version", document.getVersion());
            if (document.getIsOpen()) {
                Map<String, Object> attr = new LinkedHashMap<>(6);
                attr.put("is_open", true);
                node.put("a_attr", attr);

                Map<String, Object> state = new LinkedHashMap<>(6);
                state.put("opened", true);
                node.put("state", state);
            }
            tree.add(node);
        }
        model.addAttribute("documentCategory", objectMapper.writeValueAsString(tree));


        return "document/edit/markdown_edit_template";
    }


    @PostMapping("")
    @ResponseBody
    public JsonResponse saveDoc(Document document, boolean cover) {
        log.debug("document={}", document);
        log.debug("cover={}", cover);
        documentService.saveOrUpdate(document, cover);
        JsonResponse response = new JsonResponse();
        response.setData(document);
        return response;
    }

}
