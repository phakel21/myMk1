package com.Rpg.config.exception.monster;

import com.Rpg.config.exception.DontHaveHpException;

public class MonsterDontHaveHpException extends DontHaveHpException {
    public MonsterDontHaveHpException(String message) {
        super(message);
    }

    public MonsterDontHaveHpException(String message, Throwable cause) {
        super(message, cause);
    }

    public MonsterDontHaveHpException(Throwable cause) {
        super(cause);
    }
}
