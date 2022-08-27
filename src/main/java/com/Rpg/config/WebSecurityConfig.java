package com.Rpg.config;

import com.Rpg.config.jwt.JWTFilter;

import com.Rpg.service.implement.MyUserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTFilter jwtFilter;

//    @Autowired
//    private JWTMyUserFilter jwtMyUserFilter;

//    private MyUserServiceImplement userServiceImplement;
//
//    @Autowired
//    public WebSecurityConfig(MyUserServiceImplement userServiceImplement) {
//        this.userServiceImplement = userServiceImplement;
//    }

    //    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(this.userServiceImplement)
//                .passwordEncoder(passwordEncoder());
//    }
//


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http
//                .csrf().disable()
//                    .authorizeRequests()
//                    .antMatchers("/**")
//                    .permitAll()
//                    .anyRequest()
//                    .authenticated()
//                .and()
//                    .formLogin()
//                    .usernameParameter("login")
//                    .passwordParameter("password")
//                    .loginPage("/login")
//                    .permitAll().
//                and().
//                    logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().disable()
//                .authorizeRequests().antMatchers("/").hasAnyRole("ADMIN")
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//                .addFilterAfter(jwtMyUserFilter, JWTFilter.class);
    }
}
