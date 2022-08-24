package com.Rpg.validator.monster.create.implement;

import com.Rpg.config.exception.monster.MonsterDontHavePowerException;
import com.Rpg.entity.Monster;
import com.Rpg.validator.monster.create.MonsterCreateValidator;
import org.springframework.stereotype.Component;

@Component
public class MonsterCreatePowerValidator implements MonsterCreateValidator {
    @Override
    public void validate(Monster monster) {
        if (monster.getPower() == null) {
            throw new MonsterDontHavePowerException("Dont have power");
        }
    }
}
