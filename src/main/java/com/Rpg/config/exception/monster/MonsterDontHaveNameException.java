package com.Rpg.config.exception.monster;

import com.Rpg.config.exception.DontHaveNameException;

public class MonsterDontHaveNameException extends DontHaveNameException {
    public MonsterDontHaveNameException(String message) {
        super(message);
    }

    public MonsterDontHaveNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public MonsterDontHaveNameException(Throwable cause) {
        super(cause);
    }
}
