package com.Rpg.controller;

import com.Rpg.dto.MyCharacterDTO;
import com.Rpg.entity.MyCharacter;
import com.Rpg.service.MyCharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/admin/control")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class AdminMyCharacterController {

    private final MyCharacterService myCharacterService;


    @ModelAttribute("myCharacter")
    public MyCharacterDTO getModel() {
        return new MyCharacterDTO();
    }

    @GetMapping("/characters")
    public String getAll(Model model) {
        model.addAttribute("myCharacters", myCharacterService.getAll());
        return "adminMyCharacterCreateAndGetAll";
    }


    @PostMapping("/characters")
    public String create(@ModelAttribute(name = "myCharacter") MyCharacter myCharacter,
                         @RequestParam(name = "file") MultipartFile image) throws IOException {

        myCharacterService.create(myCharacter, image);

        return "redirect:/admin/control/characters";
    }

    @GetMapping("/character/{name}/delete")
    public String delete(@PathVariable("name") String name) {
        myCharacterService.deleteByName(name);
        return "redirect:/admin/control/characters";
    }

    @GetMapping("/character/{name}/update")
    public String update(Model model,
                         @PathVariable("name") String name) {
        MyCharacter myCharacter = myCharacterService.getMyCharacterByName(name);
        model.addAttribute("myCharacter",myCharacter);
        return "adminMyCharacterUpdateAndGetOne";
    }

    @PostMapping("/character/{name}/update/name")
    public String updateName(@PathVariable("name") String name,
                             @RequestParam("updateName") String updateName) {
        myCharacterService.updateName(name, updateName);
        return "redirect:/admin/control/character/" + updateName + "/update";
    }

    @PostMapping("/character/{name}/update/hp")
    public String updateHp(@PathVariable("name") String name,
                           @RequestParam("updateHp") Integer updateHp) {
        myCharacterService.updateHp(name, updateHp);
        return "redirect:/admin/control/character/" + name + "/update";
    }

    @PostMapping("/character/{name}/update/mp")
    public String updateMp(@PathVariable("name") String name,
                           @RequestParam("updateMp") Integer updateMp) {
        myCharacterService.updateMp(name, updateMp);
        return "redirect:/admin/control/character/" + name + "/update";
    }

    @PostMapping("/character/{name}/update/power")
    public String updatePower(@PathVariable("name") String name,
                              @RequestParam("updatePower") Integer updatePower) {
        myCharacterService.updatePower(name, updatePower);
        return "redirect:/admin/control/character/" + name + "/update";
    }

    @PostMapping("/character/{name}/update/image")
    public String updateImage(@PathVariable("name") String name,
                              @RequestParam("updateImage") MultipartFile multipartFile) throws IOException {
        myCharacterService.updateImage(name, multipartFile);
        return "redirect:/admin/control/character/" + name + "/update";
    }

}
