package com.Rpg.validator.hero.update.implement;

import com.Rpg.config.exception.hero.HeroDontHaveMyCharacterException;
import com.Rpg.entity.Hero;
import com.Rpg.validator.hero.update.HeroUpdateValidator;
import org.springframework.stereotype.Component;

@Component
public class HeroUpdateMyCharacterValidator implements HeroUpdateValidator {

    @Override
    public void validate(Hero hero) {

        if (hero.getMyCharacter() == null){
            throw new HeroDontHaveMyCharacterException("Hero don't have character");
        }
    }
}
