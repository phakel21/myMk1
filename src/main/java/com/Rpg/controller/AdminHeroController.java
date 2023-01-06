package com.Rpg.controller;


import com.Rpg.dto.HeroDTO;
import com.Rpg.dto.MyCharacterDTO;
import com.Rpg.dto.MyUserDTO;
import com.Rpg.entity.Hero;
import com.Rpg.entity.MyCharacter;
import com.Rpg.service.MyCharacterService;
import com.Rpg.service.HeroService;
import com.Rpg.service.MyUserService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin/control")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class AdminHeroController {

    private final HeroService heroService;
    private final MyCharacterService myCharacterService;

    @ModelAttribute("hero")
    public Hero getModel() {
        return new Hero();
    }

    @ModelAttribute("updateHero")
    public HeroDTO getUpdateHero() {
        return new HeroDTO();
    }


    @GetMapping("/heroes")
    public String createAndGetAll(Model model) {
        model.addAttribute("heroes", heroService.getAll());
        model.addAttribute("myCharacters", myCharacterService.getAll());
        return "adminHeroCreateAndGetAll";
    }

    @PostMapping("/heroes")
    public String create(@Valid @ModelAttribute("hero") Hero hero,
                         BindingResult bindingResult,
                         @RequestParam(name = "chooseCharacter") String chooseCharacter,
                         @RequestParam(name = "chooseUser") String chooseUser,
                         Model model) {
        model.addAttribute("heroes", heroService.getAll());
        model.addAttribute("myCharacters", myCharacterService.getAll());
        if(bindingResult.hasErrors()){
            return "adminHeroCreateAndGetAll";
        }
        MyCharacter myCharacter = myCharacterService.getMyCharacterByName(chooseCharacter);
        hero.setMyCharacter(myCharacter);
        heroService.create(hero);

        return "redirect:/admin/control/heroes";
    }

    @GetMapping("/hero/{name}/update")
    public String updateAndGetOne(Model model,
                         @PathVariable("name") String name) {
        model.addAttribute("hero", heroService.getHero(name));
        model.addAttribute("myCharacters", myCharacterService.getAll());
        return "adminHeroUpdateAndGetOne";
    }

    @PostMapping("/hero/{name}/update/name")
    public String updateName(@PathVariable("name") String name,
                             @RequestParam("updateName") String updateName) {
        heroService.updateName(name, updateName);
        return "redirect:/admin/control/hero/" + updateName + "/update";
    }

    @PostMapping("/hero/{name}/update/character")
    public String updateCharacter(@PathVariable("name") String name,
                                  @RequestParam("updateCharacter") String updateCharacter) {
        heroService.updateCharacter(name, updateCharacter);
        return "redirect:/admin/control/hero/" + name + "/update";
    }

    @PostMapping("/hero/{name}/update/user")
    public String updateUser(@PathVariable("name") String name,
                             @RequestParam("updateUser") String updateUser) {
        heroService.updateUser(name, updateUser);
        return "redirect:/admin/control/hero/" + name + "/update";
    }

    @GetMapping("/hero/{name}/delete")
    public String delete(@PathVariable("name") String name) {
        heroService.delete(name);
        return "redirect:/admin/control/heroes";
    }

}
