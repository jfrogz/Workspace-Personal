package com.jfrogz.controller;

import com.jfrogz.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/example")
public class ExampleController {

    public static final String EXAMPLE_VIEW = "example";
    //primera forma
    //@RequestMapping(value = "/exampleString", method = RequestMethod.GET)
    @GetMapping("/exampleString")
    public String exampleString(Model model) {
        model.addAttribute("people", getPeople());
        return EXAMPLE_VIEW;
    }

    //Segunda forma
    @GetMapping("/exampleMAV")
    public ModelAndView exampleMAV(){
        ModelAndView andView = new ModelAndView(EXAMPLE_VIEW);
        andView.addObject("people", getPeople());
        return andView;
    }

    private List<Person> getPeople (){
        List<Person> people = new ArrayList<>();
        people.add(new Person("Adair", 11));
        people.add(new Person("Jennifer", 9));
        people.add(new Person("Aiko", 3));
        people.add(new Person("Ian", 0));
        return people;
    }
}
