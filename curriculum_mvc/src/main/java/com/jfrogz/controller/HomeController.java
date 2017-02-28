package com.jfrogz.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    @GetMapping("/curriculum")
    public ModelAndView curriculum (){
            logger.info("Comienza la solicitud del curriculum");
            ModelAndView andView = new ModelAndView("curriculum");
            andView.addObject("nombre", "Fernando Robles Guzmán");
            andView.addObject("carrera", "Ing. Ciencias de la Computación");
       return andView;
    }

    @GetMapping("/home")
    public ModelAndView toHome (){
        logger.info("Comienza la solicitud del curriculum");
        ModelAndView andView = new ModelAndView("home");
        andView.addObject("nombre", "fernando");
        return andView;
    }
}
