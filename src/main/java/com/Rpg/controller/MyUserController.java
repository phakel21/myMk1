package com.Rpg.controller;

import com.Rpg.entity.MyUser;
import com.Rpg.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
@PreAuthorize("hasAnyRole('ROLE_USER')")
public class MyUserController {

    private final MyUserService myUserService;

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }



    @ModelAttribute("userLogin")
    public MyUser gerUser() {
        return new MyUser();
    }



    @GetMapping("update")
    public String updateUser(Model model){
        MyUser currentMyUser = myUserService.getCurrent();
        model.addAttribute("user", currentMyUser);
        return "userupdate";
    }

    @PostMapping("/update/username")
    public String updateUsername(@RequestParam String newUsername){
        MyUser currentMyUser = myUserService.getCurrent();
        String login = currentMyUser.getLogin();
        myUserService.updateLogin(login, newUsername);
        return "redirect:/user/update";
    }

    @PostMapping("/update/password")
    public String updatePassword(@RequestParam String newPassword){
        MyUser currentMyUser = myUserService.getCurrent();
        String login = currentMyUser.getLogin();
        myUserService.updatePassword(login, newPassword);
        return "redirect:/user/update";

    }

}