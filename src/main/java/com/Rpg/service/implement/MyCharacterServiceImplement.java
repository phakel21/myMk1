package com.Rpg.service.implement;

import com.Rpg.config.exception.myCharacter.MyCharacterNotFoundException;
import com.Rpg.dto.MyCharacterDTO;

import com.Rpg.entity.MyCharacter;
import com.Rpg.repository.MyCharacterRepository;

import com.Rpg.service.ImageService;
import com.Rpg.service.MyCharacterService;
import com.Rpg.validator.hero.create.HeroCreateValidator;
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

    @Value("${myCharacters}")
    private String charactersPath;

    private MyCharacterRepository myCharacterRepository;

    private ImageService imageService;

    private List<HeroCreateValidator> heroCreateValidators;

    @Autowired
    public MyCharacterServiceImplement(@Lazy MyCharacterRepository myCharacterRepository,
                                       ImageService imageService) {
        this.myCharacterRepository = myCharacterRepository;
        this.imageService = imageService;
        this.heroCreateValidators = heroCreateValidators;
    }

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

    @Override
    public void create(MyCharacterDTO myCharacterDTO, MultipartFile multipartFile) throws IOException {
        MyCharacter myCharacter = map(myCharacterDTO);
        String resultFileName = imageService.saveFile(charactersPath, multipartFile);
        myCharacter.setImage(resultFileName);



        save(myCharacter);
    }

    private void save(MyCharacter myCharacter) {
        myCharacterRepository.save(myCharacter);
    }

    @Override
    public MyCharacterDTO getByName(String name) {
        return map(findOne(name));
    }

    private MyCharacter findOne(String name) {
        return myCharacterRepository.findByName(name);
    }

    @Override
    public List<MyCharacterDTO> getAll() {
        return map(findAll());
    }

    private List<MyCharacter> findAll() {
        return myCharacterRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        if (imageService.deleteFile(charactersPath, findOne(name).getImage()))
            myCharacterRepository.deleteByName(name);
    }

    @Override
    public MyCharacter get(String name) {
        return findOne(name);
    }

    @Override
    public void updateName(String name, String updateName) {
        MyCharacter myCharacter = findOne(name);
        myCharacter.setName(updateName);
        save(myCharacter);
    }

    @Override
    public void updateHp(String name, Integer updateHp) {
        MyCharacter myCharacter = findOne(name);
        myCharacter.setHp(updateHp);
        save(myCharacter);
    }

    @Override
    public void updateMp(String name, Integer updateMp) {
        MyCharacter myCharacter = findOne(name);
        myCharacter.setMp(updateMp);
        save(myCharacter);
    }

    @Override
    public void updatePower(String name, Integer updatePower) {
        MyCharacter myCharacter = findOne(name);
        myCharacter.setPower(updatePower);
        save(myCharacter);
    }

    @Override
    public void updateImage(String name, MultipartFile multipartFile) throws IOException {
        MyCharacter myCharacter = findOne(name);
        if (myCharacter.getImage() != null)
            imageService.deleteFile(charactersPath, myCharacter.getImage());

        myCharacter.setImage(imageService.saveFile(charactersPath, multipartFile));
        save(myCharacter);
    }

    @Override
    public MyCharacterDTO getOne(String name) {
        Optional<MyCharacter> optionalMyCharacter = myCharacterRepository.findMyCharacterByName(name);
        if(optionalMyCharacter.isPresent()){
            return map(optionalMyCharacter.get());
        }
        throw new MyCharacterNotFoundException("Character: "+ name +" not found");
    }

    @Override
    public MyCharacter getMyCharacter(String name) {
        Optional<MyCharacter> optionalMyCharacter = myCharacterRepository.findMyCharacterByName(name);
        if(optionalMyCharacter.isPresent()){
            return optionalMyCharacter.get();
        }
        throw new MyCharacterNotFoundException("Character: "+ name +" not found");
    }
}
