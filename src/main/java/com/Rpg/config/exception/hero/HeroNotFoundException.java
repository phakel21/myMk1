package com.Rpg.config.exception.hero;

import com.Rpg.config.exception.NotFoundException;

public class HeroNotFoundException extends NotFoundException {
    public HeroNotFoundException(String message) {
        super(message);
    }

    public HeroNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public HeroNotFoundException(Throwable cause) {
        super(cause);
    }
}
