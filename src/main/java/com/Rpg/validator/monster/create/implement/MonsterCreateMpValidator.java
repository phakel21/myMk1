package com.Rpg.validator.monster.create.implement;

import com.Rpg.config.exception.monster.MonsterDontHaveMpException;
import com.Rpg.entity.Monster;
import com.Rpg.validator.monster.create.MonsterCreateValidator;
import org.springframework.stereotype.Component;

@Component
public class MonsterCreateMpValidator implements MonsterCreateValidator {
    @Override
    public void validate(Monster monster) {
        if(monster.getMp() == null){
            throw new MonsterDontHaveMpException("Dont have MP");
        }
    }
}
