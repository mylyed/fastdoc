package io.github.mylyed.lessdoc.response;

import lombok.Builder;
import lombok.Data;

/**
 * editor.md图片上传 返回json格式
 * http://editor.md.ipandao.com/examples/image-upload.html
 *
 * @author lilei
 * created at 2019/4/30
 */
@Data
@Builder
public class EditormdResponse {
    private int success = 1;//: 0 | 1,           // 0 表示上传失败，1 表示上传成功
    private String message;//: "提示的信息，上传成功或上传失败及错误信息等。",
    private String url;//: "图片地址"        // 上传成功时才返回

}
