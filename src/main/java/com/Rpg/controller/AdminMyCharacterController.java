package com.Rpg.controller;

import com.Rpg.dto.MyCharacterDTO;
import com.Rpg.service.MyCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/admin/control")
public class AdminMyCharacterController {

    private MyCharacterService myCharacterService;

    @Autowired
    public AdminMyCharacterController(MyCharacterService myCharacterService) {
        this.myCharacterService = myCharacterService;
    }

    @ModelAttribute("myCharacter")
    public MyCharacterDTO getModel() {
        return new MyCharacterDTO();
    }

    @GetMapping("/myCharacter")
    public String getAll(Model model) {
        model.addAttribute("myCharacters", myCharacterService.getAll());
        return "getCharacters";
    }

    @PostMapping("/myCharacter")
    public String create(@ModelAttribute(name = "myCharacter") MyCharacterDTO myCharacterDTO,
                         @RequestParam(name = "file") MultipartFile image) throws IOException {
        myCharacterService.create(myCharacterDTO, image);

        return "redirect:/admin/control/myCharacter";
    }

    @GetMapping("/myCharacter/{name}/delete")
    public String delete(@PathVariable("name") String name) {
        myCharacterService.deleteByName(name);
        return "redirect:/admin/control/myCharacter";
    }

    @GetMapping("/myCharacter/{name}/edit")
    public String update(Model model,
                         @PathVariable("name") String name) {
        MyCharacterDTO myCharacterDTO = myCharacterService.getOne(name);
        model.addAttribute("myCharacter", myCharacterDTO);
        return "updateAdminCharacter";
    }

    @PostMapping("/myCharacter/{name}/edit/name")
    public String updateName(@PathVariable("name") String name,
                             @RequestParam("updateName") String updateName) {
        myCharacterService.updateName(name, updateName);
        return "redirect:/admin/control/myCharacter/" + updateName + "/edit";
    }

    @PostMapping("/myCharacter/{name}/edit/hp")
    public String updateHp(@PathVariable("name") String name,
                           @RequestParam("updateHp") Integer updateHp) {
        myCharacterService.updateHp(name, updateHp);
        return "redirect:/admin/control/myCharacter/" + name + "/edit";
    }

    @PostMapping("/myCharacter/{name}/edit/mp")
    public String updateMp(@PathVariable("name") String name,
                           @RequestParam("updateMp") Integer updateMp) {
        myCharacterService.updateMp(name, updateMp);
        return "redirect:/admin/control/myCharacter/" + name + "/edit";
    }

    @PostMapping("/myCharacter/{name}/edit/power")
    public String updatePower(@PathVariable("name") String name,
                              @RequestParam("updatePower") Integer updatePower) {
        myCharacterService.updatePower(name, updatePower);
        return "redirect:/admin/control/myCharacter/" + name + "/edit";
    }

    @PostMapping("/myCharacter/{name}/edit/image")
    public String updateImage(@PathVariable("name") String name,
                              @RequestParam("updateImage") MultipartFile multipartFile) throws IOException {
        myCharacterService.updateImage(name, multipartFile);
        return "redirect:/admin/control/myCharacter/" + name + "/edit";
    }

}
