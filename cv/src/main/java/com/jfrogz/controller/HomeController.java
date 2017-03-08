package com.jfrogz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class HomeController {

    @RequestMapping(value = "/home")
    public String home (){
        return "test2";
    }

/*
    @GetMapping(value = "/test2")
    public ModelAndView ejemploParametros(){
        ModelAndView andView = new ModelAndView("templates/test2");
        //andView.addObject("cadena", "Esta es mi cadena desde la vista");
        return andView;
    }*/
}
