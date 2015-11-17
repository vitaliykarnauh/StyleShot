package com.checkdoc.exception;

/**
 * Custom exception for identifying that user entered incorrect confirmation of password.
 */
public class IncorrectPasswordException extends RuntimeException {

    public IncorrectPasswordException(String message) {
        super(message);
    }

}
