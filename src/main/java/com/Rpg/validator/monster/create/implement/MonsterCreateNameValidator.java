package com.Rpg.validator.monster.create.implement;

import com.Rpg.config.exception.monster.MonsterDontHaveNameException;
import com.Rpg.entity.Monster;
import com.Rpg.validator.monster.create.MonsterCreateValidator;
import org.springframework.stereotype.Component;

@Component
public class MonsterCreateNameValidator implements MonsterCreateValidator {

    @Override
    public void validate(Monster monster) {
         if(monster.getName() == null || monster.getName().trim().isEmpty()){
             throw new MonsterDontHaveNameException("Don't have name");
         }

         if(monster.getName().contains("@")){
             throw new MonsterDontHaveNameException("Not include @");
         }
    }
}
