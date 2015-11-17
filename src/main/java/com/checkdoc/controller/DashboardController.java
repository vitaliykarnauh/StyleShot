package com.checkdoc.controller;


import com.checkdoc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @Autowired
    private UserService userService;

    @RequestMapping(value ={"/", "/dashboard"})
    public String redirectToDashboard(Model model) {
        String hello = "hello";
        model.addAttribute("hello", hello);
        return "index";
    }
}
