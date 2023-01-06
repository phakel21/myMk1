package com.Rpg.service;

import com.Rpg.entity.Hero;
import com.Rpg.entity.Monster;

import java.util.List;

public interface HeroService {

    void create(Hero hero);

    Hero getHero(String name);

    List<Hero> getAll();

    void updateName(String name, String editName);

    void updateName(String newName);

    void updateCharacter(String name, String editCharacter);

    void updateUser(String name, String updateUser);

    void delete();
    void delete(String name);


    void kick(Hero hero, Monster monster);

    void chooseHero(String name);

    Hero getCurrentHero();

    List<Hero> getHeroesByUser();

}
