package io.github.mylyed.lessdoc.response;

/**
 * @author lilei
 * created at 2019/4/30
 */
public class JsonResponse {

    private Integer errcode = 0;
    private String message = "ok";
    private Object data;

    public JsonResponse() {
    }

    public JsonResponse(Integer errcode, String message) {
        this.errcode = errcode;
        this.message = message;
    }

    public JsonResponse(String message) {
        this.message = message;
    }


    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
