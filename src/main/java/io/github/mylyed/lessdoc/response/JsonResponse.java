package io.github.mylyed.lessdoc.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lilei
 * created at 2019/4/30
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonResponse {

    private Integer errcode;
    private String message;
    private Object data;

    public Integer getErrcode() {
        return errcode == null ? 0 : errcode;
    }

    public String getMessage() {
        return message == null ? "ok" : message;
    }

    public JsonResponse(Integer errcode, String message) {
        this.errcode = errcode;
        this.message = message;
    }

}
