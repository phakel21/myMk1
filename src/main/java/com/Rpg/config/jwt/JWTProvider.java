package com.Rpg.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JWTProvider {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String login){

        Date date = Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


    public boolean validate(String token){
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String getLoginFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

}
