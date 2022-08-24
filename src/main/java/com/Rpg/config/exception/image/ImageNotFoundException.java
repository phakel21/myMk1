package com.Rpg.config.exception.image;

import com.Rpg.config.exception.NotFoundException;

public class ImageNotFoundException extends NotFoundException {

    public ImageNotFoundException(String message) {
        super(message);
    }

    public ImageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageNotFoundException(Throwable cause) {
        super(cause);
    }
}
