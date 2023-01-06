package com.Rpg.service;

import com.Rpg.entity.Hero;
import com.Rpg.entity.Location;
import com.Rpg.entity.Monster;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MonsterService {

    void create(Monster monster, MultipartFile multipartFile) throws IOException;

    Monster getMonsterByName(String name);

    List<Monster> getAll();

    void updateName(String name, String updateName);

    void updateHp(String name, Integer updateHp);

    void updateMp(String name, Integer updateMp);

    void updatePower(String name, Integer updatePower);

    void updateScore(String name, Integer updateScore);

    void updateLocation(String name, String updateLocation);

    void updateImage(String name, MultipartFile multipartFile) throws IOException;

    void deleteByName(String name);

    void kick(Hero hero, Monster monster);

    List<Monster> getMonstersByLocation(Location location);

    void chooseMonster(String name);

    Monster currentMonster();

    List<Monster> unlockedMonsters();

    List<Monster> disableMonsters();
}
