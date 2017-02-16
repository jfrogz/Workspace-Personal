package com.jfrogz.controller;

import com.jfrogz.model.Person;
import com.sun.javafx.sg.prism.NGShape;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/example3")
public class Example3Controller {
    private static final Log logger = LogFactory.getLog(Example3Controller.class);

    public static final String FORM_VIEW = "form";
    public static final String RESULT_VIEW = "result";

    //forma1

    @GetMapping("/")
    public String redirect() {
        return "redirect:/example3/showform";
    }

    @GetMapping("")
    public RedirectView redirectView() {
        return new RedirectView("/example3/showform");
    }

    @GetMapping("/showform")
    public String showForm(Model model) {
        logger.info("INFO TRACE");
        logger.warn("WARNING TRACE");
        logger.error("ERROR TRACE");
        logger.debug("DEBUG TRACE");
        model.addAttribute("person", new Person());
        return FORM_VIEW;
    }

    @PostMapping("/addperson")
    public ModelAndView addPerson(@ModelAttribute("person") Person person) {
        logger.info("METHOD: 'addPerson' -- PARAMS: '" + person);
        ModelAndView andView = new ModelAndView(RESULT_VIEW);
        andView.addObject("person", person);
        return andView;
    }


}
