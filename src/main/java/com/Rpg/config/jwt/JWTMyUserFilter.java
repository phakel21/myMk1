//package com.Rpg.config.jwt;
//
//import com.Rpg.config.exception.NotFoundException;
//import com.Rpg.entity.MyUser;
//import com.Rpg.service.MyUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Collections;
//
//@Component
//public class JWTMyUserFilter extends GenericFilterBean {
//
//    private JWTProvider jwtProvider;
//
//    private MyUserService myUserService;
//
//    private final String headerForAuth = "Authorization";
//
//    @Autowired
//    public JWTMyUserFilter(JWTProvider jwtProvider, MyUserService myUserService) {
//        this.jwtProvider = jwtProvider;
//        this.myUserService = myUserService;
//    }
//
//    private Cookie getCookie(HttpServletRequest httpServletRequest){
//        Cookie[] cookies = httpServletRequest.getCookies();
//        for(Cookie cookie : cookies){
//            if(cookie.getName().equals(headerForAuth)){
//                return cookie;
//            }
//        }return null;
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
//
//        Cookie cookie = getCookie(httpServletRequest);
//        if(cookie == null){
//            httpServletRequest.getHea
//        }
//
////        String token = httpServletRequest.getHeader(headerForAuth);
//        String token = cookie.getValue();
//        boolean validate = jwtProvider.validate(token);
//        if (validate) {
//            String loginFromToken = jwtProvider.getLoginFromToken(token);
//            MyUser myUser = myUserService.getMyUserByName(loginFromToken);
//            if (myUser == null) {
//                throw new com.Rpg.config.exception.NotFoundException("Cant fond");
//            }
//
//            UsernamePasswordAuthenticationToken auth =
//                    new UsernamePasswordAuthenticationToken(myUser,
//                            null,
//                            Collections.singletonList(new SimpleGrantedAuthority(myUser.getRole().name())));
//
//            SecurityContextHolder.getContext().setAuthentication(auth);
//            httpServletRequest.
//        }
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//
//
//}
