package com.Rpg.controller;

import com.Rpg.entity.Location;
import com.Rpg.entity.Monster;
import com.Rpg.service.LocationService;
import com.Rpg.service.MonsterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/admin/control")
@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class AdminMonsterController {

    private final MonsterService monsterService;

    private final LocationService locationService;

    @ModelAttribute("monster")
    public Monster getModel() {
        return new Monster();
    }



    @GetMapping("/monsters")
    public String getAll(Model model) {
        model.addAttribute("locations", locationService.getAll());
        model.addAttribute("monsters", monsterService.getAll());
        return "adminMonsterCreateAndGetAll";
    }


    @PostMapping("/monsters")
    public String create(@ModelAttribute("monster") Monster monster,
                         @RequestParam String chooseLocation,
                         @RequestParam("file") MultipartFile multipartFile) throws IOException {
        Location location = locationService.getLocationByName(chooseLocation);
        monster.setLocation(location);
        monsterService.create(monster, multipartFile);
        return "redirect:/admin/control/monsters";
    }

    @GetMapping("/monster/{name}/delete")
    public String deleteByName(@PathVariable("name") String name) {
        monsterService.deleteByName(name);
        return "redirect:/admin/control/monsters";
    }

    @GetMapping("/monster/{name}/update")
    public String update(Model model,
                         @PathVariable("name") String name) {
        Monster monster = monsterService.getMonsterByName(name);
        model.addAttribute("monster", monster);
        List<Location> locations = locationService.getAll();
        model.addAttribute("locations", locations);
        return "adminMonsterUpdateAndGetOne";
    }

    @PostMapping("/monster/{name}/update/name")
    public String updateName(@PathVariable("name") String name,
                             @RequestParam("updateName") String updateName) {
        monsterService.updateName(name, updateName);
        return "redirect:/admin/control/monster/" + updateName + "/update";
    }

    @PostMapping("/monster/{name}/update/hp")
    public String updateHp(@PathVariable("name") String name,
                           @RequestParam("updateHp") Integer updateHp) {
        monsterService.updateHp(name, updateHp);
        return "redirect:/admin/control/monster/" + name + "/update";
    }

    @PostMapping("/monster/{name}/update/mp")
    public String updateMp(@PathVariable("name") String name,
                           @RequestParam("updateMp") Integer updateMp) {
        monsterService.updateMp(name, updateMp);
        return "redirect:/admin/control/monster/" + name + "/update";
    }

    @PostMapping("/monster/{name}/update/power")
    public String updatePower(@PathVariable("name") String name,
                              @RequestParam Integer updatePower) {
        monsterService.updatePower(name, updatePower);
        return "redirect:/admin/control/monster/" + name + "/update";
    }
    @PostMapping("/monster/{name}/update/score")
    public String updateScore(@PathVariable("name") String name,
                              @RequestParam Integer updateScore) {
        monsterService.updateScore(name, updateScore);
        return "redirect:/admin/control/monster/" + name + "/update";
    }

    @PostMapping("/monster/{name}/update/location")
    public String updateLocation(@PathVariable("name") String name,
                                 @RequestParam("updateLocation") String updateLocation) {
        monsterService.updateLocation(name, updateLocation);
        return "redirect:/admin/control/monster/" + name + "/update";
    }

    @PostMapping("/monster/{name}/update/image")
    public String updateImage(@PathVariable("name") String name,
                              @RequestParam("updateImage") MultipartFile multipartFile) throws IOException {
        monsterService.updateImage(name, multipartFile);
        return "redirect:/admin/control/monster/" + name + "/update";
    }

}
