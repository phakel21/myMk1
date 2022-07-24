package com.Rpg.controller;

import com.Rpg.service.HeroService;
import com.Rpg.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("{userName}/hero/{heroName}/location")
@Controller
public class LocationController {

    private LocationService locationService;

    private HeroService heroService;

    @Autowired
    public LocationController(LocationService locationService, HeroService heroService) {
        this.locationService = locationService;
        this.heroService = heroService;
    }

    @GetMapping("/choose")
    public String choose(Model model,
                         @PathVariable("heroName") String heroName,
                         @PathVariable("userName") String userName) {
        model.addAttribute("userName", userName);
        model.addAttribute("heroName", heroName);
        model.addAttribute("locations", locationService.getAll());
        return "chooseLocation";
    }

    @PostMapping("/choose")
    public String choose(@PathVariable("heroName") String heroName,
                         @PathVariable("userName") String userName,
                         @RequestParam(name = "chooseLocation") String chooseLocation) {
        return "redirect:/" + userName + "/hero/" + heroName + "/location/" + chooseLocation + "/fight";
    }

}
