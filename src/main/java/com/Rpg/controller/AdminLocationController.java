package com.Rpg.controller;

import com.Rpg.entity.Location;
import com.Rpg.service.LocationService;
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
public class AdminLocationController {

    private final LocationService locationService;

    @ModelAttribute("location")
    public Location getModel() {
        return new Location();
    }

    @GetMapping("/locations")
    public String getAll(Model model) {
        model.addAttribute("locations", locationService.getAll());
        return "adminLocationCreateAndGetAll";
    }

    @PostMapping("/locations")
    public String create(@ModelAttribute("location") Location location,
                         @RequestParam("file")MultipartFile multipartFile) throws IOException {
        locationService.create(location, multipartFile);

        return "redirect:/admin/control/locations";
    }


    @GetMapping("/location/{name}/delete")
    public String deleteByName(@PathVariable("name") String name) {
        locationService.deleteByName(name);
        return "redirect:/admin/control/locations";
    }

    @GetMapping("/location/{name}/update")
    public String update(Model model,
                         @PathVariable("name") String name) {
        Location location = locationService.getLocationByName(name);
        model.addAttribute("location", location);
        return "adminLocationUpdateAndGetAll";
    }

    @PostMapping("/location/{name}/update/name")
    public String updateName(@PathVariable("name") String name,
                             @RequestParam("updateName") String updateName) {
        locationService.updateName(name, updateName);
        return "redirect:/admin/control/location/" + updateName + "/update";
    }

    @PostMapping("/location/{name}/update/image")
    public String updateImage(@PathVariable("name") String name,
                             @RequestParam("updateImage") MultipartFile multipartFile) throws IOException {
        locationService.updateImage(name, multipartFile);
        return "redirect:/admin/control/location/" + name + "/update";
    }

}
