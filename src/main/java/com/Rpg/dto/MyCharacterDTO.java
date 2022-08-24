package com.Rpg.dto;


import com.Rpg.entity.MyCharacter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyCharacterDTO {

    private Long id;

//    @NotBlank(message = "Not be empty")
    private String name;

//    @Min(value = 0, message = "Must be biggest")
//    @Max(value = 300, message = "Must be smallest")
//    @NotNull(message = "Not be empty")
    private Integer hp;

//    @Min(value = 0, message = "Must be biggest")
//    @Max(value = 300, message = "Must be smallest")
//    @NotNull(message = "Not be empty")
    private Integer mp;

//    @Min(value = 0, message = "Must be biggest")
//    @Max(value = 300, message = "Must be smallest")
//    @NotNull(message = "Not be empty")
    private Integer power;

    private String image;

    private List<HeroDTO> heroDTOS;

    MyCharacterDTO(MyCharacter myCharacter) {
        this.name = myCharacter.getName();
        this.hp = myCharacter.getHp();
        this.mp = myCharacter.getMp();
        this.power = myCharacter.getPower();
        this.image = myCharacter.getImage();
    }
}
