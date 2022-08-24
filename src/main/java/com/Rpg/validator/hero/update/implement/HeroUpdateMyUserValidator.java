package com.Rpg.validator.hero.update.implement;


import com.Rpg.config.exception.hero.HeroDontHaveMyUserException;
import com.Rpg.entity.Hero;
import com.Rpg.validator.hero.update.HeroUpdateValidator;
import org.springframework.stereotype.Component;

@Component
public class HeroUpdateMyUserValidator implements HeroUpdateValidator {

    @Override
    public void validate(Hero hero) {
        if (hero.getMyUser() == null) {
            throw new HeroDontHaveMyUserException("Hero don't have user");
        }
    }
}
