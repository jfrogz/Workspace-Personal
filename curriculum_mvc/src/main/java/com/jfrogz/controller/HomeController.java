package com.jfrogz.controller;

import com.jfrogz.Indentity.Generales;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class HomeController {

    public static Log logger = LogFactory.getLog(HomeController.class);
    @Autowired(required = true)
    private Generales datosGenerales;

    @GetMapping("/cv")
    public ModelAndView curriculum() {
        logger.info("Comienza la solicitud del curriculum");
        ModelAndView andView = new ModelAndView("curriculum");
        andView.addObject("datosGen", datosGenerales);
        return andView;
    }

    @GetMapping("/home")
    public ModelAndView toHome() {
        logger.info("Comienza la solicitud del curriculum");
        ModelAndView andView = new ModelAndView("home");
        andView.addObject("nombre", "fernando");
        return andView;
    }
}
