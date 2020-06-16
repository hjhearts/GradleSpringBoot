package com.mygradle.commons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String hello(Model model){
        model.addAttribute("message", "hello spring");
        return "th";
    }
}
