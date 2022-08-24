package com.Rpg.config.exception.monster;

import com.Rpg.config.exception.DontHaveMpException;

public class MonsterDontHaveMpException extends DontHaveMpException {
    public MonsterDontHaveMpException(String message) {
        super(message);
    }

    public MonsterDontHaveMpException(String message, Throwable cause) {
        super(message, cause);
    }

    public MonsterDontHaveMpException(Throwable cause) {
        super(cause);
    }
}
