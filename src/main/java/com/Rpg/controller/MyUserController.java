package com.Rpg.controller;

import com.Rpg.config.jwt.JWTProvider;
import com.Rpg.dto.MyUserDTO;
import com.Rpg.entity.MyUser;
import com.Rpg.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class MyUserController {

    private MyUserService myUserService;

    private JWTProvider jwtProvider;

    private final String headerForAuth = "Authorization";

    @Autowired
    public MyUserController(MyUserService myUserService, JWTProvider jwtProvider) {
        this.myUserService = myUserService;
        this.jwtProvider = jwtProvider;
    }

    @ModelAttribute("myUserDTO")
    public MyUserDTO getModel() {
        return new MyUserDTO();
    }

    @ModelAttribute("userLogin")
    public MyUser gerUser() {
        return new MyUser();
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("myUserDTO") MyUserDTO myUserDTO,
                               BindingResult bindingResult,
                               HttpServletResponse httpServletResponse) {
        if (bindingResult.hasErrors()) return "registration";
        MyUser myUser = myUserService.create(myUserDTO);
        String token = jwtProvider.generateToken(myUser.getLogin());
        System.out.println(token);
        httpServletResponse.addHeader(headerForAuth, token);
        Cookie cookie = new Cookie(headerForAuth, token);

        httpServletResponse.addCookie(cookie);

        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("userLogin") MyUser myUserLogin, HttpServletResponse httpServletResponse){
        MyUser myUser = myUserService.getByLoginAndPassword(myUserLogin.getLogin(), myUserLogin.getPassword());
        String token = jwtProvider.generateToken(myUser.getLogin());
        System.out.println(token);
        String loginFromToken = jwtProvider.getLoginFromToken(token);
        httpServletResponse.addHeader(headerForAuth, token);
        Cookie cookie = new Cookie(headerForAuth, token);

        httpServletResponse.addCookie(cookie);
        return "redirect:/" + loginFromToken + "/hero/choose";
    }

//    @GetMapping
//    public String getUserName(Principal principal) {
//        String name = principal.getName();
//        return "redirect:" + name + "/hero/choose";
//    }
}