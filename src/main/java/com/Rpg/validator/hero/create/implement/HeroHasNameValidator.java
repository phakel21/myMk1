package com.Rpg.validator.hero.create.implement;

import com.Rpg.config.exception.hero.HeroDontHaveAllDataException;
import com.Rpg.config.exception.hero.HeroDontHaveNameException;
import com.Rpg.entity.Hero;
import com.Rpg.validator.hero.create.HeroCreateValidator;
import org.springframework.stereotype.Component;

@Component
public class HeroHasNameValidator implements HeroCreateValidator {

    @Override
    public void validate(Hero hero) {
        if(hero.getName() == null || hero.getName().trim().isEmpty()){
            throw new HeroDontHaveAllDataException("Enter all data");
        }
        if(hero.getName() == null || hero.getName().trim().isEmpty()){
            throw new HeroDontHaveNameException("Can't create hero without name");
        }
    }
}
