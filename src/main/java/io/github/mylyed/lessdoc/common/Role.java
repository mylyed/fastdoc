package io.github.mylyed.lessdoc.common;

import lombok.AllArgsConstructor;

/**
 * @author lilei
 * created at 2019/5/22
 */
@AllArgsConstructor
public enum Role {
    //    if book.RoleId == 0 {
//        book.RoleName = "创始人"
//    } else if book.RoleId == 1 {
//        book.RoleName = "管理员"
//    } else if book.RoleId == 2 {
//        book.RoleName = "编辑者"
//    } else if book.RoleId == 3 {
//        book.RoleName = "观察者"
//    }
    FOUNDER("创始人"),
    ADMIN("管理员"),
    EDITOR("编辑者"),
    OBSERVER("观察者"),
    ;
    public String roleName;
}
