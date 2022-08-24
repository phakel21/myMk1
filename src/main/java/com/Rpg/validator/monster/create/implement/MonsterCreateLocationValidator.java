package com.Rpg.validator.monster.create.implement;

import com.Rpg.config.exception.monster.MonsterDontHaveLocationException;
import com.Rpg.entity.Monster;
import com.Rpg.validator.monster.create.MonsterCreateValidator;
import org.springframework.stereotype.Component;

@Component
public class MonsterCreateLocationValidator implements MonsterCreateValidator {

    @Override
    public void validate(Monster monster) {
        if (monster.getLocation() == null){
            throw new MonsterDontHaveLocationException("Don't have location");
        }
    }
}
