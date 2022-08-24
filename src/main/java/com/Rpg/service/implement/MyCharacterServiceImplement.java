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
public class MyCharacterServiceImplement implements MyCharacterService {

    @Value("${characters}")
    private String charactersPath;

    private MyCharacterRepository myCharacterRepository;

    private ImageService imageService;

    private List<MyCharacterCreateValidator> myCharacterCreateValidators;

    private List<MyCharacterUpdateValidator> myCharacterUpdateValidators;

    @Autowired
    public MyCharacterServiceImplement(@Lazy MyCharacterRepository myCharacterRepository,
                                       ImageService imageService,
                                       List<MyCharacterCreateValidator> myCharacterCreateValidators,
                                       List<MyCharacterUpdateValidator> myCharacterUpdateValidators) {
        this.myCharacterRepository = myCharacterRepository;
        this.imageService = imageService;
        this.myCharacterCreateValidators = myCharacterCreateValidators;
        this.myCharacterUpdateValidators = myCharacterUpdateValidators;
    }

    //CRUD methods work with repository

    private void save(MyCharacter myCharacter) {
        myCharacterRepository.save(myCharacter);
    }

    private MyCharacter findOne(String name) {
        return myCharacterRepository.findByName(name);
    }

    private List<MyCharacter> findAll() {
        return myCharacterRepository.findAll();
    }

    private void delete(String name) {
        myCharacterRepository.deleteByName(name);
    }


    //mappers

    private MyCharacter map(MyCharacterDTO myCharacterDTO) {
        MyCharacter myCharacter = new MyCharacter();
        myCharacter.setName(myCharacterDTO.getName());
        myCharacter.setHp(myCharacterDTO.getHp());
        myCharacter.setMp(myCharacterDTO.getMp());
        myCharacter.setPower(myCharacterDTO.getPower());
        myCharacter.setImage(myCharacterDTO.getImage());
        return myCharacter;
    }

    private MyCharacterDTO map(MyCharacter myCharacter) {
        MyCharacterDTO myCharacterDTO = new MyCharacterDTO();
        myCharacterDTO.setName(myCharacter.getName());
        myCharacterDTO.setHp(myCharacter.getHp());
        myCharacterDTO.setMp(myCharacter.getMp());
        myCharacterDTO.setPower(myCharacter.getPower());
        myCharacterDTO.setImage(myCharacter.getImage());
        return myCharacterDTO;
    }

    private List<MyCharacterDTO> map(List<MyCharacter> myCharacters) {
        List<MyCharacterDTO> myCharacterDTOS = new ArrayList<>();
        for (MyCharacter myCharacter : myCharacters) {
            myCharacterDTOS.add(map(myCharacter));
        }
        return myCharacterDTOS;
    }

    //my methods

    @Override
    public void create(MyCharacterDTO myCharacterDTO, MultipartFile multipartFile) throws IOException {

        MyCharacter myCharacter = map(myCharacterDTO);

        checkMyCharacterExist(myCharacter.getName());

        for (MyCharacterCreateValidator myCharacterCreateValidator : myCharacterCreateValidators) {
            myCharacterCreateValidator.validate(myCharacter);
        }

        String resultFileName = imageService.saveFile(charactersPath, multipartFile);
        myCharacter.setImage(resultFileName);

        save(myCharacter);
    }

    @Override
    public MyCharacter getMyCharacterByName(String name) {

        Optional<MyCharacter> optionalMyCharacter = myCharacterRepository.findMyCharacterByName(name);

        if (optionalMyCharacter.isPresent()) {
            return optionalMyCharacter.get();
        }

        throw new MyCharacterNotFoundException("Character: " + name + " not found");
    }

    @Override
    public List<MyCharacterDTO> getAll() {
        return map(findAll());
    }

    private void update(MyCharacter myCharacter) {

        for (MyCharacterUpdateValidator myCharacterUpdateValidator : myCharacterUpdateValidators) {
            myCharacterUpdateValidator.validate(myCharacter);
        }
        save(myCharacter);

    }

    @Override
    public void updateName(String name, String updateName) {
        checkMyCharacterExist(updateName);
        MyCharacter myCharacter = findOne(name);
        myCharacter.setName(updateName);
        update(myCharacter);
    }

    @Override
    public void updateHp(String name, Integer updateHp) {
        MyCharacter myCharacter = findOne(name);
        myCharacter.setHp(updateHp);
        update(myCharacter);
    }

    @Override
    public void updateMp(String name, Integer updateMp) {
        MyCharacter myCharacter = findOne(name);
        myCharacter.setMp(updateMp);
        update(myCharacter);
    }

    @Override
    public void updatePower(String name, Integer updatePower) {
        MyCharacter myCharacter = findOne(name);
        myCharacter.setPower(updatePower);
        update(myCharacter);
    }

    @Override
    public void updateImage(String name, MultipartFile multipartFile) throws IOException {
        MyCharacter myCharacter = findOne(name);
        imageService.deleteFile(charactersPath, myCharacter.getImage());

        myCharacter.setImage(imageService.saveFile(charactersPath, multipartFile));
        update(myCharacter);
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        Optional<MyCharacter> optionalMonster = myCharacterRepository.findMyCharacterByName(name);

        if (!optionalMonster.isPresent()) {
            throw new MyCharacterNotFoundException("MyCharacter not found");
        }

        imageService.deleteFile(charactersPath, findOne(name).getImage());
        delete(name);
    }

    @Override
    public MyCharacterDTO getMyCharacterDTOByName(String name) {
        return map(getMyCharacterByName(name));
    }

    private void checkMyCharacterExist(String name) {
        if (myCharacterRepository.existsMyCharacterByName(name)) {
            throw new MyCharacterExistException("Character " + name + " is already exist");
        }
    }


    //    @Override
//    public MyCharacter get(String name) {
//        return map(getMyCharacterByName(name));
//    }
    //    @Override
//    public MyCharacterDTO getByName(String name) {
//        return map(findOne(name));
//    }

//    @Override
//    public MyCharacter getMyCharacter(String name) {
//        Optional<MyCharacter> optionalMyCharacter = myCharacterRepository.findMyCharacterByName(name);
//        if(optionalMyCharacter.isPresent()){
//            return optionalMyCharacter.get();
//        }
//        throw new MyCharacterNotFoundException("Character: "+ name +" not found");
//    }
//
//
//    @Override
//    public MyCharacterDTO getMyCharacterDTOforUpdate(String name) {
//        MyCharacterDTO myCharacterDTO;
//        Optional<MyCharacter> myCharacterOptional = myCharacterRepository.findMyCharacterByName(name);
//        if(!myCharacterOptional.isPresent()){
//            throw new HeroDontHaveMyCharacterException("");
//        }
//
//        return map(myCharacterOptional.get());
//
//    }
}
