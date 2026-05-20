package com.example.autosalone.security.jwt;

import com.example.autosalone.security.CustomUserDetailsService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenManager {

    private final SecretKey key;
    private final long expirationTime;
    private final CustomUserDetailsService userDetailsService;

    public JwtTokenManager(
            @Value("${jwt.secret-key}") String keyString,
            @Value("${jwt.lifetime}") long stringExpirationTime, CustomUserDetailsService userDetailsService
    ) {
        this.key = Keys.hmacShaKeyFor(keyString.getBytes());
        this.expirationTime = stringExpirationTime;
        this.userDetailsService = userDetailsService;
    }


    public String generateToken(String login) {

        UserDetails user = userDetailsService.loadUserByUsername(login);

        return Jwts
                .builder()
                .subject(login)
                .claim("role", user.getAuthorities().iterator().next().getAuthority())
                .signWith(key)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .compact();
    }

    public String getLoginFromToken(String token) {

        return Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

}
