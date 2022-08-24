package com.Rpg.service.implement;

import com.Rpg.config.exception.monster.MonsterExistException;
import com.Rpg.config.exception.monster.MonsterNotFoundException;
import com.Rpg.dto.HeroDTO;
import com.Rpg.dto.MonsterDTO;
import com.Rpg.entity.Monster;
import com.Rpg.repository.MonsterRepository;
import com.Rpg.service.ImageService;
import com.Rpg.service.LocationService;
import com.Rpg.service.MonsterService;
import com.Rpg.validator.monster.create.MonsterCreateValidator;
import com.Rpg.validator.monster.update.MonsterUpdateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MonsterServiceImplement implements MonsterService {

    @Value("${monsters}")
    private String monstersPath;

    private MonsterRepository monsterRepository;

    private LocationService locationService;

    private ImageService imageService;

    private List<MonsterCreateValidator> monsterCreateValidators;

    private List<MonsterUpdateValidator> monsterUpdateValidators;

    @Autowired
    public MonsterServiceImplement(MonsterRepository monsterRepository,
                                   LocationService locationService,
                                   ImageService imageService,
                                   List<MonsterCreateValidator> monsterCreateValidators,
                                   List<MonsterUpdateValidator> monsterUpdateValidators
    ) {
        this.monsterRepository = monsterRepository;
        this.locationService = locationService;
        this.imageService = imageService;
        this.monsterCreateValidators = monsterCreateValidators;
        this.monsterUpdateValidators = monsterUpdateValidators;
    }

    //CRUD methods work with repository

    private void save(Monster monster) {
        monsterRepository.save(monster);
    }

    private Monster findOne(String name) {
        return monsterRepository.findByName(name);
    }

    private List<Monster> findAll() {
        return monsterRepository.findAll();
    }

    private void delete(String name) {
        monsterRepository.deleteByName(name);
    }

    //mappers

    private Monster map(MonsterDTO monsterDTO) {
        Monster monster = new Monster();
        monster.setName(monsterDTO.getName());
        monster.setHp(monsterDTO.getHp());
        monster.setCurrentHp(monsterDTO.getCurrentHp());
        monster.setMp(monsterDTO.getMp());
        monster.setPower(monsterDTO.getPower());
        monster.setLocation(monsterDTO.getLocation());
        monster.setImage(monsterDTO.getImage());
        return monster;
    }

    private MonsterDTO map(Monster monster) {
        MonsterDTO monsterDTO = new MonsterDTO();
        monsterDTO.setName(monster.getName());
        monsterDTO.setHp(monster.getHp());
        monsterDTO.setCurrentHp(monster.getCurrentHp());
        monsterDTO.setMp(monster.getMp());
        monsterDTO.setPower(monster.getPower());
        monsterDTO.setLocation(monster.getLocation());
        monsterDTO.setImage(monster.getImage());
        return monsterDTO;
    }

    private List<MonsterDTO> map(List<Monster> monsters) {
        List<MonsterDTO> monsterDTOS = new ArrayList<>();
        for (Monster monster : monsters) {
            monsterDTOS.add(map(monster));
        }
        return monsterDTOS;
    }

    //methods for controller

    @Override
    public void create(MonsterDTO monsterDTO, MultipartFile multipartFile) throws IOException {

        Monster monster = map(monsterDTO);

        checkMonsterExist(monster.getName());

        for (MonsterCreateValidator monsterCreateValidator : monsterCreateValidators) {
            monsterCreateValidator.validate(monster);
        }
        String resultFilename = imageService.saveFile(monstersPath, multipartFile);
        monster.setImage(resultFilename);

        save(monster);
    }

    @Override
    public Monster getMonsterByName(String name) {
        Optional<Monster> optionalMonster = monsterRepository.findMonsterByName(name);
        if (optionalMonster.isPresent()) {
            return optionalMonster.get();
        }
        throw new MonsterNotFoundException("Monster: " + name + " not found");
    }

    @Override
    public List<MonsterDTO> getAll() {
        return map(findAll());
    }

    private void update(Monster monster) {

        for (MonsterUpdateValidator monsterUpdateValidator : monsterUpdateValidators) {
            monsterUpdateValidator.validate(monster);
        }
        save(monster);

    }

    @Override
    public void updateName(String name, String updateName) {
        checkMonsterExist(updateName);
        Monster monster = findOne(name);
        monster.setName(updateName);
        update(monster);
    }

    @Override
    public void updateHp(String name, Integer updateHp) {
        Monster monster = findOne(name);
        monster.setHp(updateHp);
        update(monster);
    }

    @Override
    public void updateMp(String name, Integer updateMp) {
        Monster monster = findOne(name);
        monster.setMp(updateMp);
        update(monster);
    }

    @Override
    public void updatePower(String name, Integer updatePower) {
        Monster monster = findOne(name);
        monster.setPower(updatePower);
        update(monster);
    }

    @Override
    public void updateLocation(String name, String updateLocation) {
        Monster monster = findOne(name);
        monster.setLocation(locationService.getLocationByName(updateLocation));
        update(monster);
    }

    @Override
    public void updateImage(String name, MultipartFile multipartFile) throws IOException {
        Monster monster = findOne(name);
        imageService.deleteFile(monstersPath, monster.getImage());
        monster.setImage(imageService.saveFile(monstersPath, multipartFile));
        update(monster);
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        Optional<Monster> optionalMonster = monsterRepository.findMonsterByName(name);

        if (!optionalMonster.isPresent()) {
            throw new MonsterNotFoundException("Monster not found");
        }

        imageService.deleteFile(monstersPath, findOne(name).getImage());
        delete(name);
    }

    @Override
    public void kick(HeroDTO heroDTO, MonsterDTO monsterDTO) {
        if (heroDTO.getCurrentHp() > 0) {
            monsterDTO.setCurrentHp(monsterDTO.getCurrentHp() - heroDTO.getMyCharacterDTO().getPower());
        }

        saving(monsterDTO);
    }

    @Override
    public MonsterDTO monsterInMemory(List<MonsterDTO> monsters) {
        for (MonsterDTO monster : monsters) {
            if (monster.getCurrentHp() > 0) {
                return monster;
            }
        }
        return null;
    }

    @Override
    public void saving(MonsterDTO monsterDTO) {
        Monster monster = findOne(monsterDTO.getName());
        monster.setCurrentHp(monsterDTO.getCurrentHp());
        save(monster);
    }

    @Override
    public void monstersAlive(List<MonsterDTO> monsterDTOS) {
        for (MonsterDTO monsterDTO : monsterDTOS) {
            monsterDTO.setCurrentHp(monsterDTO.getHp());
            saving(monsterDTO);
        }
    }

    @Override
    public MonsterDTO getMonsterDTOByName(String name) {
        return map(getMonsterByName(name));
    }

    private void checkMonsterExist(String name) {
        if (monsterRepository.existsHeroByName(name)) {
            throw new MonsterExistException("Monster " + name + " is already exist");
        }
    }

    @Override
    public List<MonsterDTO> getMonstersByLocationName(String name) {
        List<Monster> monsters = monsterRepository.findMonstersByLocationName(name);
        return map(monsters);
    }
}


