package com.tankOfTitans.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

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
	
	public String generateToken(Long userId, String nickname) {
        return Jwts.builder()
                .setSubject(nickname)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
	}
	
	private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
	
	 public String extractNickname(String token) {
	        return getClaims(token).getSubject();
	 }

	 public Long extractUserId(String token) {
	        return getClaims(token).get("userId", Long.class);
	 }
    
}
