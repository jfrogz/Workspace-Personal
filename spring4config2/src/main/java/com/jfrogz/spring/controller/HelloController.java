package com.jfrogz.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String inicio (Model model){
        return "index";
    }


    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "Fernando Robles");
        return "welcome";
    }
}