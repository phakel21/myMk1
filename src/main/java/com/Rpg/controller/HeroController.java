package com.Rpg.controller;

import com.Rpg.dto.HeroDTO;
import com.Rpg.dto.MyCharacterDTO;
import com.Rpg.dto.MyUserDTO;
import com.Rpg.repository.HeroRepository;
import com.Rpg.service.MyCharacterService;
import com.Rpg.service.HeroService;
import com.Rpg.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("{userName}/hero")
public class HeroController {

    private HeroService heroService;

    private HeroRepository heroRepository;

    private MyCharacterService myCharacterService;

    private MyUserService myUserService;

    @Autowired
    public HeroController(HeroService heroService, MyCharacterService myCharacterService,
                          HeroRepository heroRepository, MyUserService myUserService) {
        this.heroService = heroService;
        this.myCharacterService = myCharacterService;
        this.heroRepository = heroRepository;
        this.myUserService = myUserService;
    }

    @ModelAttribute("hero")
    public HeroDTO getModel() {
        return new HeroDTO();
    }

    @GetMapping("/create")
    public String create(@PathVariable("userName") String user,
                         Model model) {
        model.addAttribute("user", user);
        model.addAttribute("myCharacters", myCharacterService.getAll());
        return "createHero";
    }

//    @GetMapping("/test")
//    public String test(@RequestParam(name = "chooseCharacter", defaultValue = "Scorpion") String chooseCharacter, Model model) {
//        model.addAttribute("myCharacters", myCharacterService.getAll());
//        model.addAttribute("myCharacter", myCharacterService.getHeroByName(chooseCharacter));
//        return "test";
//    }

//    @PostMapping("/create")
//    public String chooseCharacter(@RequestParam(name = "chooseCharacter")String name,
//                         Model model){
//        model.addAttribute("myCharacter", myCharacterService.getHeroByName(name));
//        return "redirect:/" + name + "/hero/create";
//    }

    @PostMapping("/create")
    public String create(@ModelAttribute("hero") HeroDTO heroDTO,
                         @RequestParam(name = "choose") String chooseCharacter,
                         @PathVariable("userName") String userName) {
//        if(chooseCharacter != null && !chooseCharacter.trim().isEmpty()){
//            heroDTO.setMyCharacterDTO(myCharacterService.getByName(chooseCharacter));
//        }
//        if(bindingResult.hasErrors()){
//            return create(model);
//            return "createHero";
//        }


        MyCharacterDTO myCharacterDTO = myCharacterService.getMyCharacterDTOByName(chooseCharacter);
        MyUserDTO myUserDTO = myUserService.getMyUserDTOByName(userName);
        heroDTO.setMyCharacterDTO(myCharacterDTO);
        heroDTO.setMyUserDTO(myUserDTO);
        heroService.create(heroDTO);
        return "redirect:/" + userName + "/hero/choose";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/choose")
    public String choose(Model model,
                         @PathVariable("userName") String name) {
        model.addAttribute("userName", name);
        model.addAttribute("heroes", heroService.getHeroesByUserName(name));
        Integer count = heroRepository.countHeroByMyUser(myUserService.getMyUserByName(name));
        if (count <= 0) {
            return "redirect:/" + name + "/hero/create";
        }
        return "chooseHero";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @PostMapping("/choose")
    public String choose(@RequestParam(name = "chooseHero") String chooseHero,
                         @PathVariable("userName") String name) {
        return "redirect:/" + name + "/hero/" + chooseHero + "/lobby";
    }


    @GetMapping("/{heroName}/lobby")
    public String lobby(@PathVariable("heroName") String name,
                        @PathVariable("userName") String userName,
                        Model model) {
        HeroDTO hero = heroService.findHeroByMyUserAndName(userName, name);
        model.addAttribute("hero", hero);
        heroService.heroAlive(hero);
        return "lobby";
    }

    @GetMapping("/delete/{name}")
    public String delete(@PathVariable("name") String name,
                         @PathVariable("userName") String userName) {
        heroService.deleteByName(name);
        return "redirect:/" + userName + "/hero/choose";
    }

    @GetMapping("/update/{name}")
    public String editAndGetOne(Model model,
                                @PathVariable("name") String name) {
        HeroDTO heroDTO = heroService.getHeroDTOByName(name);
        model.addAttribute("hero", heroDTO);
        return "updateHero";
    }

//    @PostMapping("/edit/{name}/name")
//    public String editAndGetOne(@PathVariable("name") String name,
//                                @PathVariable("userName") String userName,
//                                @RequestParam("editName") String editName) {
//        heroService.updateName(name, editName);
//        return "redirect:/" + userName + "/hero/edit/" + editName;
//    }


}
