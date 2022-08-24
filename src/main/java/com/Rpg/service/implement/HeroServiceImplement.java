package com.Rpg.service.implement;

import com.Rpg.config.exception.hero.HeroExistException;
import com.Rpg.config.exception.hero.HeroNotFoundException;
import com.Rpg.config.exception.myUser.MyUserDontHaveThisHeroException;
import com.Rpg.dto.HeroDTO;
import com.Rpg.dto.MonsterDTO;
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


    //CRUD methods work with repository

    private void save(Hero hero) {
        heroRepository.save(hero);
    }

    private Hero findOne(String name) {
        return heroRepository.findByName(name);
    }

    private List<Hero> findAll() {
        return heroRepository.findAll();
    }

    private void delete(String name) {
        heroRepository.deleteByName(name);
    }

    //mappers

    private Hero map(HeroDTO heroDTO) {
        Hero hero = new Hero();
        hero.setName(heroDTO.getName());
        hero.setCurrentHp(heroDTO.getCurrentHp());

        MyCharacter myCharacter = myCharacterService.getMyCharacterByName(heroDTO.getMyCharacterDTO().getName());
        hero.setMyCharacter(myCharacter);

        MyUser myUser = myUserService.getMyUserByName(heroDTO.getMyUserDTO().getLogin());
        hero.setMyUser(myUser);
        return hero;
    }

    private HeroDTO map(Hero hero) {
        return new HeroDTO(hero);
    }

    private List<HeroDTO> map(List<Hero> heroes) {
        List<HeroDTO> heroDtos = new ArrayList<>();
        for (Hero hero : heroes) {
            heroDtos.add(map(hero));
        }
        return heroDtos;
    }

    //methods for controller

    @Override
    public void create(HeroDTO heroDTO) {

        Hero hero = map(heroDTO);

        checkHeroExist(hero.getName());

        for (HeroCreateValidator heroCreateValidator : heroCreateValidators) {
            heroCreateValidator.validate(hero);
        }
//        heroCreateValidators.stream().forEach(heroCreateValidator -> heroCreateValidator.validate(hero));
        save(hero);
    }

    private Hero getHeroByName(String name) {

        Optional<Hero> optionalHero = heroRepository.findHeroByName(name);

        if (optionalHero.isPresent()) {
            return optionalHero.get();
        }

        throw new HeroNotFoundException("Hero with name: " + name + " not found");
    }

    @Override
    public List<HeroDTO> getAll() {
        return map(findAll());
    }

    private void update(Hero hero) {

        for (HeroUpdateValidator heroUpdateValidator : heroUpdateValidators) {
            heroUpdateValidator.validate(hero);
        }
        save(hero);
    }

    @Override
    public void updateName(String name, String updateName) {
        checkHeroExist(updateName);
        Hero hero = getHeroByName(name);
        hero.setName(updateName);
        update(hero);
    }

    @Override
    public void updateCharacter(String name, String updateMyCharacter) {
        Hero hero = getHeroByName(name);
        hero.setMyCharacter(myCharacterService.getMyCharacterByName(updateMyCharacter));
        update(hero);
    }

    @Override
    public void updateUser(String name, String updateMyUser) {
        Hero hero = getHeroByName(name);
        hero.setMyUser(myUserService.getMyUserByName(updateMyUser));
        update(hero);
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        Optional<Hero> optionalHero = heroRepository.findHeroByName(name);
        if (!optionalHero.isPresent()) {
            throw new HeroNotFoundException("Hero with name: " + name + " not found");
        }
        delete(name);
    }

    @Override
    public HeroDTO getHeroDTOByName(String name) {
        return map(getHeroByName(name));
    }

    @Override
    public List<HeroDTO> getHeroesByUserName(String name) {
        return map(heroRepository.findAllByMyUserLogin(name));
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
        heroDTO.setCurrentHp(heroDTO.getMyCharacterDTO().getHp());
        saving(heroDTO);
    }

    @Override
    public HeroDTO findHeroByMyUserAndName(String myUserName, String name) {
        MyUser myUser = myUserService.getMyUserByName(myUserName);
        Hero heroByMyUserAndName = heroRepository.findHeroByMyUserAndName(myUser, name);
        if (heroByMyUserAndName == null) {
            throw new MyUserDontHaveThisHeroException("User " + myUserName + " don't have hero " + name);
        }

        return map(heroByMyUserAndName);
    }

    private void checkHeroExist(String name) {
        if(heroRepository.existsHeroByName(name)){
            throw new HeroExistException("Hero " + name + " is already exist");
        }
    }

    //
//    @Override
//    public HeroDTO getByName(String name) {
//        return map(findOne(name));
//    }

    //    @Override
//    public Hero get(String name) {
//        return findOne(name);
//    }
}
