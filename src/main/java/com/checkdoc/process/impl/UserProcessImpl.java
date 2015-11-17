package com.checkdoc.process.impl;

import com.checkdoc.exception.IncorrectPasswordException;
import com.checkdoc.domain.Role;
import com.checkdoc.domain.User;
import com.checkdoc.process.UserProcess;
import com.checkdoc.service.UserService;

/**
 * Created by Aexandere on 17-Nov-15.
 */
public class UserProcessImpl implements UserProcess {

    UserService userService;

    public UserProcessImpl(UserService userService) {
        this.userService=userService;
    }

    @Override
    public User register(String userName, String email, String password,String passwordConfirmation) {
        if(!password.equals(passwordConfirmation))
        {
            throw new IncorrectPasswordException("incorrectPassword");
        }
        User u =new User();
        u.setUserName(userName);
        u.setEmail(email);
        u.setPassword(password);
        u.setRole(new Role("user"));
        userService.add(u);
        return u;
    }

}
