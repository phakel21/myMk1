package com.Rpg.controller;

import com.Rpg.entity.Hero;
import com.Rpg.entity.MyCharacter;
import com.Rpg.entity.MyUser;
import com.Rpg.repository.HeroRepository;
import com.Rpg.service.HeroService;
import com.Rpg.service.MyCharacterService;
import com.Rpg.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@PreAuthorize("hasAnyRole('ROLE_USER')")
@RequestMapping("/hero")
public class HeroController {

    private final HeroService heroService;
    private final HeroRepository heroRepository;
    private final MyCharacterService myCharacterService;
    private final MyUserService myUserService;

    @ModelAttribute("hero")
    public Hero getModel() {
        return new Hero();
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("myCharacters", myCharacterService.getAll());
        return "createHero";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("hero") Hero hero,
                         @RequestParam String chooseCharacter) {

        MyCharacter myCharacter = myCharacterService.getMyCharacterByName(chooseCharacter);
        MyUser myUser = myUserService.getCurrent();

        hero.setMyCharacter(myCharacter);
        hero.setMyUser(myUser);

        heroService.create(hero);

        return "redirect:/hero/choose";
    }


    @GetMapping("/choose")
    public String choose(Model model) {

        MyUser currentUser = myUserService.getCurrent();

        Integer count = heroRepository.countHeroByMyUser(currentUser);

        if (count <= 0) {
            return "redirect:/hero/create";
        }

        List<Hero> heroesByUser = heroService.getHeroesByUser();

        model.addAttribute("heroes", heroesByUser);

        return "chooseHero";
    }

    @PostMapping("/choose")
    public String choose(@RequestParam String chooseHero) {
        heroService.chooseHero(chooseHero);
        return "redirect:/lobby";
    }


    @GetMapping("/delete")
    public String delete() {

        heroService.delete();

        return "redirect:/hero/choose/";
    }

    @GetMapping("/update")
    public String updateAndGetOne(Model model) {

        Hero hero = heroService.getCurrentHero();

        model.addAttribute("hero", hero);

        return "heroUpdateAndGetOne";
    }

    @PostMapping("/update/name")
    public String updateName(@RequestParam String updateName) {

        heroService.updateName(updateName);

        return "redirect:/hero/update";
    }

}
