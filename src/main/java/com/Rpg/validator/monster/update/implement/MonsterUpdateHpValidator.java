package com.Rpg.validator.monster.update.implement;

import com.Rpg.config.exception.monster.MonsterDontHaveHpException;
import com.Rpg.entity.Monster;
import com.Rpg.validator.monster.update.MonsterUpdateValidator;
import org.springframework.stereotype.Component;

@Component
public class MonsterUpdateHpValidator implements MonsterUpdateValidator {

    @Override
    public void validate(Monster monster) {
     if(monster.getHp() == null){
         throw new MonsterDontHaveHpException("Not have HP");
     }
    }
}
