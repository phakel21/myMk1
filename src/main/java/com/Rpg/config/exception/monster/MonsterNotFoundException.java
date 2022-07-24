package com.Rpg.config.exception.monster;

import com.Rpg.config.exception.NotFoundException;

public class MonsterNotFoundException extends NotFoundException {

    public MonsterNotFoundException(String message) {
        super(message);
    }

    public MonsterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MonsterNotFoundException(Throwable cause) {
        super(cause);
    }
}
