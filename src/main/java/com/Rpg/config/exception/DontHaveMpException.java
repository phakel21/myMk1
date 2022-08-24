package com.Rpg.config.exception;

public class DontHaveMpException extends RuntimeException {
    public DontHaveMpException(String message) {
        super(message);
    }

    public DontHaveMpException(String message, Throwable cause) {
        super(message, cause);
    }

    public DontHaveMpException(Throwable cause) {
        super(cause);
    }
}
