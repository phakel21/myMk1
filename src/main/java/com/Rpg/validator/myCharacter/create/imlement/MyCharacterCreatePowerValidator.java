package com.Rpg.validator.myCharacter.create.imlement;

import com.Rpg.config.exception.myCharacter.MyCharacterDontHavePowerException;
import com.Rpg.entity.MyCharacter;
import com.Rpg.validator.myCharacter.create.MyCharacterCreateValidator;
import org.springframework.stereotype.Component;

@Component
public class MyCharacterCreatePowerValidator implements MyCharacterCreateValidator {
    @Override
    public void validate(MyCharacter myCharacter) {
        if (myCharacter.getPower() == null) {
            throw new MyCharacterDontHavePowerException("Dont have power");
        }
    }
}
