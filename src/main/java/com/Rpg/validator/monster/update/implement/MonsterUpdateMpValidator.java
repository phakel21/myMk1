package com.Rpg.validator.monster.update.implement;

import com.Rpg.config.exception.monster.MonsterDontHaveMpException;
import com.Rpg.entity.Monster;
import com.Rpg.validator.monster.update.MonsterUpdateValidator;
import org.springframework.stereotype.Component;

@Component
public class MonsterUpdateMpValidator implements MonsterUpdateValidator {
    @Override
    public void validate(Monster monster) {
        if(monster.getMp() == null){
            throw new MonsterDontHaveMpException("Dont have MP");
        }
    }
}
