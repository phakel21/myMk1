package com.Rpg.service;

import com.Rpg.dto.MyUserDTO;
import com.Rpg.entity.MyUser;

import java.util.List;

public interface MyUserService {

//    MyUser registration(MyUserDTO myUserDTO);

    MyUser getMyUserByName(String login);

//    List<MyUserDTO> getAll();

    void deleteByName(String name);

    MyUserDTO getMyUserDTOByName(String name);


//    MyUserDTO getMyUserDTOforUpdate(String updateUser);

    MyUser create(MyUserDTO myUserDTO);

    MyUser getByLoginAndPassword(String login, String password);
}
