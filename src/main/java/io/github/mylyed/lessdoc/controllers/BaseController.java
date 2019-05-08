package io.github.mylyed.lessdoc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author lilei
 * created at 2019/4/30
 */
public class BaseController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @ModelAttribute
    public void controllerName(Model model) {
        String controllerName = this.getClass().getSimpleName();
        log.debug("controllerName={}", controllerName);
        model.addAttribute("ControllerName", controllerName);
    }
}
