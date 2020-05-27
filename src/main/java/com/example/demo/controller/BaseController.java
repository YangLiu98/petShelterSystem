package com.example.demo.controller;



import com.example.demo.util.security.SecurityUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于Controller继承获取token中对应的user_id
 */
@Component
public abstract class BaseController {
    private HttpServletRequest request;

    @Autowired
    public BaseController(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * 获取token中对应的user_id
     */
    protected Integer getOperator() {
        String token = request.getHeader("token");
        if (token == null) return null;

        Claims claims;
        try {
            claims = SecurityUtils.parseJWT(token);
        } catch (Exception e) {
            return null;
        }

        return Integer.parseInt(claims.getSubject());
    }
}
