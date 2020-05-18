package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    private UserDao userDao;

    @Override
    public Boolean isUserAdmin(User user) {
        if(userDao.selectAdminByName(user)==null){
            return false;
        }
        return true;
    }
}
