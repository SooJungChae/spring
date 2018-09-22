package com.soocompany.application.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getMainPage() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "test? 되나?");
        mv.setViewName("main/main");
        return mv;
    }
}
