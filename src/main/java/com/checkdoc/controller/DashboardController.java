package com.checkdoc.controller;


import com.checkdoc.domain.User;
import com.checkdoc.exception.IncorrectPasswordException;
import com.checkdoc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DashboardController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/dashboard"})
    public String redirectToDashboard(Model model) {
        String hello = "hello";
        model.addAttribute("hello", hello);
        return "index";
    }

    @RequestMapping(value = {"/signup"})
    public String showRegistrationPage(Model model) {
        return "register";
    }

    @RequestMapping(value = {"/register"}, method= RequestMethod.GET)
    public String registerView(){
        return "register";
    }

    @RequestMapping(value = {"/register"}, method= RequestMethod.POST)
    public String registerNewUser(@RequestParam("display_name") String username,
                                  @RequestParam("email") String email,
                                  @RequestParam("password") String password,
                                  @RequestParam("password_confirmation") String passwordConfirmation,
                                  Model model) {
        try {
            userService.register(username, email, password, passwordConfirmation);
        } catch (IncorrectPasswordException e) {
            return "register";
        }
        return "dashboard";
    }

    @RequestMapping(value = {"/login"})
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        User user = userService.login(email, password);
        if (user == null) {
            return "dashboard";
        }
        model.addAttribute("user",user);
        return "mypage";
    }
}
