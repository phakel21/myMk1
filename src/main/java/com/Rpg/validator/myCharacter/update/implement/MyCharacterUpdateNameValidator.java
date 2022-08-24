package com.Rpg.validator.myCharacter.update.implement;

import com.Rpg.config.exception.myCharacter.MyCharacterDontHaveNameException;
import com.Rpg.entity.MyCharacter;
import com.Rpg.validator.myCharacter.update.MyCharacterUpdateValidator;
import org.springframework.stereotype.Component;

@Component
public class MyCharacterUpdateNameValidator implements MyCharacterUpdateValidator {

    @Override
    public void validate(MyCharacter myCharacter) {
        if(myCharacter.getName() == null || myCharacter.getName().trim().isEmpty()){
            throw new MyCharacterDontHaveNameException("Dont have name");
        }
    }
}
