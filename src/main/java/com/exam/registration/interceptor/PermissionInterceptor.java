package com.exam.registration.interceptor;

import com.exam.registration.annotation.RequireRoles;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yhf
 * @classname PermissionInterceptor
 * @description TODO
 * @date 2020/2/2
 **/
public class PermissionInterceptor extends HandlerInterceptorAdapter {
    // 在调用方法之前执行拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 将handler强转为HandlerMethod, 前面已经证实这个handler就是HandlerMethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取出要调用的方法
        Method method = handlerMethod.getMethod();
        // 获取出方法上的Access注解
        RequireRoles requireRoles = method.getAnnotation(RequireRoles.class);
        if (requireRoles == null) {
            // 如果注解为null, 说明不需要拦截, 直接放过
            return true;
        }
        if (requireRoles.value().length > 0) {
            // 如果权限配置不为空, 则取出配置值
            String[] authorities = requireRoles.value();
            Set<String> authSet = new HashSet<>();
            for (String authority : authorities) {
                // 将权限加入一个set集合中
                authSet.add(authority);
            }
            // 这里我为了方便是直接参数传入权限, 在实际操作中应该是从参数中获取用户Id
            // 到数据库权限表中查询用户拥有的权限集合, 与set集合中的权限进行对比完成权限校验
            String role = (String) request.getAttribute("role");
            System.out.println(role);
            System.out.println(authSet.toString());
            if (StringUtils.isNotBlank(role)) {
                if (authSet.contains(role)) {
                    // 校验通过返回true, 否则拦截请求
                    return true;
                }
            }
            throw new RuntimeException("无访问权限！");
        }
        return false;
    }

}
