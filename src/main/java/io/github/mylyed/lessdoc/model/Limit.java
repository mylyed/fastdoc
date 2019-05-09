package io.github.mylyed.lessdoc.model;

import lombok.Data;

/**
 * @author lilei
 * created at 2019/5/8
 */
@Data
public class Limit {
    protected Integer offset;
    protected Integer rows;


    public static Limit page(Integer page, Integer pageSize) {
        Limit limit = new Limit();
        limit.offset = page * pageSize;
        limit.rows = pageSize;
        return limit;
    }
}
