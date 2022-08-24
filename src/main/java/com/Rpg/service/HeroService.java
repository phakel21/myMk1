package com.Rpg.service;

import com.Rpg.dto.HeroDTO;
import com.Rpg.dto.MonsterDTO;

import java.util.List;

public interface HeroService {

    void create(HeroDTO heroDTO);

    HeroDTO getHeroDTOByName(String name);

    List<HeroDTO> getAll();

    void updateName(String name, String editName);

    void updateCharacter(String name, String editCharacter);

    void updateUser(String name, String updateUser);

    void deleteByName(String name);

    List<HeroDTO> getHeroesByUserName(String name);

    void kick(HeroDTO heroDTO, MonsterDTO monster);

    void saving(HeroDTO heroDTO);

    void heroAlive(HeroDTO heroDTO);

    HeroDTO findHeroByMyUserAndName(String myUserName, String name);

    //    HeroDTO getByName(String name);

//    void createTest(HeroDTO heroDTO);

    //    Hero get(String name);
}
