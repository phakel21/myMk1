package com.Rpg.config.exception.location;

import com.Rpg.config.exception.DontHaveNameException;

public class LocationDontHaveNameException extends DontHaveNameException {
    public LocationDontHaveNameException(String message) {
        super(message);
    }

    public LocationDontHaveNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public LocationDontHaveNameException(Throwable cause) {
        super(cause);
    }
}
