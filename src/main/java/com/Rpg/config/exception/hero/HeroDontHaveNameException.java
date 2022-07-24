package com.Rpg.config.exception.hero;

import com.Rpg.config.exception.DontHaveNameException;

public class HeroDontHaveNameException extends DontHaveNameException {
    public HeroDontHaveNameException(String message) {
        super(message);
    }

    public HeroDontHaveNameException(Throwable cause) {
        super(cause);
    }
}
