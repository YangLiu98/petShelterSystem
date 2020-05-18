package com.example.demo.Interceptor;


import com.example.demo.entity.User;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.UserService;
import com.example.demo.util.annotation.NeedToken;
import com.example.demo.util.security.SecurityUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) return true;
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //如果没有NeedToken这个注解就通过
        if (!method.isAnnotationPresent(NeedToken.class)) return true;

        NeedToken needToken = method.getAnnotation(NeedToken.class);
        if (!needToken.required()) return true;

        String token = request.getHeader("token");
        if (token == null) throw new RuntimeException("No token, please add token in your header");

        Claims claims;
        try {
            claims = SecurityUtils.parseJWT(token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String username = claims.getSubject();

        //检测用户名
        User user=new User();
        user.setUser_name(username);
        user = userService.getUserByName(user);
        if (user == null) throw new RuntimeException("No username: " + username);

        //检测权限（管理员）
        String function = needToken.function();
        if (function.equals(NeedToken.Admin) && !authenticationService.isUserAdmin(user)) throw new RuntimeException("No Permission");
        return true;
    }


}