package com.Rpg.service;

import com.Rpg.dto.MyUserDTO;
import com.Rpg.entity.MyUser;

import java.util.List;

public interface MyUserService {

    MyUser registration(MyUserDTO myUserDTO);

    MyUser getMyUserByName(String name);

    List<MyUserDTO> getAll();

    void deleteByName(String name);

    MyUserDTO getMyUserDTOByName(String name);


    MyUserDTO getMyUserDTOforUpdate(String updateUser);
}
