package io.github.mylyed.lessdoc.service;

import io.github.mylyed.lessdoc.persist.entity.Option;
import io.github.mylyed.lessdoc.persist.entity.OptionExample;
import io.github.mylyed.lessdoc.persist.mapper.OptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lilei
 * created at 2019/5/1
 */
@Service
public class OptionService {

    Logger log = LoggerFactory.getLogger(OptionService.class);

    @Resource
    OptionMapper optionMapper;


    public Map<String, String> allOptions() {
        List<Option> options = optionMapper.selectByExampleWithBLOBs(new OptionExample());
        Map<String, String> map = new HashMap<>(options.size());
        for (Option option : options) {
            String name = option.getOptionName();
            String value = option.getOptionValue();
            log.debug("全局变量name={},value={}", name, value);
            map.put(name, value);
        }
        return map;
    }


    public Option findByOptName(String name) {
        OptionExample optionExample = new OptionExample();
        optionExample.createCriteria().andOptionNameEqualTo(name);
        Option option = optionMapper.selectOneByExampleWithBLOBs(optionExample);

        return option;
    }

    /**
     * 是否启用验证码
     *
     * @return
     */
    public boolean enabledCaptcha() {
        return Boolean.valueOf(findByOptName("ENABLED_CAPTCHA").getOptionValue());
    }
}
