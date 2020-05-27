package com.example.demo.dao;

import com.example.demo.entity.Message;

public interface MessageDao {
    public int insert(Message message);

    public Message select(Message message);
}
