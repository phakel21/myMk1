package com.Rpg.config.exception.myCharacter;

import com.Rpg.config.exception.NotFoundException;

public class MyCharacterNotFoundException extends NotFoundException {
    public MyCharacterNotFoundException(String message) {
        super(message);
    }

    public MyCharacterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyCharacterNotFoundException(Throwable cause) {
        super(cause);
    }
}
