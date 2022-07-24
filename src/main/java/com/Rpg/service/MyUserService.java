package com.Rpg.service;

import com.Rpg.dto.MyUserDTO;
import com.Rpg.entity.MyUser;

import java.util.List;

public interface MyUserService {

    MyUser registration(MyUserDTO myUserDTO);

    MyUserDTO getByName(String name);

    List<MyUserDTO> getAll();

    void deleteByName(String name);

    MyUser get(String name);

    MyUserDTO hetOne(String name);

    MyUser getOne(String name);



}
