package com.Rpg.dto;

import com.Rpg.entity.Hero;
import com.Rpg.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyUserDTO {

    private Long id;
    private String login;
    private String password;
    private String passwordRepeat;
    private Role role;
    private Hero hero;
}
