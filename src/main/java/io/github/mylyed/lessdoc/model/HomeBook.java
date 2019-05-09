package io.github.mylyed.lessdoc.model;

import lombok.Data;

import java.util.Optional;

/**
 * 给首页使用的book信息
 *
 * @author lilei
 * created at 2019/5/8
 */

@Data
public class HomeBook {
    /**
     *
     */
    private String identify;
    private String bookName;
    private String cover;
    //
    private String createAccount;
    private String createRealName;


    private String author;


    public String getAuthor() {
        return Optional.ofNullable(createRealName).orElse(createAccount);
    }

}
