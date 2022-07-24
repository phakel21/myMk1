package com.Rpg.config.exception.myUser;

import com.Rpg.config.exception.NotFoundException;

public class MyUserNotFoundException extends NotFoundException {

    public MyUserNotFoundException(String message) {
        super(message);
    }

    public MyUserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyUserNotFoundException(Throwable cause) {
        super(cause);
    }
}
