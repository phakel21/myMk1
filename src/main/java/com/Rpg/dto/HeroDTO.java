package com.Rpg.dto;

import com.Rpg.entity.Hero;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HeroDTO {

    private Long id;

    private String name;

    private Integer currentHp;

    private MyCharacterDTO myCharacterDTO;

    private MyUserDTO myUserDTO;

    public HeroDTO(Hero hero) {
        this.name = hero.getName();
        this.currentHp = hero.getCurrentHp();
        this.myCharacterDTO = new MyCharacterDTO(hero.getMyCharacter());
        this.myUserDTO = new MyUserDTO(hero.getMyUser());
    }
}
