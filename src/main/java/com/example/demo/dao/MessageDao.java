package com.example.demo.dao;

import com.example.demo.entity.Message;

import java.util.List;
import java.util.Map;

public interface MessageDao {
    public int insert(Message message);

    public Message selectByMessageId(Message message);

    public List<Map<String,Object>> select(Message message);

    public int read(Message message);

    public int delete(Message message);
}
