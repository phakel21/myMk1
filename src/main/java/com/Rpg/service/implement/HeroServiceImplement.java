package com.Rpg.service.implement;

import com.Rpg.config.exception.hero.HeroNotFoundException;
import com.Rpg.config.exception.myUser.MyUserDontHaveThisHeroException;
import com.Rpg.dto.HeroDTO;
import com.Rpg.dto.MonsterDTO;
import com.Rpg.dto.MyUserDTO;
import com.Rpg.entity.*;
import com.Rpg.repository.HeroRepository;

import com.Rpg.service.HeroService;
import com.Rpg.service.MyCharacterService;
import com.Rpg.service.MyUserService;
import com.Rpg.validator.hero.create.HeroCreateValidator;
import com.Rpg.validator.hero.update.HeroUpdateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HeroServiceImplement implements HeroService {

    private HeroRepository heroRepository;

    private MyCharacterService myCharacterService;

    private MyUserService myUserService;

    private List<HeroCreateValidator> heroCreateValidators;

    private List<HeroUpdateValidator> heroUpdateValidators;

    @Autowired
    public HeroServiceImplement(HeroRepository heroRepository,
                                MyCharacterService myCharacterService,
                                MyUserService myUserService,
                                List<HeroCreateValidator> heroCreateValidators,
                                List<HeroUpdateValidator> heroUpdateValidators) {
        this.heroRepository = heroRepository;
        this.myCharacterService = myCharacterService;
        this.myUserService = myUserService;
        this.heroCreateValidators = heroCreateValidators;
        this.heroUpdateValidators = heroUpdateValidators;
    }

    private Hero map(HeroDTO heroDTO) {
        Hero hero = new Hero();
        hero.setName(heroDTO.getName());
        hero.setCurrentHp(heroDTO.getCurrentHp());
        hero.setMyCharacter(heroDTO.getMyCharacter());
        hero.setMyUser(heroDTO.getMyUser());
        return hero;
    }

    private HeroDTO map(Hero hero) {
        HeroDTO heroDTO = new HeroDTO();
        heroDTO.setName(hero.getName());
        heroDTO.setCurrentHp(hero.getCurrentHp());
        heroDTO.setMyCharacter(hero.getMyCharacter());
        heroDTO.setMyUser(hero.getMyUser());
        return heroDTO;
    }

    private List<HeroDTO> map(List<Hero> heroes) {
        List<HeroDTO> heroDtos = new ArrayList<>();
        for (Hero hero : heroes) {
            heroDtos.add(map(hero));
        }
        return heroDtos;
    }

    @Override
    public void create(HeroDTO heroDTO, String chooseCharacter, String name) {
        createHeroDTO(heroDTO, chooseCharacter, name);
        Hero hero = map(heroDTO);

        for (HeroCreateValidator heroCreateValidator : heroCreateValidators) {
            heroCreateValidator.validate(hero);
        }
//        heroCreateValidators.stream().forEach(heroCreateValidator -> heroCreateValidator.validate(hero));
        save(hero);
    }

    private void save(Hero hero) {
        heroRepository.save(hero);
    }

    @Override
    public HeroDTO getByName(String name) {
        return map(findOne(name));
    }

    private Hero findOne(String name) {
        return heroRepository.findByName(name);
    }

    @Override
    public List<HeroDTO> getAll() {
        return map(findAll());
    }

    private List<Hero> findAll() {
        return heroRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        Optional<Hero> optionalHero = heroRepository.findHeroByName(name);
        if (!optionalHero.isPresent()) {
            throw new HeroNotFoundException("Hero with name: " + name + " not found");
        }
        heroRepository.deleteByName(name);

    }

    @Override
    public List<HeroDTO> getHeroesByUserName(String name) {
        return map(findAllByUserLogin(name));
    }

    private List<Hero> findAllByUserLogin(String name) {
        return heroRepository.findAllByMyUserLogin(name);
    }

    @Override
    public Hero get(String name) {
        return findOne(name);
    }

    @Override
    public void saving(HeroDTO heroDTO) {
        Hero hero = findOne(heroDTO.getName());
        hero.setCurrentHp(heroDTO.getCurrentHp());
        save(hero);
    }

    @Override
    public void kick(HeroDTO heroDTO, MonsterDTO monsterDTO) {

        if (heroDTO.getCurrentHp() > 0) {
            heroDTO.setCurrentHp(heroDTO.getCurrentHp() - monsterDTO.getPower());
        }
        saving(heroDTO);
    }

    @Override
    public void heroAlive(HeroDTO heroDTO) {
        heroDTO.setCurrentHp(heroDTO.getMyCharacter().getHp());
        saving(heroDTO);
    }

    @Override
    public void updateName(String name, String editName) {
        Hero hero = findOne(name);
        hero.setName(editName);
        save(hero);
    }

    @Override
    public void updateCharacter(String name, String editCharacter) {
        Hero hero = findOne(name);
        hero.setMyCharacter(myCharacterService.get(editCharacter));
        save(hero);
    }

    @Override
    public void updateUser(String name, String editUser) {
        Hero hero = findOne(name);
        hero.setMyUser(myUserService.get(editUser));
        save(hero);
    }

    @Override
    public String update(String name, HeroDTO heroDTO, String myCharacter, String myUser) {
        createHeroDTO(heroDTO, myCharacter, myUser);
        Hero hero = findOne(name);
        for (HeroUpdateValidator heroUpdateValidator : heroUpdateValidators) {
            heroUpdateValidator.validate(hero, heroDTO);
        }
        save(hero);
        return hero.getName();

    }

    @Override
    public void createHeroDTO(HeroDTO heroDTO, String myCharacter, String myUser) {
        heroDTO.setMyCharacter(myCharacterService.get(myCharacter));
        heroDTO.setMyUser(myUserService.get(myUser));
    }

    @Override
    public HeroDTO getOne(String name) {
        Optional<Hero> optionalHero = heroRepository.findHeroByName(name);
        if (optionalHero.isPresent()) {
            return map(optionalHero.get());
        }
        throw new HeroNotFoundException("Hero with name: " + name + " not found");
    }

    @Override
    public HeroDTO findHeroByMyUserAndName(String myUserName, String name) {
        MyUser myUser = myUserService.getOne(myUserName);
        Hero heroByMyUserAndName = heroRepository.findHeroByMyUserAndName(myUser, name);
        if(heroByMyUserAndName == null){
            throw new MyUserDontHaveThisHeroException("User "+ myUserName + " don't have hero " + name);
        }
        HeroDTO heroByMyUserAndName1 = map(heroByMyUserAndName);

        return heroByMyUserAndName1;
    }
}
