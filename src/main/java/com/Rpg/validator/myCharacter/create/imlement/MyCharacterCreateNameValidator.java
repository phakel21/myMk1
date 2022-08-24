package com.Rpg.validator.myCharacter.create.imlement;

import com.Rpg.config.exception.myCharacter.MyCharacterDontHaveNameException;
import com.Rpg.entity.MyCharacter;
import com.Rpg.validator.myCharacter.create.MyCharacterCreateValidator;
import org.springframework.stereotype.Component;

@Component
public class MyCharacterCreateNameValidator implements MyCharacterCreateValidator {

    @Override
    public void validate(MyCharacter myCharacter) {

        if(myCharacter.getName() == null || myCharacter.getName().trim().isEmpty() ){
            throw new MyCharacterDontHaveNameException("Dont have name");
        }
    }
}
