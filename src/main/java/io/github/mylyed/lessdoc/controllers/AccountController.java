package io.github.mylyed.lessdoc.controllers;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.github.mylyed.lessdoc.common.Const;
import io.github.mylyed.lessdoc.persist.entity.Member;
import io.github.mylyed.lessdoc.persist.entity.MemberExample;
import io.github.mylyed.lessdoc.persist.mapper.MemberMapper;
import io.github.mylyed.lessdoc.response.JsonResponse;
import io.github.mylyed.lessdoc.service.OptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author lilei
 * created at 2019/4/30
 */

@Controller
public class AccountController {
    Logger log = LoggerFactory.getLogger(AccountController.class);

    @GetMapping("/login")
    public String toLogin(Model model, String url) {
        //从哪个页面来的url
        model.addAttribute("url", url);
        // todo 读取cookie 自动登录
        return "account/login";
    }


    @Autowired
    DefaultKaptcha defaultKaptcha;


    @GetMapping(value = "/captcha", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] captcha(HttpServletRequest httpServletRequest) throws IOException {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        // 生产验证码字符串并保存到session中
        String createText = defaultKaptcha.createText();
        httpServletRequest.getSession().setAttribute(Const.SessionKey.CAPTCHA, createText);
        // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
        BufferedImage challenge = defaultKaptcha.createImage(createText);
        ImageIO.write(challenge, "jpg", jpegOutputStream);
        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        return captchaChallengeAsJpeg;

    }


    @Resource
    MemberMapper memberMapper;

    @Resource
    OptionService optionService;

    @PostMapping("/login")
    @ResponseBody
    public JsonResponse postLogin(
            String account,
            String password,
            String code,
            @RequestParam(value = "is_remember", required = false) String isRemember,
            String url,
            HttpServletRequest httpServletRequest
    ) {

        log.debug("account={}", account);
        log.debug("password={}", password);
        log.debug("code={}", code);
        log.debug("is_remember={}", isRemember);
        log.debug("url={}", url);

        Assert.hasText(account, "用户名为空");
        Assert.hasText(password, "密码为空");
        //验证码
        if (optionService.enabledCaptcha()) {
            //启用验证码
            Assert.hasText(code, "验证码为空");
            String rightCode = (String) httpServletRequest.getSession().getAttribute(Const.SessionKey.CAPTCHA);
            if (!code.equalsIgnoreCase(rightCode)) {
                return new JsonResponse(500, "验证码错误");
            }
        }


        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andAccountEqualTo(account);
        Member member = memberMapper.selectOneByExample(memberExample);
        if (member == null) {
            throw new IllegalArgumentException("用户不存在");
        }
        //todo 验证密码
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setData(url);

        httpServletRequest.getSession().setAttribute(Const.SessionKey.ACCOUNT, member);
        return jsonResponse;
    }

    /**
     * 退出登录
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest) {
        //cookis 清除 TODO
        httpServletRequest.getSession().removeAttribute(Const.SessionKey.ACCOUNT);
        //是从哪个页面跳转过来的
        String ref = httpServletRequest.getHeader("Referer");
        String url = (ref == null) ? "" : "?url=" + ref;
        return "redirect:/login" + url;
    }
}
