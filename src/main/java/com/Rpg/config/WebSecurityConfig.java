package com.Rpg.config;

import com.Rpg.config.jwt.JWTFilter;

import com.Rpg.config.jwt.MyFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JWTFilter jwtFilter;
    private final MyFilter myFilter;

    @Value("${jwt.header}")
    private String AUTHORIZATION;

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
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(myFilter, JWTFilter.class);
        http
                .logout().deleteCookies(AUTHORIZATION).clearAuthentication(true).logoutSuccessUrl("/login");
        http
                .exceptionHandling().accessDeniedPage("/accessDenied");

    }
}
