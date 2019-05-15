package io.github.mylyed.lessdoc.controllers;

import io.github.mylyed.lessdoc.ext.permissions.RequiresUser;
import io.github.mylyed.lessdoc.response.EditormdResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

/**
 * 附件
 *
 * @author lilei
 * created at 2019/5/11
 */
@Controller
@ResponseBody
@Slf4j
public class UploadController {

    /**
     * 图片上传
     *
     * @param file
     * @return
     */
    @PostMapping("/upload/image")
    @RequiresUser
    public EditormdResponse upload(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file) {

        if (file == null || file.isEmpty()) {
            return EditormdResponse.builder().success(0).message("没有上传数据").build();
        }

        try {
            final String origFileName = file.getOriginalFilename();
            log.debug("文件名={}", origFileName);
            final String fileTyle = origFileName.substring(origFileName.lastIndexOf("."));
            log.debug("文件类型={}", fileTyle);
            if (!isImage(fileTyle)) {
                return EditormdResponse.builder().success(0).message("上传的不是图片文件").build();
            }

            final StringBuilder filePath = new StringBuilder(System.getProperty("user.dir"));
            filePath
                    .append(File.separator)
                    .append("upload")
                    .append(File.separator)
                    .append("images");


            final File images = new File(filePath.toString());
            while (!images.exists()) {
                log.error("文件夹不存在={}", filePath);
                images.mkdirs();
            }

            final String fileName = UUID.randomUUID().toString() + fileTyle;
            log.debug("fileName={}", fileName);


            File dest = new File(images, fileName);

            file.transferTo(dest);


            return EditormdResponse.builder().success(1).message("上传成功").url("http://127.0.0.1:8080/static/images/" + fileName).build();
        } catch (Exception e) {
            log.error("上传失败", e);
            return EditormdResponse.builder().success(0).message("上传失败").build();
        }

    }

    /**
     * 根据文件后缀判断文件是否是图片
     *
     * @param ext
     * @return
     */
    private boolean isImage(String ext) {
        return Objects.equals(ext, ".jpg") ||
                Objects.equals(ext, ".jpeg") ||
                Objects.equals(ext, ".png") ||
                Objects.equals(ext, ".gif") ||
                Objects.equals(ext, ".svg") ||
                Objects.equals(ext, ".bmp") ||
                Objects.equals(ext, ".webp");
    }


}
