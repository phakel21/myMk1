package com.Rpg.service.implement;

import com.Rpg.config.exception.monster.MonsterExistException;
import com.Rpg.config.exception.monster.MonsterNotFoundException;
import com.Rpg.entity.Hero;
import com.Rpg.entity.Location;
import com.Rpg.entity.Monster;
import com.Rpg.repository.MonsterRepository;
import com.Rpg.service.HeroService;
import com.Rpg.service.ImageService;
import com.Rpg.service.LocationService;
import com.Rpg.service.MonsterService;
import com.Rpg.validator.monster.create.MonsterCreateValidator;
import com.Rpg.validator.monster.update.MonsterUpdateValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MonsterServiceImplement implements MonsterService {

    private Monster current;

    @Value("${monsters}")
    private String monstersPath;

    private final MonsterRepository monsterRepository;

    private final HeroService heroService;

    private final LocationService locationService;

    private final ImageService imageService;

    private final List<MonsterCreateValidator> monsterCreateValidators;

    private final List<MonsterUpdateValidator> monsterUpdateValidators;


    //mappers

//    private Monster map(MonsterDTO monsterDTO) {
//        Monster monster = new Monster();
//        monster.setName(monsterDTO.getName());
//        monster.setHp(monsterDTO.getHp());
//        monster.setCurrentHp(monsterDTO.getCurrentHp());
//        monster.setMp(monsterDTO.getMp());
//        monster.setPower(monsterDTO.getPower());
//        monster.setLocation(monsterDTO.getLocation());
//        monster.setImage(monsterDTO.getImage());
//        return monster;
//    }
//
//    private MonsterDTO map(Monster monster) {
//        MonsterDTO monsterDTO = new MonsterDTO();
//        monsterDTO.setName(monster.getName());
//        monsterDTO.setHp(monster.getHp());
//        monsterDTO.setCurrentHp(monster.getCurrentHp());
//        monsterDTO.setMp(monster.getMp());
//        monsterDTO.setPower(monster.getPower());
//        monsterDTO.setLocation(monster.getLocation());
//        monsterDTO.setImage(monster.getImage());
//        return monsterDTO;
//    }
//
//    private List<MonsterDTO> map(List<Monster> monsters) {
//        List<MonsterDTO> monsterDTOS = new ArrayList<>();
//        for (Monster monster : monsters) {
//            monsterDTOS.add(map(monster));
//        }
//        return monsterDTOS;
//    }

    //methods for controller

    @Override
    public void create(Monster monster, MultipartFile multipartFile) throws IOException {

        checkMonsterExist(monster.getName());

        for (MonsterCreateValidator monsterCreateValidator : monsterCreateValidators) {
            monsterCreateValidator.validate(monster);
        }
        String resultFilename = imageService.saveFile(monstersPath, multipartFile);
        monster.setImage(resultFilename);

        monsterRepository.save(monster);
    }

    @Override
    public Monster getMonsterByName(String name) {
        Optional<Monster> optionalMonster = monsterRepository.findMonsterByName(name);
//        if (!optionalMonster.isPresent()) {
//            throw new MonsterNotFoundException("Monster: " + name + " not found");
//        }
//        return optionalMonster.get();
        return optionalMonster.
                orElseThrow(() -> new MonsterNotFoundException("Monster: " + name + " not found"));
    }


    @Override
    public List<Monster> getAll() {
        return monsterRepository.findAll();
    }

    private void update(Monster monster) {

        for (MonsterUpdateValidator monsterUpdateValidator : monsterUpdateValidators) {
            monsterUpdateValidator.validate(monster);
        }
        monsterRepository.save(monster);

    }

    @Override
    public void updateName(String name, String updateName) {
        checkMonsterExist(updateName);
        Monster monster = monsterRepository.findByName(name);
        monster.setName(updateName);
        update(monster);
    }

    @Override
    public void updateHp(String name, Integer updateHp) {
        Monster monster = monsterRepository.findByName(name);
        monster.setHp(updateHp);
        update(monster);
    }

    @Override
    public void updateMp(String name, Integer updateMp) {
        Monster monster = monsterRepository.findByName(name);
        monster.setMp(updateMp);
        update(monster);
    }

    @Override
    public void updatePower(String name, Integer updatePower) {
        Monster monster = monsterRepository.findByName(name);
        monster.setPower(updatePower);
        update(monster);
    }

    @Override
    public void updateScore(String name, Integer updateScore) {
        Monster monster = monsterRepository.findByName(name);
        monster.setScore(updateScore);
        update(monster);
    }

    @Override
    public void updateLocation(String name, String updateLocation) {
        Monster monster = monsterRepository.findByName(name);
        monster.setLocation(locationService.getLocationByName(updateLocation));
        update(monster);
    }

    @Override
    public void updateImage(String name, MultipartFile multipartFile) throws IOException {
        Monster monster = monsterRepository.findByName(name);
        imageService.deleteFile(monstersPath, monster.getImage());
        monster.setImage(imageService.saveFile(monstersPath, multipartFile));
        update(monster);
    }

    @Override
    public void deleteByName(String name) {
        Optional<Monster> optionalMonster = monsterRepository.findMonsterByName(name);

        if (!optionalMonster.isPresent()) {
            throw new MonsterNotFoundException("Monster not found");
        }

        Monster monster = monsterRepository.findByName(name);

        String image = monster.getImage();

        imageService.deleteFile(monstersPath, image);

        monsterRepository.delete(monster);
    }

    @Override
    public void kick(Hero hero, Monster monster) {
        if (hero.getCurrentHp() > 0) {
            monster.setCurrentHp(monster.getCurrentHp() - hero.getMyCharacter().getPower());
        }
    }



    private void checkMonsterExist(String name) {
        if (monsterRepository.existsHeroByName(name)) {
            throw new MonsterExistException("Monster " + name + " is already exist");
        }
    }

    @Override
    public List<Monster> getMonstersByLocation(Location location) {
        return monsterRepository.findMonstersByLocation(location);
    }

    @Override
    public void chooseMonster(String name){
        Optional<Monster> monsterByName = monsterRepository.findMonsterByName(name);

        if (!monsterByName.isPresent()){
            throw new MonsterNotFoundException("Not found");
        }

        current = monsterByName.get();

    }

    @Override
    public Monster currentMonster(){
        return current;
    }


    @Override
    public List<Monster> disableMonsters() {
        Hero currentHero = heroService.getCurrentHero();
        Integer score = currentHero.getScore();
        return monsterRepository.findAllByScoreGreaterThan(score);
    }

    @Override
    public List<Monster> unlockedMonsters() {
        Hero currentHero = heroService.getCurrentHero();
        Integer score = currentHero.getScore();
        return monsterRepository.findAllByScoreLessThanEqual(score);
    }
}


