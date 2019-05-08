package io.github.mylyed.lessdoc.config;

import freemarker.template.TemplateModelException;
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

    @PostConstruct
    public void setConfigure() {
        optionService.allOptions().forEach((k, v) -> {
            try {
                configuration.setSharedVariable(k, v);
            } catch (TemplateModelException e) {
                e.printStackTrace();
            }
        });
    }

}
