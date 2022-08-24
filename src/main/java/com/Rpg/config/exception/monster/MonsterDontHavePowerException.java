package com.Rpg.config.exception.monster;

import com.Rpg.config.exception.DontHavePowerException;

public class MonsterDontHavePowerException extends DontHavePowerException {
    public MonsterDontHavePowerException(String message) {
        super(message);
    }

    public MonsterDontHavePowerException(String message, Throwable cause) {
        super(message, cause);
    }

    public MonsterDontHavePowerException(Throwable cause) {
        super(cause);
    }
}
