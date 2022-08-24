package com.Rpg.config.exception;

public class DontHaveLocationException extends RuntimeException {
    public DontHaveLocationException(String message) {
        super(message);
    }

    public DontHaveLocationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DontHaveLocationException(Throwable cause) {
        super(cause);
    }
}
