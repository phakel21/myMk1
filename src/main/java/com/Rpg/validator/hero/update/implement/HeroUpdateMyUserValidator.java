package com.Rpg.validator.hero.update.implement;

import com.Rpg.dto.HeroDTO;
import com.Rpg.entity.Hero;
import com.Rpg.validator.hero.update.HeroUpdateValidator;
import org.springframework.stereotype.Component;

@Component
public class HeroUpdateMyUserValidator implements HeroUpdateValidator {

    @Override
    public void validate(Hero hero, HeroDTO updateHero) {
        if(updateHero.getMyUser() != null){
            hero.setMyUser(updateHero.getMyUser());
        }
    }
}
