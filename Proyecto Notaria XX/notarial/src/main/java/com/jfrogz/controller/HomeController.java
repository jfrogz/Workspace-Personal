package com.jfrogz.controller;

import com.jfrogz.identity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    @Qualifier("usuario")
    public Usuario pruebaUsuario;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", "Fernando Robles");
        return "hello";
    }

    @GetMapping("home2/{nombre}")
    public ModelAndView home2(@PathVariable("nombre")String nombre){
        ModelAndView andView = new ModelAndView("hello2");
        andView.addObject("title", nombre);
        return andView;
    }

    @GetMapping("home")
    public ModelAndView home3(){
        ModelAndView andView = new ModelAndView("hello2");
        andView.addObject("title", pruebaUsuario.getNombre());
        return andView;
    }
}
