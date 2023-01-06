package com.Rpg.config.exception;

public class YouWinException extends RuntimeException {
    public YouWinException(String message) {
        super(message);
    }

    public YouWinException(String message, Throwable cause) {
        super(message, cause);
    }

    public YouWinException(Throwable cause) {
        super(cause);
    }
}
