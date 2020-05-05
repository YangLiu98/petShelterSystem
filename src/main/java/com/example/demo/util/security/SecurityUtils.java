package com.example.demo.util.security;


import com.example.demo.util.constant.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SecurityUtils {

    private static SecretKey generateKey() {
        byte[] encodedKey = Base64.decodeBase64(Constant.JWT_SECERT);
        return Keys.hmacShaKeyFor(encodedKey);
    }

    //解析JWT字符串
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey key = generateKey();
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt)
                .getBody();
    }

    //签发JWT
    public static String createJWT(Integer user_id, long expirationTime) throws Exception {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", user_id);
        SecretKey key = generateKey();
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)      //载荷，json
                .setIssuedAt(now)       //签发时间
                .setSubject(user_id.toString())    //主题
                .signWith(key);         //签名秘钥
        if (expirationTime >= 0) {
            long expTime = nowMillis + expirationTime;
            Date exp = new Date(expTime);
            builder.setExpiration(exp);     //过期时间
        }
        return builder.compact();
    }

    //获取token
    public static String getToken(Integer user_id) {
        try {
            return createJWT(user_id, 60 * 60 * 1000);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
