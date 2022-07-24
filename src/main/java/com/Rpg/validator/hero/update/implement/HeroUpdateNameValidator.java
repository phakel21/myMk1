package com.Rpg.validator.hero.update.implement;

import com.Rpg.dto.HeroDTO;
import com.Rpg.entity.Hero;
import com.Rpg.validator.hero.update.HeroUpdateValidator;
import org.springframework.stereotype.Component;

@Component
public class HeroUpdateNameValidator implements HeroUpdateValidator {

    @Override
    public void validate(Hero hero, HeroDTO updateHero) {
        if (updateHero.getName() != null && !updateHero.getName().trim().isEmpty()) {
            hero.setName(updateHero.getName());
        }
    }
}
