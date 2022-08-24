package com.Rpg.config.exception;

public class DontHavePowerException extends RuntimeException {
    public DontHavePowerException(String message) {
        super(message);
    }

    public DontHavePowerException(String message, Throwable cause) {
        super(message, cause);
    }

    public DontHavePowerException(Throwable cause) {
        super(cause);
    }
}
