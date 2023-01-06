package com.Rpg.service;

import com.Rpg.entity.MyCharacter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MyCharacterService {

    void create(MyCharacter myCharacter, MultipartFile multipartFile) throws IOException;

    MyCharacter getMyCharacterByName(String name);

    List<MyCharacter> getAll();

    void updateName(String name, String updateName);

    void updateHp(String name, Integer updateHp);

    void updateMp(String name, Integer updateMp);

    void updatePower(String name, Integer updatePower);

    void updateImage(String name, MultipartFile multipartFile) throws IOException;

    void deleteByName(String name);

}