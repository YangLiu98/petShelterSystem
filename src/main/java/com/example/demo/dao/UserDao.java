package com.example.demo.dao;

import com.example.demo.entity.User;


public interface UserDao {
    public int insert(User user);

    public User signIn(User user);

    public User selectByUserName(User user);

    public User selectByPhone(User user);
}