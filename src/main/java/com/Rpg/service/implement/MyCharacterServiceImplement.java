package com.Rpg.service.implement;

import com.Rpg.config.exception.myCharacter.MyCharacterExistException;
import com.Rpg.config.exception.myCharacter.MyCharacterNotFoundException;
import com.Rpg.dto.MyCharacterDTO;

import com.Rpg.entity.MyCharacter;
import com.Rpg.repository.MyCharacterRepository;

import com.Rpg.service.ImageService;
import com.Rpg.service.MyCharacterService;
import com.Rpg.validator.myCharacter.create.MyCharacterCreateValidator;
import com.Rpg.validator.myCharacter.update.MyCharacterUpdateValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MyCharacterServiceImplement implements MyCharacterService {

    @Value("${characters}")
    private String charactersPath;

    private final MyCharacterRepository myCharacterRepository;

    private final ImageService imageService;

    private final List<MyCharacterCreateValidator> myCharacterCreateValidators;

    private final List<MyCharacterUpdateValidator> myCharacterUpdateValidators;


//    //mappers
//
//    private MyCharacter map(MyCharacterDTO myCharacterDTO) {
//        MyCharacter myCharacter = new MyCharacter();
//        myCharacter.setName(myCharacterDTO.getName());
//        myCharacter.setHp(myCharacterDTO.getHp());
//        myCharacter.setMp(myCharacterDTO.getMp());
//        myCharacter.setPower(myCharacterDTO.getPower());
//        myCharacter.setImage(myCharacterDTO.getImage());
//        return myCharacter;
//    }
//
//    private MyCharacterDTO map(MyCharacter myCharacter) {
//        MyCharacterDTO myCharacterDTO = new MyCharacterDTO();
//        myCharacterDTO.setName(myCharacter.getName());
//        myCharacterDTO.setHp(myCharacter.getHp());
//        myCharacterDTO.setMp(myCharacter.getMp());
//        myCharacterDTO.setPower(myCharacter.getPower());
//        myCharacterDTO.setImage(myCharacter.getImage());
//        return myCharacterDTO;
//    }
//
//    private List<MyCharacterDTO> map(List<MyCharacter> myCharacters) {
//        List<MyCharacterDTO> myCharacterDTOS = new ArrayList<>();
//        for (MyCharacter myCharacter : myCharacters) {
//            myCharacterDTOS.add(map(myCharacter));
//        }
//        return myCharacterDTOS;
//    }

    //my methods

    @Override
    public void create(MyCharacter myCharacter, MultipartFile multipartFile) throws IOException {

        checkMyCharacterExist(myCharacter.getName());

        for (MyCharacterCreateValidator myCharacterCreateValidator : myCharacterCreateValidators) {
            myCharacterCreateValidator.validate(myCharacter);
        }

        String resultFileName = imageService.saveFile(charactersPath, multipartFile);
        myCharacter.setImage(resultFileName);

        myCharacterRepository.save(myCharacter);
    }

    @Override
    public MyCharacter getMyCharacterByName(String name) {

        Optional<MyCharacter> optionalMyCharacter = myCharacterRepository.findMyCharacterByName(name);

        if (!optionalMyCharacter.isPresent()) {
            throw new MyCharacterNotFoundException("Character: " + name + " not found");
        }

        return optionalMyCharacter.get();


    }

    @Override
    public List<MyCharacter> getAll() {
        return myCharacterRepository.findAll();
    }

    private void update(MyCharacter myCharacter) {

        for (MyCharacterUpdateValidator myCharacterUpdateValidator : myCharacterUpdateValidators) {
            myCharacterUpdateValidator.validate(myCharacter);
        }
        myCharacterRepository.save(myCharacter);

    }

    @Override
    public void updateName(String name, String updateName) {
        checkMyCharacterExist(updateName);
        MyCharacter myCharacter = myCharacterRepository.findByName(name);
        myCharacter.setName(updateName);
        update(myCharacter);
    }

    @Override
    public void updateHp(String name, Integer updateHp) {
        MyCharacter myCharacter = myCharacterRepository.findByName(name);
        myCharacter.setHp(updateHp);
        update(myCharacter);
    }

    @Override
    public void updateMp(String name, Integer updateMp) {
        MyCharacter myCharacter = myCharacterRepository.findByName(name);
        myCharacter.setMp(updateMp);
        update(myCharacter);
    }

    @Override
    public void updatePower(String name, Integer updatePower) {
        MyCharacter myCharacter = myCharacterRepository.findByName(name);
        myCharacter.setPower(updatePower);
        update(myCharacter);
    }

    @Override
    public void updateImage(String name, MultipartFile multipartFile) throws IOException {
        MyCharacter myCharacter = myCharacterRepository.findByName(name);

        imageService.deleteFile(charactersPath, myCharacter.getImage());

        String image = imageService.saveFile(charactersPath, multipartFile);
        myCharacter.setImage(image);
        update(myCharacter);
    }

    @Override
    public void deleteByName(String name) {
        Optional<MyCharacter> optionalMonster = myCharacterRepository.findMyCharacterByName(name);

        if (!optionalMonster.isPresent()) {
            throw new MyCharacterNotFoundException("MyCharacter not found");
        }

        MyCharacter myCharacter = myCharacterRepository.findByName(name);

        String image = myCharacter.getImage();

        imageService.deleteFile(charactersPath, image);
        myCharacterRepository.delete(myCharacter);
    }


    private void checkMyCharacterExist(String name) {
        if (myCharacterRepository.existsMyCharacterByName(name)) {
            throw new MyCharacterExistException("Character " + name + " is already exist");
        }
    }

}
