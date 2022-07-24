package com.Rpg.config.exception.hero;

public class HeroDontHaveMyCharacterException extends RuntimeException {
    public HeroDontHaveMyCharacterException(String message) {
        super(message);
        }

    public HeroDontHaveMyCharacterException(Throwable cause) {
        super(cause);
    }
}
