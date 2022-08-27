package com.Rpg.config.exception.myUser;

public class PasswordDontMatchException extends RuntimeException {
    public PasswordDontMatchException(String message) {
        super(message);
    }

    public PasswordDontMatchException(Throwable cause) {
        super(cause);
    }
}
