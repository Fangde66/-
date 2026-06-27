package com.itfd.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    // ====================== 固定密钥（一次生成，永久使用） ======================
    // 这是安全的 256位 HS256 密钥，你也可以换成自己生成的
    private static final String SECRET_KEY = "5jGAOuTv1YtPAwdNvNR5f2mir7BgYr8Y5g4tMcNd+9A=";

    // 令牌有效期：12小时
    private static final long EXPIRE_TIME = 1000 * 60 * 60 * 12;

    // ====================== 获取密钥对象 ======================
    private static SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY));
    }

    // ====================== 1. 生成JWT令牌 ======================
    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .claims(claims)           // 自定义数据
                .expiration(new Date(System.currentTimeMillis() + EXPIRE_TIME)) // 12小时过期
                .signWith(getSecretKey())    // 签名
                .compact();
    }

    // ====================== 2. 解析JWT令牌 ======================
    public static Claims parseJwt(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())   // 验证密钥
                .build()
                .parseSignedClaims(token)     // 解析签名JWT
                .getPayload();                // 获取载荷
    }
}