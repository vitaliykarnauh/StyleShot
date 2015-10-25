package com.checkdoc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {


    @RequestMapping(value = {"/", "/dashboard"})
    public String redirectToDashboard(Model model) {
        String hello = "hello";
        model.addAttribute("hello", hello);
        return "dashboard";
    }
}
