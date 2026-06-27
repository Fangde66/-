package com.itfd;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {


    /**
     * 生成JWT令牌
     */
    @Test
    public void testGenerateJwt(){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("username", "admin");
        dataMap.put("age", 18);

        // 生成密钥
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        // 把密钥转成字符串（可以存到配置文件里）
        String secretString = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        System.out.println(secretString);

        String jwt = Jwts.builder() // 构建jwt令牌
                .signWith(SignatureAlgorithm.HS256, secretString) // 指定加密的签名算法以及密钥
                .addClaims(dataMap) // 添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 设置令牌过期时间(ms)
                .compact();// 生成令牌
        System.out.println(jwt);
    }

    /**
     * 解析JWT令牌
     */
    @Test
    public void testParseJwt(){
        SecretKey secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode("5jGAOuTv1YtPAwdNvNR5f2mir7BgYr8Y5g4tMcNd+9A="));
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJhZ2UiOjE4LCJ1c2VybmFtZSI6ImFkbWluIiwiZXhwIjoxNzc5Nzk3MjE3fQ.2wEJh06FWzEstqc11eHMAy6Qwmb5zWii6sK9XIl332U";

        // claims本质是个map集合
        Claims claims = Jwts.parser() // 解析
                .verifyWith(secretKey) // 获取密钥
                .build() // 构建解析器
                .parseSignedClaims(token)// 解析令牌
                .getPayload(); // 生成Claims对象
        System.out.println(claims);
    }
}
