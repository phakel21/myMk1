package com.Rpg.service;

import com.Rpg.entity.Hero;
import com.Rpg.entity.Monster;
import org.springframework.stereotype.Service;

@Service
public interface FightService {

    void fight(Hero hero, Monster monster);

}
