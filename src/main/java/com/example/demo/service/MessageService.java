package com.example.demo.service;

import com.example.demo.entity.Message;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface MessageService {
    public ResponseEntity<String> insert(Message message);

    public Message messageDetail(Message message);

    public ResponseEntity<List<Map<String, Object>>> allMyMessage(Message message);

    public int read(Message message);

    public ResponseEntity<Integer> delete(Message message);
}
