package com.Rpg.validator.hero.update;

import com.Rpg.dto.HeroDTO;
import com.Rpg.entity.Hero;

public interface HeroUpdateValidator {

    void validate(Hero hero, HeroDTO heroUpdate);
}
