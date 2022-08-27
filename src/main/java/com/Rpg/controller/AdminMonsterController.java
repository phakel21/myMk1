package com.Rpg.controller;

import com.Rpg.dto.LocationDTO;
import com.Rpg.dto.MonsterDTO;
import com.Rpg.service.LocationService;
import com.Rpg.service.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequestMapping("/admin/control")
@Controller
public class AdminMonsterController {

    private MonsterService monsterService;

    private LocationService locationService;

    @Autowired
    public AdminMonsterController(MonsterService monsterService, LocationService locationService) {
        this.monsterService = monsterService;
        this.locationService = locationService;
    }

    @ModelAttribute("monster")
    public MonsterDTO getModel() {
        return new MonsterDTO();
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/monsters")
    public String getAll(Model model) {
        model.addAttribute("locations", locationService.getAll());
        model.addAttribute("monsters", monsterService.getAll());
        return "adminMonsterCreateAndGetAll";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/monsters")
    public String create(@ModelAttribute("monster") MonsterDTO monsterDTO,
                         @RequestParam(name = "chooseLocation") String chooseLocation,
                         @RequestParam("file") MultipartFile multipartFile) throws IOException {
        monsterDTO.setLocation(locationService.getLocationByName(chooseLocation));
        monsterService.create(monsterDTO, multipartFile);
        return "redirect:/admin/control/monsters";
    }

    @GetMapping("/monster/{name}/delete")
    public String deleteByName(@PathVariable("name") String name) {
        monsterService.deleteByName(name);
        return "redirect:/admin/control/monsters";
    }

    @GetMapping("/monster/{name}/update")
    public String getOne(Model model,
                         @PathVariable("name") String name) {
        MonsterDTO monsterDTO = monsterService.getMonsterDTOByName(name);
        model.addAttribute("monster", monsterDTO);
        List<LocationDTO> locationDTOS = locationService.getAll();
        model.addAttribute("locations", locationDTOS);
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
                              @RequestParam("updatePower") Integer updateLocation) {
        monsterService.updatePower(name, updateLocation);
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
