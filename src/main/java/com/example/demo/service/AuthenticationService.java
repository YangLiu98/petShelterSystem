package com.example.demo.service;

import com.example.demo.entity.User;

public interface AuthenticationService {

    //根据用户昵称判断用户是否是管理员
    public Boolean isUserAdmin(User user);
}
