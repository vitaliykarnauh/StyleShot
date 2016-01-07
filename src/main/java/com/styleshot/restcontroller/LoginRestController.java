package com.styleshot.restcontroller;

import com.styleshot.constant.RoleEnum;
import com.styleshot.domain.User;
import com.styleshot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/rest/login")
public class LoginRestController {


    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/performlogin")
    public @ResponseBody User performLogin(@RequestParam("email") String email,
                      @RequestParam("password") String password) {
        User user = userService.login(email, password);

        if (user == null || user.getRole().getName().equalsIgnoreCase(RoleEnum.EXPERT.getRoleName())) {
            return null;
        }

        return user;
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public @ResponseBody User registerUser(@RequestParam("display_name") String username,
                             @RequestParam("email") String email,
                             @RequestParam("password") String password,
                             @RequestParam("password_confirmation") String passwordConfirmation) {

        User registerUser = userService.register(username, email, password, passwordConfirmation);

        return registerUser;
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public User findUserById(@PathVariable String userId) {
        User user = userService.findUserById(Long.valueOf(userId));

        user.setUserLinks(user.getUserLinks().stream().filter(a -> a.isViewed()).collect(Collectors.toList()));

        return user;
    }


}
