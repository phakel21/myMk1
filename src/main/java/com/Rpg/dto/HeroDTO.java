package com.Rpg.dto;

import com.Rpg.entity.MyCharacter;
import com.Rpg.entity.MyUser;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HeroDTO {
        private Long id;

    private String name;

    private Integer currentHp;

    private MyCharacter myCharacter;

    private MyUser myUser;

}
