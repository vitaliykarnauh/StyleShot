package com.styleshot.restcontroller;

import com.styleshot.domain.User;
import com.styleshot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest/login")
public class LoginRestController {


    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/performlogin")
    public @ResponseBody User performLogin(@RequestParam("email") String email,
                      @RequestParam("password") String password) {
        User user = userService.login(email, password);
        if(user != null) {
            return user;
        }
        return null;
    }



    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public boolean registerUser(@RequestParam("display_name") String username,
                                @RequestParam("email") String email,
                                @RequestParam("password") String password,
                                @RequestParam("password_confirmation") String passwordConfirmation) {


        User registerUser = userService.register(username, email, password, passwordConfirmation);

        return false;
    }


}
