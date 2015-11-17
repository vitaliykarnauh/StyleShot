package com.checkdoc.controller;


import com.checkdoc.exception.IncorrectPasswordException;
import com.checkdoc.process.UserProcess;
import com.checkdoc.process.impl.UserProcessImpl;
import com.checkdoc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = {"/signup"})
        public String showRegistrationPage(Model model) {
            return "register";
    }

    @RequestMapping(value = {"/register"})
        public String registerNewUser(@RequestParam("display_name") String username, @RequestParam("email") String email,@RequestParam("password") String password,@RequestParam("password_confirmation") String passwordConfirmation, Model model) {
        UserProcess userProcess=new UserProcessImpl(userService);
        try {
            userProcess.register(username, email, password,passwordConfirmation);
        }
        catch (IncorrectPasswordException e)
        {
            return "register";
        }
        return "myAccount";
    }
}
