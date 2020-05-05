package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    //插入用户
    public ResponseEntity<String> insertUser(User user);

    //登入
    public ResponseEntity<String> signIn(User user);

}
