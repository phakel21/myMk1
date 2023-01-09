package com.Rpg.controller;

import com.Rpg.entity.Hero;
import com.Rpg.entity.Location;
import com.Rpg.entity.Monster;
import com.Rpg.entity.MyCharacter;
import com.Rpg.repository.HeroRepository;
import com.Rpg.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/fight")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_USER')")
public class FightController {

    private final HeroService heroService;
    private final LocationService locationService;
    private final FightService fightService;
    private final MonsterService monsterService;
    private final HeroRepository heroRepository;


    private static final String win = "win";
    private static final String loose = "loose";


    @GetMapping
    public String fight(Model model) {


        Monster monster = monsterService.currentMonster();
        Hero hero = heroService.getCurrentHero();
        Location location = locationService.currentLocation();

        if(monster == null || hero == null || location == null){
            return "redirect:/hero/choose";
        }

        model.addAttribute("monster", monster);
        model.addAttribute("hero", hero);
        model.addAttribute("location", location);

        return "fight";

    }

    @PostMapping
    public String fight(@RequestParam String choose) {

        Monster monster = monsterService.currentMonster();
        Hero hero = heroService.getCurrentHero();

        if (choose.equals("kick")) {
            fightService.fight(hero, monster);
        } else if (choose.equals("heal")) {
            fightService.heal();
        }

        if (hero.getCurrentHp() > 0 && monster.getCurrentHp() <= 0) {
                hero.setScore(10);
            heroRepository.save(hero);
            return "redirect:/fight/" + win;
        }

        if (hero.getCurrentHp() <= 0) {
            return "redirect:/fight/" + loose;
        }

        return "redirect:/fight";

    }


    @GetMapping("/{winOrLose}")
    public String menu(Model model,
                       @PathVariable("winOrLose") String winOrLose) {
        Hero currentHero = heroService.getCurrentHero();
        MyCharacter myCharacter = currentHero.getMyCharacter();
        currentHero.setCurrentHp(myCharacter.getHp());
        heroRepository.save(currentHero);
        model.addAttribute("winOrLose", winOrLose);
        return "menu";
    }

    @PostMapping("/{winOrLose}")
    public String menu(@RequestParam String choose,
                       @PathVariable("winOrLose") String winOrLose) {

        if (choose.equals("lobby"))
            return "redirect:/lobby";

        return "redirect:/fight/" + winOrLose;
    }

}
