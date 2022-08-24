package com.Rpg.config.exception.myCharacter;

import com.Rpg.config.exception.DontHaveMpException;

public class MyCharacterDontHaveMpException extends DontHaveMpException {
    public MyCharacterDontHaveMpException(String message) {
        super(message);
    }

    public MyCharacterDontHaveMpException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyCharacterDontHaveMpException(Throwable cause) {
        super(cause);
    }
}
