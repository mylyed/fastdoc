package io.github.mylyed.lessdoc.common;

import io.github.mylyed.lessdoc.persist.entity.Member;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author lilei
 * created at 2019/5/1
 */
public class TokenHolder {


    /**
     * 获取已经登录的会员
     *
     * @return
     */
    public static Member loginedMember() {
        return (Member) ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getSession().getAttribute(Const.SessionKey.ACCOUNT);
    }
}
