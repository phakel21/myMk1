package com.Rpg.config.exception.myCharacter;

import com.Rpg.config.exception.DontHavePowerException;

public class MyCharacterDontHavePowerException extends DontHavePowerException {
    public MyCharacterDontHavePowerException(String message) {
        super(message);
    }

    public MyCharacterDontHavePowerException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyCharacterDontHavePowerException(Throwable cause) {
        super(cause);
    }
}
