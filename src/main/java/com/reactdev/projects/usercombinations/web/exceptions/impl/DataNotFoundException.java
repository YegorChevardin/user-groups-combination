package com.reactdev.projects.usercombinations.web.exceptions.impl;

import com.reactdev.projects.usercombinations.web.exceptions.CustomException;

public class DataNotFoundException extends CustomException {

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
