package com.Rpg.controller;

import com.Rpg.dto.LocationDTO;
import com.Rpg.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
@RequestMapping("/admin/control")
public class AdminLocationController {

    private LocationService locationService;

    @Autowired
    public AdminLocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @ModelAttribute("location")
    public LocationDTO getModel() {
        return new LocationDTO();
    }

    @GetMapping("/location")
    public String getAll(Model model) {
        model.addAttribute("locations", locationService.getAll());
        return "getLocations";
    }

    @PostMapping("/location")
    public String create(@ModelAttribute("location") LocationDTO locationDTO,
                         @RequestParam("file")MultipartFile multipartFile) throws IOException {
        locationService.create(locationDTO, multipartFile);

        return "redirect:/admin/control/location";
    }


    @GetMapping("/location/{name}/delete")
    public String deleteByName(@PathVariable("name") String name) {
        locationService.deleteByName(name);
        return "redirect:/admin/control/location";
    }

    @GetMapping("/location/{name}/edit")
    public String update(Model model,
                         @PathVariable("name") String name) {
        LocationDTO locationDTO = locationService.getOne(name);
        model.addAttribute("location", locationDTO);
        return "updateAdminLocation";
    }

    @PostMapping("/location/{name}/edit/name")
    public String updateName(@PathVariable("name") String name,
                             @RequestParam("updateName") String updateName) {
        locationService.updateName(name, updateName);
        return "redirect:/admin/control/location/" + updateName + "/edit";
    }

    @PostMapping("/location/{name}/edit/image")
    public String updateImage(@PathVariable("name") String name,
                             @RequestParam("updateImage") MultipartFile multipartFile) throws IOException {
        locationService.updateImage(name, multipartFile);
        return "redirect:/admin/control/location/" + name + "/edit";
    }

}
