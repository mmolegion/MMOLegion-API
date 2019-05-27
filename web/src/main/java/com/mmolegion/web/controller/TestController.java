package com.mmolegion.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

    @GetMapping("/test")
    public ModelAndView testPage() {
        Map<String,String> model = new HashMap<>();
        model.put("statement", "Test works.");

        return new ModelAndView("test", "map", model);
    }
}
