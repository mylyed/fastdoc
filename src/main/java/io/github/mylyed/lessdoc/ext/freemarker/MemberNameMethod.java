package io.github.mylyed.lessdoc.ext.freemarker;

import freemarker.template.SimpleNumber;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import io.github.mylyed.lessdoc.persist.entity.Member;
import io.github.mylyed.lessdoc.persist.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class MemberNameMethod implements TemplateMethodModelEx {

    @Resource
    MemberMapper memberMapper;


    @Override
    public Object exec(List arguments) throws TemplateModelException {
        if (arguments == null || arguments.isEmpty()) {
            return "";
        }
        log.debug("传入的参数:{}", arguments);
        //只有一个参数
        if (arguments.size() == 1) {
            Object object = arguments.get(0);
            return convertUserName(object);
        } else {
            //多个参数
            return arguments.stream().map(this::convertUserName).collect(Collectors.toList());
        }
    }

    private static Map<Integer, String> cache = new HashMap<>(16);

    public String convertUserName(Object object) {
        if (null == object) {
            return "";
        }
        try {
            int memberId;
            if (object instanceof SimpleNumber) {
                memberId = ((SimpleNumber) object).getAsNumber().intValue();
            } else {
                memberId = Integer.valueOf(object.toString());
            }
            if (cache.containsKey(memberId)) {
                return cache.get(memberId);
            }
            log.debug("freemarker获取{}的用户名", memberId);
            Member member = memberMapper.selectByPrimaryKey(memberId);
            if (member != null) {
                String name = Optional.ofNullable(member.getRealName()).orElse(member.getAccount());
                cache.put(memberId, name);
                return name;
            } else {
                log.warn("memberId:{},找不到成员", memberId);
                return "";
            }

        } catch (Exception e) {
            log.warn("用户翻译错误:{}", e.getMessage());
        }
        return "";
    }
}