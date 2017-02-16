package com.jfrogz.controller;

import com.jfrogz.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/example")
public class ExampleController {

    public static final String EXAMPLE_VIEW = "example";
    //primera forma
    //@RequestMapping(value = "/exampleString", method = RequestMethod.GET)
    @GetMapping("/exampleString")
    public String exampleString(Model model) {
        model.addAttribute("person", new Person("Adair", 11));
        return EXAMPLE_VIEW;
    }

    //Segunda forma
    @GetMapping("/exampleMAV")
    public ModelAndView exampleMAV(){
        ModelAndView andView = new ModelAndView(EXAMPLE_VIEW);
        andView.addObject("person", new Person("Jennifer", 9));
        return andView;
    }
}
