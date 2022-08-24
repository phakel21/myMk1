package com.Rpg.config.exception.myCharacter;

import com.Rpg.config.exception.DontHaveNameException;

public class MyCharacterDontHaveNameException extends DontHaveNameException {

    public MyCharacterDontHaveNameException(String message) {
        super(message);
    }

    public MyCharacterDontHaveNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyCharacterDontHaveNameException(Throwable cause) {
        super(cause);
    }
}
