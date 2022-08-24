package com.Rpg.validator.hero.create.implement;

import com.Rpg.config.exception.hero.HeroDontHaveMyUserException;
import com.Rpg.entity.Hero;
import com.Rpg.validator.hero.create.HeroCreateValidator;
import org.springframework.stereotype.Component;

@Component
public class HeroHasMyUserValidator implements HeroCreateValidator {

    @Override
    public void validate(Hero hero) {

        if (hero.getMyUser() == null){
            throw new HeroDontHaveMyUserException("Cant create hero without user");
        }
    }
}
