package io.github.mylyed.lessdoc.exception;

/**
 * @author lilei
 * created at 2019/5/10
 */
public abstract class LessDocException extends RuntimeException {


    public abstract int errorCode();


    public LessDocException() {
        super();
    }

    public LessDocException(String message) {
        super(message);
    }

    public LessDocException(String message, Throwable cause) {
        super(message, cause);
    }

    public LessDocException(Throwable cause) {
        super(cause);
    }

    protected LessDocException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
