package com.Rpg.config.exception.myUser;

public class MyUserDontHaveThisHeroException extends RuntimeException {
    public MyUserDontHaveThisHeroException(String message) {
        super(message);
    }

    public MyUserDontHaveThisHeroException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyUserDontHaveThisHeroException(Throwable cause) {
        super(cause);
    }
}
