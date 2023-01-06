package com.Rpg.controller;

import com.Rpg.entity.Hero;
import com.Rpg.service.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lobby")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_USER')")
public class LobbyController {

    private final HeroService heroService;

    @GetMapping
    public String lobby(Model model) {
        Hero hero = heroService.getCurrentHero();
        model.addAttribute("hero", hero);
        return "lobby";
    }
}
