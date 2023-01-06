package com.Rpg.config.jwt;

import com.Rpg.entity.MyUser;
import com.Rpg.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JWTFilter extends GenericFilterBean {

    private final JWTProvider jwtProvider;
    private final MyUserService myUserService;

    @Value("${jwt.header}")
    private String AUTHORIZATION;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            Cookie jwtCookie = null;
            for (Cookie cookie : cookies) {
                boolean authorization = cookie.getName().equals(AUTHORIZATION);
                if (authorization)
                    jwtCookie = cookie;
            }

            if (jwtCookie != null) {
                String value = jwtCookie.getValue();
                boolean validate = jwtProvider.validate(value);
                if (!validate) {
                    deleteCookie(cookies, httpServletResponse);
                    throw new RuntimeException("Not Valid");
                }
                String loginFromToken = jwtProvider.getLoginFromToken(value);
                MyUser myUserByLogin = myUserService.get(loginFromToken);
                if (myUserByLogin == null) {
                    throw new RuntimeException("Null");

                }
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        myUserByLogin, null, Collections.singleton(new SimpleGrantedAuthority(myUserByLogin.getRole().name()))
                );
                SecurityContextHolder.getContext().setAuthentication(auth);

            }
        }


        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void deleteCookie(Cookie[] cookies, HttpServletResponse response){

        if (cookies != null) {

            for (Cookie cookie : cookies) {
                cookie.setValue(null);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }

        }
    }

}
