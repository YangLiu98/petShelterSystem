package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.util.response.ResponseFactory;
import com.example.demo.util.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements  UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public ResponseEntity<String> insertUser(User user) {
        int count = userDao.insert(user);
        if(count==0){
            return ResponseFactory.badRequest(user.getUser_name());
        }
        return ResponseFactory.success(user.getUser_name());
    }

    @Override
    public ResponseEntity<String> signIn(User user) {
        if(userDao.signIn(user)!=null){
            return ResponseFactory.success(SecurityUtils.getToken(user.getUser_id()));
        }
        return ResponseFactory.unauthorized(user.getUser_id().toString());
    }

    @Override
    public ResponseEntity<String> isUserNameDumplicate(User user) {
        if(userDao.selectByUserName(user)!=null){
            return ResponseFactory.badRequest("user_name has existed");
        }
        return ResponseFactory.success("user_name not found");
    }

    @Override
    public ResponseEntity<String> isPhoneDumplicate(User user) {
        if(userDao.selectByPhone(user)!=null){
            return ResponseFactory.badRequest("phone has existed");
        }
        return ResponseFactory.success("phone not found");
    }

    @Override
    public User getUserByName(User user) {
        return userDao.selectByUserName(user);
    }

    @Override
    public User getUserById(User user) {
        System.out.println(user);
        return userDao.selectByUserId(user);
    }
}
