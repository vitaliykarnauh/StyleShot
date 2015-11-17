package com.checkdoc.process;

import com.checkdoc.domain.User;
import com.checkdoc.exception.IncorrectPasswordException;

public interface UserProcess {
    /**
     * This method allows to register new User with specified parameters.
     * Use it to register new User in system.
     *
     * @param userName user name
     * @param email user email
     * @param password password of user
     *
     * @throws IncorrectPasswordException if password not equal to password_confirmation
     */
        public User register(String userName,String email,String password,String passwordConfirmation);

}
