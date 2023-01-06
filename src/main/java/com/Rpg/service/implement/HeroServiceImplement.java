package com.Rpg.service.implement;

import com.Rpg.config.exception.hero.HeroExistException;
import com.Rpg.config.exception.hero.HeroNotFoundException;
import com.Rpg.config.exception.myCharacter.MyCharacterNotFoundException;
import com.Rpg.entity.Hero;
import com.Rpg.entity.Monster;
import com.Rpg.entity.MyCharacter;
import com.Rpg.entity.MyUser;
import com.Rpg.repository.HeroRepository;
import com.Rpg.service.HeroService;
import com.Rpg.service.MyCharacterService;
import com.Rpg.service.MyUserService;
import com.Rpg.validator.hero.create.HeroCreateValidator;
import com.Rpg.validator.hero.update.HeroUpdateValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class HeroServiceImplement implements HeroService {

    private Hero currentHero;

    private final HeroRepository heroRepository;
    private final MyCharacterService myCharacterService;
    private final MyUserService myUserService;
    private final List<HeroCreateValidator> heroCreateValidators;
    private final List<HeroUpdateValidator> heroUpdateValidators;

    @Override
    public void create(Hero hero) {

        checkHeroExist(hero.getName());

        heroCreateValidators.
                forEach(heroCreateValidator -> heroCreateValidator.validate(hero));

        heroRepository.save(hero);
    }

    @Override
    public Hero getHero(String name) {

        Optional<Hero> optionalHero = heroRepository.findHeroByName(name);

        if (!optionalHero.isPresent()) {
            throw new HeroNotFoundException("Hero with name: " + name + " not found");
        }
        return optionalHero.get();
    }

    @Override
    public List<Hero> getAll() {
        return heroRepository.findAll();
    }

    private void update(Hero hero) {

        for (HeroUpdateValidator heroUpdateValidator : heroUpdateValidators) {
            heroUpdateValidator.validate(hero);
        }
        heroRepository.save(hero);
    }

    @Override
    public void updateName(String name, String updateName) {
        checkHeroExist(updateName);
        Hero hero = heroRepository.findByName(name);
        hero.setName(updateName);
        update(hero);
    }

    @Override
    public void updateName(String updateName) {
        checkHeroExist(updateName);
        currentHero.setName(updateName);
        update(currentHero);
    }

    @Override
    public void updateCharacter(String name, String updateMyCharacter) {
        Hero hero = heroRepository.findByName(name);
        MyCharacter myCharacter = myCharacterService.getMyCharacterByName(updateMyCharacter);
        hero.setMyCharacter(myCharacter);
        update(hero);
    }

    @Override
    public void updateUser(String name, String updateMyUser) {
        Hero hero = heroRepository.findByName(name);
        hero.setMyUser(myUserService.get(updateMyUser));
        update(hero);
    }

    @Override
    public void delete() {
        heroRepository.delete(currentHero);
    }

    @Override
    public void delete(String name) {
        Optional<Hero> optionalMonster = heroRepository.findHeroByName(name);

        if (!optionalMonster.isPresent()) {
            throw new HeroNotFoundException("Hero not found");
        }

        Hero hero = heroRepository.findByName(name);

        heroRepository.delete(hero);

    }

    @Override
    public List<Hero> getHeroesByUser() {
        MyUser currentUser = myUserService.getCurrent();
        return heroRepository.findAllByMyUser(currentUser);
    }

    @Override
    public void kick(Hero hero, Monster monster) {

        if (hero.getCurrentHp() > 0) {
            hero.setCurrentHp(hero.getCurrentHp() - monster.getPower());
        }
    }


    private void checkHeroExist(String name) {
        if (heroRepository.existsHeroByName(name)) {
            throw new HeroExistException("Hero " + name + " is already exist");
        }
    }

    @Override
    public void chooseHero(String name) {
        Optional<Hero> heroByName = heroRepository.findHeroByName(name);

        if (!heroByName.isPresent()) {
            throw new HeroNotFoundException("Not found");
        }

        currentHero = heroByName.get();
    }

    @Override
    public Hero getCurrentHero() {
        return currentHero;
    }


    //mappers

//    private Hero map(HeroDTO heroDTO) {
//        Hero hero = new Hero();
//        hero.setName(heroDTO.getName());
//        hero.setCurrentHp(heroDTO.getCurrentHp());
//
//        MyCharacter myCharacter = myCharacterService.getMyCharacterByName(heroDTO.getMyCharacterDTO().getName());
//        hero.setMyCharacter(myCharacter);
//
//        MyUser myUser = myUserService.get(heroDTO.getMyUserDTO().getLogin());
//        hero.setMyUser(myUser);
//        return hero;
//    }
//
//    private HeroDTO map(Hero hero) {
//        return new HeroDTO(hero);
//    }
//
//    private List<HeroDTO> map(List<Hero> heroes) {
//        List<HeroDTO> heroDtos = new ArrayList<>();
//        for (Hero hero : heroes) {
//            heroDtos.add(map(hero));
//        }
//        return heroDtos;
//    }

}


