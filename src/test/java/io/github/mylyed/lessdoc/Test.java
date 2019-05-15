package io.github.mylyed.lessdoc;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author lilei
 * created at 2019/5/15
 */
public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
//        String passwordV = DigestUtils.md5Hex("1");
//        System.out.println(passwordV);

        System.out.println("https://blog.csdn.net/yangzhenzhen/article/details/56014829".matches("http[s]?://"));
    }
}
