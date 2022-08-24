package com.Rpg.validator.monster.update.implement;

import com.Rpg.config.exception.monster.MonsterDontHaveLocationException;
import com.Rpg.entity.Monster;
import com.Rpg.validator.monster.create.MonsterCreateValidator;
import com.Rpg.validator.monster.update.MonsterUpdateValidator;
import org.springframework.stereotype.Component;

@Component
public class MonsterUpdateLocationValidator implements MonsterUpdateValidator {

    @Override
    public void validate(Monster monster) {
        if (monster.getLocation() == null){
            throw new MonsterDontHaveLocationException("Don't have location");
        }
    }
}
