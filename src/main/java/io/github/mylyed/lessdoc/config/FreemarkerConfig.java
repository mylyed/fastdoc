package io.github.mylyed.lessdoc.config;

import freemarker.template.TemplateModelException;
import io.github.mylyed.lessdoc.ext.freemarker.MemberNameMethod;
import io.github.mylyed.lessdoc.service.OptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author lilei
 * created at 2019/4/30
 */
@Configuration
public class FreemarkerConfig {


    static Logger log = LoggerFactory.getLogger(FreemarkerConfig.class);

    @Autowired
    private freemarker.template.Configuration configuration;


//    @Autowired
//    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Resource
    private OptionService optionService;


    @Resource
    MemberNameMethod memberNameMethod;


    @PostConstruct
    public void setConfigure() throws TemplateModelException {
        configuration.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
        configuration.setDateFormat("yyyy-MM-dd HH:mm:ss");
        configuration.setTimeFormat("HH:mm:ss");

        configuration.setSharedVariable("LOGO", "LessDoc");
        configuration.setSharedVariable("HOME_PAGE", "http://mylyed.github.io");

        optionService.allOptions().forEach((k, v) -> {
            try {
                configuration.setSharedVariable(k, v);
            } catch (TemplateModelException e) {
                e.printStackTrace();
            }
        });


        configuration.setSharedVariable("name", memberNameMethod);
    }

}
