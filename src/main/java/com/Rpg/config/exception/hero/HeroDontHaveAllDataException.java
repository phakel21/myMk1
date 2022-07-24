package com.Rpg.config.exception.hero;

public class HeroDontHaveAllDataException extends RuntimeException {
    public HeroDontHaveAllDataException(String message) {
        super(message);
    }

    public HeroDontHaveAllDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public HeroDontHaveAllDataException(Throwable cause) {
        super(cause);
    }
}
