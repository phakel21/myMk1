package com.Rpg.controller;


import com.Rpg.entity.Monster;
import com.Rpg.service.MonsterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/monster")
@PreAuthorize("hasAnyRole('ROLE_USER')")
public class MonsterController {

    private final MonsterService monsterService;

    @GetMapping("/choose")
    public String chooseMonster(Model model){
        List<Monster> unlockedMonsters = monsterService.unlockedMonsters();
        List<Monster> disableMonsters = monsterService.disableMonsters();

        boolean isEmpty = disableMonsters.isEmpty();
        model.addAttribute("isEmpty", isEmpty);
        model.addAttribute("unlockedMonsters", unlockedMonsters);
        model.addAttribute("disableMonsters", disableMonsters);

        return "chooseMonster";
    }

    @PostMapping("/choose")
    public String chooseMonster(@RequestParam String chooseMonster){
        monsterService.chooseMonster(chooseMonster);
        return "redirect:/fight";
    }
}
