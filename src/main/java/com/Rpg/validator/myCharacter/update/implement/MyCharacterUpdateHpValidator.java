package com.Rpg.validator.myCharacter.update.implement;

import com.Rpg.config.exception.myCharacter.MyCharacterDontHaveHpException;
import com.Rpg.entity.MyCharacter;
import com.Rpg.validator.myCharacter.create.MyCharacterCreateValidator;
import com.Rpg.validator.myCharacter.update.MyCharacterUpdateValidator;
import org.springframework.stereotype.Component;

@Component
public class MyCharacterUpdateHpValidator implements MyCharacterUpdateValidator {
    @Override
    public void validate(MyCharacter myCharacter) {
        if(myCharacter.getHp() == null ){
            throw new MyCharacterDontHaveHpException("Dont have HP");

        }
    }
}
