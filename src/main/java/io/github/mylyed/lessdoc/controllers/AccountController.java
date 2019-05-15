package io.github.mylyed.lessdoc.controllers;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.github.mylyed.lessdoc.common.Const;
import io.github.mylyed.lessdoc.common.TokenHolder;
import io.github.mylyed.lessdoc.persist.entity.Member;
import io.github.mylyed.lessdoc.persist.entity.MemberExample;
import io.github.mylyed.lessdoc.persist.mapper.MemberMapper;
import io.github.mylyed.lessdoc.response.JsonResponse;
import io.github.mylyed.lessdoc.service.OptionService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

/**
 * @author lilei
 * created at 2019/4/30
 */

@Controller
public class AccountController {
    Logger log = LoggerFactory.getLogger(AccountController.class);

    @GetMapping("/login")
    public String toLogin(Model model, String url) {
        if (TokenHolder.loginedMember() != null) {
            //已经登录了
            String ref = parseReferer();
            if (ref != null) {
                return "redirect:" + ref;
            } else if (url != null) {
                if (url.startsWith("http://") || url.startsWith("https://")) {
                    return "redirect:" + url;
                } else {
                    return "redirect:/" + url;
                }
            } else {
                return "redirect:/";
            }
        } else {
            //从哪个页面来的url
            model.addAttribute("url", url);
            // todo 读取cookie 自动登录
            return "account/login";
        }
    }


    @Autowired
    DefaultKaptcha defaultKaptcha;

    /**
     * 生产验证码
     *
     * @param httpServletRequest
     * @return
     * @throws IOException
     */
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

    /**
     * 登录操作
     *
     * @param account
     * @param password
     * @param code
     * @param isRemember
     * @param url
     * @param httpServletRequest
     * @return
     */
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
            throw new IllegalArgumentException("用户名或密码错误");
        }
        String passwordV = DigestUtils.md5Hex(password + member.getAccount());
        if (!Objects.equals(passwordV, member.getPassword())) {
            throw new IllegalArgumentException("用户名或密码错误");
        }
        if (member.getStatus() != 0) {
            throw new IllegalArgumentException("该用户已被锁定");
        }
        //更新最后登录时间
        Member updateDto = new Member();
        updateDto.setMemberId(member.getMemberId());
        updateDto.setLastLoginTime(new Date());
        memberMapper.updateByPrimaryKeySelective(updateDto);

        httpServletRequest.getSession().setAttribute(Const.SessionKey.ACCOUNT, member);

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setData(url);
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
        httpServletRequest.getSession().removeAttribute(Const.SessionKey.ACCOUNT);
        //是从哪个页面跳转过来的
        String ref = parseReferer();
        String url = (ref == null) ? "" : "?url=" + ref;
        return "redirect:/login" + url;
    }


    private String parseReferer() {
        String ref = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getHeader("Referer");
        log.debug("ref={}", ref);
        return ref;
    }

}
