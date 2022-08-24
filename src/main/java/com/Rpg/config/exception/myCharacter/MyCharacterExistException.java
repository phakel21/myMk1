package com.Rpg.config.exception.myCharacter;

import com.Rpg.config.exception.ExistException;

public class MyCharacterExistException extends ExistException {
    public MyCharacterExistException(String message) {
        super(message);
    }

    public MyCharacterExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyCharacterExistException(Throwable cause) {
        super(cause);
    }
}
