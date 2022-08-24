package com.Rpg.validator.hero.update.implement;

import com.Rpg.config.exception.hero.HeroDontHaveNameException;
import com.Rpg.entity.Hero;
import com.Rpg.validator.hero.update.HeroUpdateValidator;
import org.springframework.stereotype.Component;

@Component
public class HeroUpdateNameValidator implements HeroUpdateValidator {

    @Override
    public void validate(Hero hero) {
        if (hero.getName().trim().isEmpty() || hero.getName() == null){
            throw new HeroDontHaveNameException("Hero don't have name");
        }
    }
}
