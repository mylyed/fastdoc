package io.github.mylyed.lessdoc.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mylyed.lessdoc.common.TokenHolder;
import io.github.mylyed.lessdoc.ext.permissions.RequiresUser;
import io.github.mylyed.lessdoc.persist.entity.Member;
import io.github.mylyed.lessdoc.persist.mapper.MemberMapper;
import io.github.mylyed.lessdoc.response.JsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 个人设置中心
 *
 * @author lilei
 * created at 2019/5/1
 */

@Controller
@RequestMapping("/setting")
@RequiresUser
@Slf4j
public class SettingController {


    @Resource
    MemberMapper memberMapper;

    @GetMapping({"", "/", "/index"})
    public String index() {
        return "setting/index";
    }

    @GetMapping("/password")
    public String password() {
        return "setting/password";
    }


    /**
     * 修改密码
     *
     * @param password1 原密码
     * @param password2 修改密码
     * @param password3 确认密码
     * @return
     */
    @PostMapping("/password")
    @ResponseBody
    public JsonResponse updatePassword(String password1, String password2, String password3) {
        Assert.hasText(password1, "原密码不能为空");
        Assert.hasText(password2, "新密码不能为空");
        Assert.hasText(password3, "确认密码不能为空");
        if (password2.length() < 6 || password2.length() > 18) {
            throw new IllegalArgumentException("密码必须在6-18字之间");
        }
        if (!password2.equalsIgnoreCase(password3)) {
            throw new IllegalArgumentException("确认密码不正确");
        }
        if (password1.equalsIgnoreCase(password2)) {
            throw new IllegalArgumentException("新密码不能和原始密码相同");
        }
        Member member = memberMapper.selectByPrimaryKey(TokenHolder.loginedMember().getMemberId());

        // 验证旧密码 修改新密码
        String passwordOld = DigestUtils.md5Hex(password1 + member.getAccount());
        if (!member.getPassword().equalsIgnoreCase(passwordOld)) {
            throw new IllegalArgumentException("原密码错误");
        }
        String passwordNew = DigestUtils.md5Hex(password2 + member.getAccount());
        member.setPassword(passwordNew);
        memberMapper.updateByPrimaryKeySelective(member);

        return new JsonResponse();
    }

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping
    @ResponseBody
    public JsonResponse updateMember(Member memberUpdate) throws JsonProcessingException {
        log.debug("member={}", objectMapper.writeValueAsString(memberUpdate));

        memberUpdate.setMemberId(TokenHolder.loginedMember().getMemberId());
        memberUpdate.setPassword(null);

        memberMapper.updateByPrimaryKeySelective(memberUpdate);

        TokenHolder.setLoginedMember(memberMapper.selectByPrimaryKey(memberUpdate.getMemberId()));

        return JsonResponse.builder().build();
    }
}
