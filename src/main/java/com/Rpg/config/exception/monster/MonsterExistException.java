package com.Rpg.config.exception.monster;

import com.Rpg.config.exception.ExistException;

public class MonsterExistException extends ExistException {
    public MonsterExistException(String message) {
        super(message);
    }

    public MonsterExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public MonsterExistException(Throwable cause) {
        super(cause);
    }
}
