package com.Rpg.config.exception.monster;

import com.Rpg.config.exception.DontHaveLocationException;

public class MonsterDontHaveLocationException extends DontHaveLocationException {
    public MonsterDontHaveLocationException(String message) {
        super(message);
    }

    public MonsterDontHaveLocationException(String message, Throwable cause) {
        super(message, cause);
    }

    public MonsterDontHaveLocationException(Throwable cause) {
        super(cause);
    }
}
