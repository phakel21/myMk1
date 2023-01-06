package com.Rpg.config.jwt;

import com.Rpg.entity.MyUser;
import com.Rpg.service.MyUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class MyFilter extends GenericFilter {

    private final MyUserService myUserService;

    private final MyProvider myProvider;

    private final JWTProvider jwtProvider;

    @Value("${jwt.header}")
    private String AUTHORIZATION;

    @Value("${header.username}")
    private String HEADER_USERNAME;

    @Value("${header.password}")
    private String HEADER_PASSWORD;

    public MyFilter(MyUserService myUserService, MyProvider myProvider, JWTProvider jwtProvider) {
        this.myUserService = myUserService;
        this.myProvider = myProvider;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String username = httpServletRequest.getParameter(HEADER_USERNAME);
        String password = httpServletRequest.getParameter(HEADER_PASSWORD);

        if (username != null && password != null) {
            boolean validate = myProvider.validate(username, password);
            MyUser myUserByLogin = myUserService.get(username);
            if (!validate) {
                throw new RuntimeException("Not valid");
            }
            String token = jwtProvider.generateToken(username);
            Cookie cookie = new Cookie(AUTHORIZATION, token);
            httpResponse.addCookie(cookie);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    username, null, Collections.singleton(new SimpleGrantedAuthority(myUserByLogin.getRole().name()))
            );


            SecurityContextHolder.getContext().setAuthentication(auth);

        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
