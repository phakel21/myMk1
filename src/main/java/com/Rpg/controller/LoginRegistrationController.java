package com.Rpg.controller;

import com.Rpg.dto.MyUserDTO;
import com.Rpg.entity.MyUser;
import com.Rpg.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
@RequiredArgsConstructor

public class LoginRegistrationController {

    private final MyUserService myUserService;

    @ModelAttribute("myUser")
    public MyUserDTO getModel() {
        return new MyUserDTO();
    }

    @GetMapping("/registration")
    public String getRegistration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("myUser") MyUserDTO myUserDTO) {
        myUserService.create(myUserDTO);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest httpServletRequest) {

        System.out.println(httpServletRequest.getRemoteAddr());return "login";
    }

    @PostMapping("/login")
    public String afterLogin(@RequestParam String username) {
        MyUser myUser = myUserService.get(username);
        boolean role_admin = myUser.getRole().name().equals("ROLE_ADMIN");
        if(role_admin){
            return "redirect:/admin/control";
        }
        return "redirect:/hero/choose";
    }

}
