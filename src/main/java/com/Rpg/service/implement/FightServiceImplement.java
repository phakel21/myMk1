package com.Rpg.service.implement;

import com.Rpg.entity.Hero;
import com.Rpg.entity.Monster;
import com.Rpg.service.FightService;
import com.Rpg.service.HeroService;
import com.Rpg.service.MonsterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class FightServiceImplement implements FightService {

    private final HeroService heroService;
    private final MonsterService monsterService;

    @Override
    public void fight(Hero hero, Monster monster) {
        monsterService.kick(hero, monster);
        if (monster.getCurrentHp() > 0) {
            heroService.kick(hero, monster);
        }
    }

    @Override
    public void heal() {
        Hero currentHero = heroService.getCurrentHero();
        currentHero.setCurrentHp(currentHero.getCurrentHp() + 10);
        currentHero.setCurrentHp(currentHero.getCurrentHp() - (int)(Math.random() * 100 - 1 + 1)- 1);
    }
}
