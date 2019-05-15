package io.github.mylyed.lessdoc.common;

import java.nio.charset.Charset;

/**
 * @author lilei
 * created at 2019/5/1
 */
public interface Const {
    int PAGE_SIZE = 10;

    interface SessionKey {
        String CAPTCHA = "captcha";
        String ACCOUNT = "member";
    }

    interface Charsets {
        Charset UTF_8 = Charset.forName("utf-8");
    }


}
