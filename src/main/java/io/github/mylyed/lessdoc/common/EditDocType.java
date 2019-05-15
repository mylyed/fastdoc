package io.github.mylyed.lessdoc.common;

import lombok.AllArgsConstructor;

/**
 * 修改文档类型
 *
 * @author lilei
 * created at 2019/5/13
 */
@AllArgsConstructor
public enum EditDocType {
    UPDATE("modify", "修改文档", false),
    COVER("cover", "覆盖文档", true),
    RESTORE("restore", "恢复文档", true),
    ;
    public final String action;
    public final String actionName;
    //是否直接覆盖文档
    public final boolean cover;

}
