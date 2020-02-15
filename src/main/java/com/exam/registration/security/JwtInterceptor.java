package com.exam.registration.security;

import com.exam.registration.util.MsgUtils;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yhf
 * @classname JwtInterceptor
 * @description TODO
 * @date 2020/1/9
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
            if ("student".equals(role)) {
                request.setAttribute("idCardNumber", claims.getSubject());
            } else {
                request.setAttribute("adminId", claims.getSubject());
            }
            return true;
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }
}
