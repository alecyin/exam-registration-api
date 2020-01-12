package com.exam.registration.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.ServletException;
import java.util.Date;

/**
 * @author yhf
 * @classname JwtUtil
 * @description TODO
 * @date 2020/1/9
 **/
public class JwtUtil {
    /**
     * 私钥
     */
    final static String base64EncodedSecretKey = "8@A5jw1lVBVz!cD&DX$zAvnstN7!kKBMwQ5X8Xqrv0kXKLrv97"
                                                + "g&53wYogdISX^f2KdzxtvV$judGkBo&k27gasBxq*#q3CKhgYg"
                                                + "yhmWghHSBPq%KAoWgzrd5Uk5*s#LgTKWxp$Q!XQ*Ney@JEM8GC"
                                                + "!yrM9q";
    /**
     * 过期时间,测试使用20分钟
     */
    final static long TOKEN_EXP = 1000 * 60 * 20;

    public static String getToken(String userName) {
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                /*过期时间*/
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP))
                .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)
                .compact();
    }

    public static String parserToken(String token) throws ServletException {
        try {
            final Claims claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();
            return claims.getSubject();
        } catch (ExpiredJwtException e1) {
            throw new ServletException("token expired");
        } catch (Exception e) {
            throw new ServletException("token other exception");
        }
    }

    static void checkToken(String token) throws ServletException {
        try {
            final Claims claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e1) {
            throw new ServletException("token expired");
        } catch (Exception e) {
            throw new ServletException("token other exception");
        }
    }

    public static boolean isTokenOk(String token) {
        try {
            Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
