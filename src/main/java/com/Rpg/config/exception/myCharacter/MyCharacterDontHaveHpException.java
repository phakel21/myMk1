package com.Rpg.config.exception.myCharacter;

import com.Rpg.config.exception.DontHaveHpException;

public class MyCharacterDontHaveHpException extends DontHaveHpException {
    public MyCharacterDontHaveHpException(String message) {
        super(message);
    }

    public MyCharacterDontHaveHpException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyCharacterDontHaveHpException(Throwable cause) {
        super(cause);
    }
}
