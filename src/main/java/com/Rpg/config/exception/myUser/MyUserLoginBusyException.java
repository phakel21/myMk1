package com.Rpg.config.exception.myUser;

public class MyUserLoginBusyException extends RuntimeException {
    public MyUserLoginBusyException(String message) {
        super(message);
    }

    public MyUserLoginBusyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyUserLoginBusyException(Throwable cause) {
        super(cause);
    }
}
