package com.Rpg.config.exception;

public class DontHaveNameException extends RuntimeException {
    public DontHaveNameException(String message) {
        super(message);
    }

    public DontHaveNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DontHaveNameException(Throwable cause) {
        super(cause);
    }
}
