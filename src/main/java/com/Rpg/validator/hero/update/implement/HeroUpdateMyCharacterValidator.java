package com.Rpg.validator.hero.update.implement;

import com.Rpg.dto.HeroDTO;
import com.Rpg.entity.Hero;
import com.Rpg.validator.hero.update.HeroUpdateValidator;
import org.springframework.stereotype.Component;

@Component
public class HeroUpdateMyCharacterValidator implements HeroUpdateValidator {

    @Override
    public void validate(Hero hero, HeroDTO updateHero) {
        if(updateHero.getMyCharacter() != null ){
            hero.setMyCharacter(updateHero.getMyCharacter());
        }
    }
}
