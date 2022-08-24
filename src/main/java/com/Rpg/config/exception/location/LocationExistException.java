package com.Rpg.config.exception.location;

import com.Rpg.config.exception.ExistException;

public class LocationExistException extends ExistException {

    public LocationExistException(String message) {
        super(message);
    }

    public LocationExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public LocationExistException(Throwable cause) {
        super(cause);
    }
}
