package io.github.mylyed.lessdoc.ext.permissions;

import io.github.mylyed.lessdoc.common.TokenHolder;
import io.github.mylyed.lessdoc.persist.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截器
 */
@Component
@Slf4j
public class PermissionsInterceptor
        implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        log.debug("拦截器  handler=：{}", handler.toString());
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //类上获取注解
            RequiresUser requiresUser = handlerMethod.getBeanType().getAnnotation(RequiresUser.class);
            if (requiresUser == null) {
                //方法上获取注解
                requiresUser = handlerMethod.getMethodAnnotation(RequiresUser.class);
            }
            if (requiresUser != null) {
                log.debug("需要验证当前是否有用户登录");
                //需要验证用户
                Member member = TokenHolder.loginedMember();
                Assert.notNull(member, "请先登录");
            }
        }
        return true;

    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
