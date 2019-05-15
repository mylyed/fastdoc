package io.github.mylyed.lessdoc.ext.permissions;

import java.lang.annotation.*;

/**
 * 表明该请求用户需要登录
 *
 * @author lilei
 * created at 2019/5/15
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiresUser {
}
