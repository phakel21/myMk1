package com.Rpg;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class GameApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameApplication.class, args);
//
//        AutowiredAnnotationBeanPostProcessor

//        HttpHeaders headers = new HttpHeaders();
//        HttpCookie httpCookie;
//        String autorization = ResponseCookie.from("Autorization", "12345").
//                httpOnly(true)
//                .path("/")
//                .build()
//                .toString();
//        httpCookie = ResponseCookie.from("Autorization", "12345").
//                httpOnly(true)
//                .path("/")
//                .build();
//
//        headers.add(HttpHeaders.SET_COOKIE, httpCookie.toString());
//
//        System.out.println(headers.toString());

    }
}


