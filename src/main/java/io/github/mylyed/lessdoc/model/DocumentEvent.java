package io.github.mylyed.lessdoc.model;

import io.github.mylyed.lessdoc.persist.entity.Document;
import lombok.Builder;
import lombok.Data;

/**
 * 文档的事件
 * @author lilei
 * created at 2019/5/16
 */
@Data
@Builder
public class DocumentEvent {
    public enum EventType {
        /**
         * 新增
         */
        CREATE,
        /**
         * 修改
         */
        UPDATE,
        /**
         * 删除
         */
        DELETE;
    }

    EventType eventType;
    Document document;

}
