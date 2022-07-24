package com.Rpg.service;

import com.Rpg.dto.MyCharacterDTO;
import com.Rpg.entity.MyCharacter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MyCharacterService {

    void create(MyCharacterDTO myCharacterDTO, MultipartFile multipartFile) throws IOException;

    MyCharacterDTO getByName(String name);

    List<MyCharacterDTO> getAll();

    void deleteByName(String name);

    MyCharacter get(String name);

    void updateName(String name, String updateName);

    void updateHp(String name, Integer updateHp);

    void updateMp(String name, Integer updateMp);

    void updatePower(String name, Integer updatePower);

    void updateImage(String name, MultipartFile multipartFile) throws IOException;

    MyCharacterDTO getOne(String name);

    MyCharacter getMyCharacter(String name);

}