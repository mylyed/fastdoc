package io.github.mylyed.lessdoc.response;

import java.util.List;

/**
 * @author lilei
 * created at 2019/5/5
 */
public class DocumentTree {

    private int documentId;// int               `json:"id"`
    private String documentName;// string            `json:"text"`
    private int parentId;// interface{}       `json:"parent"`
    private String identify;// string            `json:"identify"`
    private String bookIdentify;// string            `json:"-"`
    private Long version;// int64             `json:"version"`

    private boolean open;


    private List<DocumentTree> children;


    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getBookIdentify() {
        return bookIdentify;
    }

    public void setBookIdentify(String bookIdentify) {
        this.bookIdentify = bookIdentify;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }


    public List<DocumentTree> getChildren() {
        return children;
    }

    public void setChildren(List<DocumentTree> children) {
        this.children = children;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
