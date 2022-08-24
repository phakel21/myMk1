package com.Rpg.controller;


import com.Rpg.dto.HeroDTO;
import com.Rpg.dto.MyCharacterDTO;
import com.Rpg.dto.MyUserDTO;
import com.Rpg.service.MyCharacterService;
import com.Rpg.service.HeroService;
import com.Rpg.service.MyUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/control")
public class AdminHeroController {

    private HeroService heroService;

    private MyUserService myUserService;

    private MyCharacterService myCharacterService;

    @Autowired
    public AdminHeroController(HeroService heroService, MyUserService myUserService,
                               MyCharacterService myCharacterService) {
        this.heroService = heroService;
        this.myUserService = myUserService;
        this.myCharacterService = myCharacterService;
    }

    @ModelAttribute("hero")
    public HeroDTO getModel() {
        return new HeroDTO();
    }

    @ModelAttribute("updateHero")
    public HeroDTO getUpdateHero() {
        return new HeroDTO();
    }

    @GetMapping("/heroes")
    public String createAndGetAll(Model model) {
        model.addAttribute("heroes", heroService.getAll());
        model.addAttribute("myCharacters", myCharacterService.getAll());
        model.addAttribute("myUsers", myUserService.getAll());
        return "adminHeroCreateAndGetAll";
    }

    @PostMapping("/heroes")
    public String create(@Valid @ModelAttribute("hero") HeroDTO heroDTO,
                         BindingResult bindingResult,
                         @RequestParam(name = "chooseCharacter") String chooseCharacter,
                         @RequestParam(name = "chooseUser") String chooseUser,
                         Model model) {
        model.addAttribute("heroes", heroService.getAll());
        model.addAttribute("myCharacters", myCharacterService.getAll());
        model.addAttribute("myUsers", myUserService.getAll());
        if(bindingResult.hasErrors()){
            return "adminHeroCreateAndGetAll";
        }
        MyCharacterDTO myCharacterDTO = myCharacterService.getMyCharacterDTOByName(chooseCharacter);
        MyUserDTO myUserDTO = myUserService.getMyUserDTOByName(chooseUser);
        heroDTO.setMyCharacterDTO(myCharacterDTO);
        heroDTO.setMyUserDTO(myUserDTO);
        heroService.create(heroDTO);

        return "redirect:/admin/control/heroes";
    }

    @GetMapping("/hero/{name}/update")
    public String updateAndGetOne(Model model,
                         @PathVariable("name") String name) {
        model.addAttribute("hero", heroService.getHeroDTOByName(name));
        model.addAttribute("myCharacters", myCharacterService.getAll());
        model.addAttribute("myUsers", myUserService.getAll());
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
        heroService.deleteByName(name);
        return "redirect:/admin/control/heroes";
    }

}
