package com.exam.registration.security;

import com.exam.registration.util.RedisUtils;
import io.jsonwebtoken.Claims;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yhf
 * @classname JwtInterceptor
 **/
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ServletException("token invalid Authorization header, 请重新登陆");
        }
        //取得token
        String token = authHeader.substring(7);
        try {
            JwtUtil.checkToken(token);
            Claims claims = JwtUtil.parserToken(token);
            String role = (String) claims.get("role");
            request.setAttribute("role", role);
            String userId = claims.getSubject();
            RedisTemplate redisTemplate = RedisUtils.getRedisTemplate();
            if ("student".equals(role)) {
                String savedToken = (String) redisTemplate.opsForValue()
                                        .get(RedisUtils.STUDENT_TOKEN_PREFIX + userId);
                if (!savedToken.equals(token)) {
                    throw new Exception("token 在其他地方登录");
                }
                request.setAttribute("studentId", userId);
            } else {
                String savedToken = (String) redisTemplate.opsForValue()
                        .get(RedisUtils.ADMIN_TOKEN_PREFIX + userId);
                if (!savedToken.equals(token)) {
                    throw new Exception("token 在其他地方登录");
                }
                request.setAttribute("adminId", userId);
            }
            return true;
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }
}
