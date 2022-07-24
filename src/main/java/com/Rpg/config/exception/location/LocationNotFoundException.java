package com.Rpg.config.exception.location;

import com.Rpg.config.exception.NotFoundException;

public class LocationNotFoundException extends NotFoundException {

    public LocationNotFoundException(String message) {
        super(message);
    }

    public LocationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public LocationNotFoundException(Throwable cause) {
        super(cause);
    }
}
