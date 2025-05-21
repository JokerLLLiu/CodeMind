package com.software.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    /**
     * 生成JWT令牌
     */
    public static String generateJwt(String secretKey, Long ttlMillis, Map<String,Object> claims){
        Long expMillis = ttlMillis +System.currentTimeMillis();
        Date exp = new Date(expMillis); // 设置过期时间

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes(StandardCharsets.UTF_8))
                .setExpiration(exp);

        return builder.compact();
    }

    /**
     * 解析JWT令牌
     */
    public static Claims parseJWT(String secretKey, String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token).getBody();
        return claims;
    }
}
