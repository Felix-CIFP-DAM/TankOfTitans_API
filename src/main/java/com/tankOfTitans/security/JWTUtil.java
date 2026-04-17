package com.tankOfTitans.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTUtil {
	
	@Value("${jwt.secret}")
    private String secret;
	
	@Value("${jwt.expiration}")
    private long expiration;
	
	private Key getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}
