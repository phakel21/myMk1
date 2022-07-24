package com.Rpg.dto;

import com.Rpg.entity.Hero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyCharacterDTO {

    private Long id;

    private String name;

    private Integer hp;

    private Integer mp;

    private Integer power;

    private String image;

    private List<Hero> heroes;

}
