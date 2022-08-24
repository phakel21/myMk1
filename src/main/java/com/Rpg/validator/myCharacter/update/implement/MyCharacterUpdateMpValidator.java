package com.Rpg.validator.myCharacter.update.implement;

import com.Rpg.config.exception.myCharacter.MyCharacterDontHaveMpException;
import com.Rpg.entity.MyCharacter;
import com.Rpg.validator.myCharacter.create.MyCharacterCreateValidator;
import com.Rpg.validator.myCharacter.update.MyCharacterUpdateValidator;
import org.springframework.stereotype.Component;

@Component
public class MyCharacterUpdateMpValidator implements MyCharacterUpdateValidator {
    @Override
    public void validate(MyCharacter myCharacter) {
        if(myCharacter.getMp() == null){
            throw new MyCharacterDontHaveMpException("Dont have MP");
        }
    }
}
