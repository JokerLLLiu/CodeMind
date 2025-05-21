package com.software.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static SecretKey signKey = Jwts.SIG.HS256.key().build();
    private static Long expire = 43200000L;

    /**
     * 生成JWT令牌
     */
    public static String generateJwt(Map<String,Object> claims){
        String jwt = Jwts.builder()
                .claims(claims)
                .signWith(signKey, Jwts.SIG.HS256)
                .expiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return jwt;
    }

    /**
     * 解析JWT令牌
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .verifyWith(signKey)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
        return claims;
    }
}
