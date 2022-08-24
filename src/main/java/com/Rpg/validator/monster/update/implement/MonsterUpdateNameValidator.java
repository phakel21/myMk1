package com.Rpg.validator.monster.update.implement;

import com.Rpg.config.exception.DontHaveNameException;
import com.Rpg.entity.Monster;
import com.Rpg.validator.monster.update.MonsterUpdateValidator;
import org.springframework.stereotype.Component;

@Component
public class MonsterUpdateNameValidator implements MonsterUpdateValidator {

    @Override
    public void validate(Monster monster) {
         if(monster.getName() == null || monster.getName().trim().isEmpty()){
             throw new DontHaveNameException("Don't have name");
         }

         if(monster.getName().contains("@")){
             throw new DontHaveNameException("Not include @");
         }
    }
}
