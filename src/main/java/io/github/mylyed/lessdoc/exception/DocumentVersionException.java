package io.github.mylyed.lessdoc.exception;

/**
 * @author lilei
 * created at 2019/5/10
 */
public class DocumentVersionException extends LessDocException {
    @Override
    public int errorCode() {
        return 6005;
    }

    public DocumentVersionException() {
        super();
    }

    public DocumentVersionException(String message) {
        super(message);
    }

    public DocumentVersionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocumentVersionException(Throwable cause) {
        super(cause);
    }

    protected DocumentVersionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
