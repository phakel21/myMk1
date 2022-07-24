package com.Rpg.validator.hero.create.implement;

import com.Rpg.config.exception.hero.HeroDontHaveAllDataException;
import com.Rpg.config.exception.hero.HeroDontHaveMyCharacterException;
import com.Rpg.entity.Hero;
import com.Rpg.validator.hero.create.HeroCreateValidator;
import org.springframework.stereotype.Component;

@Component
public class HeroHasMyCharacterValidator implements HeroCreateValidator {
    @Override
    public void validate(Hero hero) {
        if(hero.getMyCharacter() == null){
            throw new HeroDontHaveAllDataException("Enter all data");
        }
        if (hero.getMyCharacter()== null){
            throw new HeroDontHaveMyCharacterException("Cant create hero without character");
        }
    }
}
