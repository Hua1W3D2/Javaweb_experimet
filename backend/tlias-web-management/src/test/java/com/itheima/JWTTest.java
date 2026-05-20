package com.itheima;

import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class JWTTest {
//    @Test
//    public void testGenerateJwt(){
//
//        Base64.Encoder encoder = Base64.getEncoder();
//        String encode = encoder.encodeToString("itheima".getBytes(StandardCharsets.US_ASCII));
//        System.out.println(encode);
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("id", 10);
//        claims.put("username", "itheima");
//
//        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "aXRjYXN0")
//                .addClaims(claims)
//                .setExpiration(new Date(System.currentTimeMillis() + 12 * 3600 * 1000))
//                .compact();
//
//        System.out.println(jwt);
//        JwtBuilder builder = Jwts.builder().signWith(SignatureAlgorithm.HS256,);
//    }@Test
//    public void testParseJwt() {
//        Claims claims = Jwts.parser().setSigningKey("aXRjYXN0")
//                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MTAsInVzZXJuYW1lIjoiaXRoZWltYSIsImV4cCI6MTc2ODUyOTkxOH0.J3Xrj-08LDmEc9bUVch-NO32Acdsdl_6yGbIogtOyOU")
//                .getBody();
//        System.out.println(claims);
//        Map jwt=JwtUtils.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MiwidXNlcm5hbWUiOiJzb25namlhbmciLCJleHAiOjE3Njg1MzE3Nzd9.cuaq3fk0O8Y5XgldhxUrolJys0Se2XyZFhKMj6gmL1E");
//        System.out.println(jwt);
//    }


}
