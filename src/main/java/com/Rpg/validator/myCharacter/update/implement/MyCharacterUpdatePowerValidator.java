package com.Rpg.validator.myCharacter.update.implement;

import com.Rpg.config.exception.myCharacter.MyCharacterDontHavePowerException;
import com.Rpg.entity.MyCharacter;
import com.Rpg.validator.myCharacter.create.MyCharacterCreateValidator;
import com.Rpg.validator.myCharacter.update.MyCharacterUpdateValidator;
import org.springframework.stereotype.Component;

@Component
public class MyCharacterUpdatePowerValidator implements MyCharacterUpdateValidator {
    @Override
    public void validate(MyCharacter myCharacter) {
        if (myCharacter.getPower() == null) {
            throw new MyCharacterDontHavePowerException("Dont have power");
        }
    }
}
