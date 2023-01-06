package com.Rpg.config.jwt;

import com.Rpg.entity.MyUser;
import com.Rpg.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyProvider {

    private final MyUserService myUserService;

    private final PasswordEncoder passwordEncoder;

    public boolean validate(String username, String password) {
        MyUser myUserByLogin = myUserService.get(username);
        if (myUserByLogin == null) {
            throw new RuntimeException("User not Exist");
        }
        String passwordFromDB = myUserByLogin.getPassword();
        return passwordEncoder.matches(password, passwordFromDB);
    }
}
