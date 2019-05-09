package io.github.mylyed.lessdoc.controllers;

import io.github.mylyed.lessdoc.persist.entity.Document;
import io.github.mylyed.lessdoc.persist.entity.DocumentExample;
import io.github.mylyed.lessdoc.persist.mapper.DocumentMapper;
import io.github.mylyed.lessdoc.response.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文档编辑
 *
 * @author lilei
 * created at 2019/5/4
 */

@Controller
@RequestMapping("doc")
public class DocumentController {
    Logger log = LoggerFactory.getLogger(DocumentController.class);

    @Resource
    DocumentMapper documentMapper;

    @RequestMapping(value = "/docTree/{bookId}", headers = {"X-Requested-With=XMLHttpRequest"})
    @ResponseBody
    public JsonResponse docTree(@PathVariable("bookId") Integer bookId) {
        DocumentExample documentExample = new DocumentExample();
        documentExample.createCriteria().andBookIdEqualTo(bookId);
        List<Document> documents = documentMapper.selectByExample(documentExample);
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setData(documents);
        return jsonResponse;

    }

}
