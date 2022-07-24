package com.Rpg.controller;

import com.Rpg.dto.MyCharacterDTO;
import com.Rpg.dto.HeroDTO;
import com.Rpg.dto.MyUserDTO;
import com.Rpg.entity.Hero;
import com.Rpg.service.MyCharacterService;
import com.Rpg.service.HeroService;
import com.Rpg.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/hero")
    public String createAndGetAll(Model model) {
        model.addAttribute("heroes", heroService.getAll());
        model.addAttribute("myCharacters", myCharacterService.getAll());
        model.addAttribute("myUsers", myUserService.getAll());
        return "getHeroes";
    }

    @PostMapping("/hero")
    public String createAndGetAll(@ModelAttribute("createHero") HeroDTO heroDTO,
                                  @RequestParam(name = "chooseCharacter") String chooseCharacter,
                                  @RequestParam(name = "chooseUser") String chooseUser) {
        heroService.create(heroDTO, chooseCharacter, chooseUser);
        return "redirect:/admin/control/hero";
    }

    @GetMapping("/hero/delete/{name}")
    public String delete(@PathVariable("name") String name) {
        heroService.deleteByName(name);
        return "redirect:/admin/control/hero";
    }


    @GetMapping("/hero/{name}/edit")
    public String update(Model model,
                         @PathVariable("name") String name) {
        HeroDTO heroDTO = heroService.getOne(name);
        model.addAttribute("hero", heroDTO);
        List<MyCharacterDTO> myCharacterDTOS = myCharacterService.getAll();
        model.addAttribute("myCharacters", myCharacterDTOS);
        List<MyUserDTO> myUserDTOS = myUserService.getAll();
        model.addAttribute("myUsers", myUserDTOS);
        return "updateAdminHero";
    }

    @GetMapping("/hero/{name}/upd")
    public String upd(Model model,
                      @PathVariable("name")String name) {
        model.addAttribute("hero", heroService.get(name));
        model.addAttribute("myCharacters", myCharacterService.getAll());
        model.addAttribute("myUsers", myUserService.getAll());
        return "updHero";
    }

    @PostMapping("/hero/{name}/upd")
    public String upd(@ModelAttribute HeroDTO heroDTO,
                      @RequestParam(value = "updateCharacter", required = false) String updateCharacter,
                      @RequestParam(value = "updateUser", required = false)String updateUser,
                      @PathVariable("name")String name){
        String updateName = heroService.update(name, heroDTO, updateCharacter, updateUser);
        return "redirect:/admin/control/hero/"+ updateName +"/upd";
    }


    @PostMapping("/hero/{name}/edit/name")
    public String updateName(@PathVariable("name") String name,
                             @RequestParam("updateName") String updateName) {
        heroService.updateName(name, updateName);
        return "redirect:/admin/control/hero/" + updateName + "/edit";
    }

    @PostMapping("/hero/{name}/edit/character")
    public String updateCharacter(@PathVariable("name") String name,
                                  @RequestParam("updateCharacter") String updateCharacter) {
        heroService.updateCharacter(name, updateCharacter);
        return "redirect:/admin/control/hero/" + name + "/edit";
    }

    @PostMapping("/hero/{name}/edit/user")
    public String updateUser(@PathVariable("name") String name,
                             @RequestParam("updateUser") String updateUser) {
        heroService.updateUser(name, updateUser);
        return "redirect:/admin/control/hero/" + name + "/edit";
    }

}
