package com.Rpg.validator.myCharacter.create.imlement;

import com.Rpg.config.exception.myCharacter.MyCharacterDontHaveMpException;
import com.Rpg.entity.MyCharacter;
import com.Rpg.validator.myCharacter.create.MyCharacterCreateValidator;
import org.springframework.stereotype.Component;

@Component
public class MyCharacterCreateMpValidator implements MyCharacterCreateValidator {
    @Override
    public void validate(MyCharacter myCharacter) {
        if(myCharacter.getMp() == null){
            throw new MyCharacterDontHaveMpException("Dont have MP");
        }
    }
}
