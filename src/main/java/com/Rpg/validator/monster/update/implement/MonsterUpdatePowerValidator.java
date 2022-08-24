package com.Rpg.validator.monster.update.implement;

import com.Rpg.config.exception.monster.MonsterDontHavePowerException;
import com.Rpg.entity.Monster;
import com.Rpg.validator.monster.update.MonsterUpdateValidator;
import org.springframework.stereotype.Component;

@Component
public class MonsterUpdatePowerValidator implements MonsterUpdateValidator {
    @Override
    public void validate(Monster monster) {
        if (monster.getPower() == null) {
            throw new MonsterDontHavePowerException("Dont have power");
        }
    }
}
