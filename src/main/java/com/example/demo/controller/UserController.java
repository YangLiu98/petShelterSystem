package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.util.annotation.NeedToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    //用户注册
    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody User user){
//                System.out.println(user);
//        return null;
        return userService.insertUser(user);
    }

    //用户登录
    @PostMapping("signIn")
    public ResponseEntity<String> signIn(@RequestBody User user){
       return userService.signIn(user);
//                System.out.println(user);
//        return null;
    }

    //检查用户名是否已存在
    @PostMapping("/isUserNameDumplicate")
    public ResponseEntity<String> isUserNameDumplicate(@RequestBody User user){
        return userService.isUserNameDumplicate(user);
    }

    //检查用户电话是否已存在
    @PostMapping("/isPhoneDumplicate")
    public ResponseEntity<String> isPhoneDumplicate(@RequestBody User user){
        return userService.isPhoneDumplicate(user);
    }

    //测试token功能
    @NeedToken(function=NeedToken.Logged_User)
    @PostMapping("/testToken")
    public ResponseEntity<String> testToken(){
        return new ResponseEntity<String>("pass",OK);
    }
    //测试token功能2
    @NeedToken(function=NeedToken.Admin)
    @PostMapping("/testToken2")
    public ResponseEntity<String> testToken2(){
        return new ResponseEntity<String>("pass",OK);
    }
}
