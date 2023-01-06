package com.Rpg.service;

import com.Rpg.dto.MyUserDTO;
import com.Rpg.entity.MyUser;

import java.util.List;

public interface MyUserService {

    MyUser create(MyUserDTO myUserDTO);

    MyUser get(String login);

    List<MyUser> getAll();

    void updateLogin(String login, String newLogin);
    void updatePassword(String login,String password);

    void delete(String name);

//    MyUserDTO getMyUserDTOByLogin(String login);
//
//
//    MyUser getByLoginAndPassword(String login, String password);
//
//    MyUserDTO getCurrentMyUserDTO();
    MyUser getCurrent();

}
