package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    //插入用户
    public ResponseEntity<String> insertUser(User user);

    //登入
    public ResponseEntity<String> signIn(User user);

    //根据用户昵称查找
    public ResponseEntity<String> isUserNameDumplicate(User user);

    //根据用户电话查找
    public ResponseEntity<String> isPhoneDumplicate(User user);

    //根据用户昵称查找
    public User getUserByName(User user);


}
