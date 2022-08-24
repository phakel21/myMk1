package com.Rpg.config.exception;

public class DontHaveHpException extends RuntimeException {
    public DontHaveHpException(String message) {
        super(message);
    }

    public DontHaveHpException(String message, Throwable cause) {
        super(message, cause);
    }

    public DontHaveHpException(Throwable cause) {
        super(cause);
    }
}
