package io.github.mylyed.lessdoc.controllers;

import io.github.mylyed.lessdoc.persist.entity.Book;
import io.github.mylyed.lessdoc.persist.entity.BookExample;
import io.github.mylyed.lessdoc.persist.mapper.BookMapper;
import io.github.mylyed.lessdoc.response.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lilei
 * created at 2019/4/30
 */
@Controller
public class HomeController extends BaseController {

    @Resource
    BookMapper bookMapper;


    @RequestMapping({"", "/", "/index"})
    public String index(Model model, Integer page) {

        int pageIndex = page == null ? 1 : page;
        int pageSize = 18;


        BookExample bookExample = new BookExample();
        bookExample.page(pageIndex - 1, pageSize);
        List<Book> books = bookMapper.selectByExample(bookExample);
        long count = bookMapper.countByExample(bookExample);
        model.addAttribute("books", books);

        Pagination pagination = new Pagination(pageIndex, pageSize, count);
        model.addAttribute("pagination", pagination);
        return "home/index";
    }


    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @RequestMapping(value = "/mappings")
    @ResponseBody
    public Object list() {
        List<HashMap<String, Object>> urlList = new ArrayList<HashMap<String, Object>>();

        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            PatternsRequestCondition p = info.getPatternsCondition();
            hashMap.put("url", p.getPatterns());
            hashMap.put("className", method.getMethod().getDeclaringClass().getName()); // 类名
            hashMap.put("method", method.getMethod().getName()); // 方法名
            RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
            String type = methodsCondition.toString();
            if (type != null && type.startsWith("[") && type.endsWith("]")) {
                type = type.substring(1, type.length() - 1);
                hashMap.put("type", type); // 方法名
            }
            urlList.add(hashMap);
        }
        return urlList;
    }
}
