package com.Rpg.config.exception.hero;

import com.Rpg.config.exception.ExistException;

public class HeroExistException extends ExistException {
    public HeroExistException(String message) {
        super(message);
    }

    public HeroExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public HeroExistException(Throwable cause) {
        super(cause);
    }
}
