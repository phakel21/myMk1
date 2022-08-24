package com.Rpg.validator.myCharacter.create.imlement;

import com.Rpg.config.exception.myCharacter.MyCharacterDontHaveHpException;
import com.Rpg.entity.MyCharacter;
import com.Rpg.validator.myCharacter.create.MyCharacterCreateValidator;
import org.springframework.stereotype.Component;

@Component
public class MyCharacterCreateHpValidator implements MyCharacterCreateValidator {
    @Override
    public void validate(MyCharacter myCharacter) {
        if(myCharacter.getHp() == null ){
            throw new MyCharacterDontHaveHpException("Dont have HP");

        }
    }
}
