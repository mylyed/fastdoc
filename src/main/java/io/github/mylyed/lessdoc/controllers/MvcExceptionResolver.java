package io.github.mylyed.lessdoc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 调用 controller 出现异常处理
 *
 * @author lilei
 * created at 2019/5/5
 */
@Component
public class MvcExceptionResolver implements HandlerExceptionResolver {

    Logger log = LoggerFactory.getLogger(MvcExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.debug("handler={}", handler);
        log.error("exception", ex);


        String xrw = request.getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equals(xrw);
        log.debug("isAjax={}", isAjax);
        ModelAndView modelAndView;
        if (isAjax) {
            modelAndView = new ModelAndView(new MappingJackson2JsonView());
        } else {
            modelAndView = new ModelAndView("error/error");
        }
        modelAndView.addObject("errcode", 500);
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;

    }
}
