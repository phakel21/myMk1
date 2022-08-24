package com.Rpg.validator.monster.create.implement;

import com.Rpg.config.exception.monster.MonsterDontHaveHpException;
import com.Rpg.entity.Monster;
import com.Rpg.validator.monster.create.MonsterCreateValidator;
import org.springframework.stereotype.Component;

@Component
public class MonsterCreateHpValidator implements MonsterCreateValidator {

    @Override
    public void validate(Monster monster) {
     if(monster.getHp() == null){
         throw new MonsterDontHaveHpException("Not have HP");
     }
    }
}
