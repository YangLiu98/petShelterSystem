package com.example.demo.service;

import com.example.demo.entity.Message;
import org.springframework.http.ResponseEntity;

public interface MessageService {
    public ResponseEntity<String> insert(Message message);

    public ResponseEntity<Message> messageDetail(Message message);
}
