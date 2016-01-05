package com.styleshot.controller;


import com.styleshot.constant.ControllerConstants;
import com.styleshot.constant.RoleEnum;
import com.styleshot.domain.User;
import com.styleshot.exception.IncorrectPasswordException;
import com.styleshot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DashboardController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/dashboard"}, method = RequestMethod.GET)
    public String redirectToDashboard(Model model) {
        String hello = "hello";
        model.addAttribute("hello", hello);
        return ControllerConstants.Pages.INDEX;
    }

    @RequestMapping(value = {"/signup"})
    public String showRegistrationPage(Model model) {
        return "register";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String registerView() {
        return "register";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String registerNewUser(@RequestParam("display_name") String username,
                                  @RequestParam("email") String email,
                                  @RequestParam("password") String password,
                                  @RequestParam("password_confirmation") String passwordConfirmation,
                                  Model model) {
        try {
            User registerUser = userService.register(username, email, password, passwordConfirmation);
            model.addAttribute("user", registerUser);
        } catch (IncorrectPasswordException e) {
            return ControllerConstants.Pages.REGISTER;
        }
        return ControllerConstants.Pages.USER_PAGE;
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model, RedirectAttributes redirectAttributes) {

        User user = userService.login(email, password);
        if (user == null) {
            return ControllerConstants.Pages.INDEX;
        }

        if (user.getRole().getName().equalsIgnoreCase(RoleEnum.USER.getRoleName())) {
            model.addAttribute("user", user);
            return ControllerConstants.Pages.USER_PAGE;
        }
        redirectAttributes.addFlashAttribute("user", user);
        return ControllerConstants.Pages.REDIRECT_EXPERT_PAGE;
    }
}
