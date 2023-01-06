package com.Rpg.controller;

import com.Rpg.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/location")
@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_USER')")
public class LocationController {

    private final LocationService locationService;

    @GetMapping("/choose")
    public String choose(Model model) {
        model.addAttribute("locations", locationService.getAll());
        return "chooseLocation";
    }

    @PostMapping("/choose")
    public String choose(@RequestParam String chooseLocation) {
        locationService.chooseLocation(chooseLocation);
        return "redirect:/monster/choose";
    }

}
