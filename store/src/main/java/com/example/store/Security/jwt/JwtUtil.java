package com.example.store.Security.jwt;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Value;
import javax.crypto.SecretKey;
import io.jsonwebtoken.io.Decoders;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    @Value("${security.jwt.secret-key}")
    private String jwtSecret;

    @Value("${security.jwt.expiration-time}")
    private int jwtExpirationMS;

    private SecretKey key;


    @PostConstruct
    public void init(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username){

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMS))
                .signWith(key)
                .compact();
    }

    public String getUserFromToken(String token){
        return Jwts.parser().verifyWith(key).build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

    }

    public void validateJwtToken(String token){
        Jwts.parser().verifyWith(key).build().parseSignedClaims(token);

    }
}
