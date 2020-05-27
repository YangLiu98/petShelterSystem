package com.example.demo.service;

import com.example.demo.dao.MessageDao;
import com.example.demo.entity.Message;
import com.example.demo.util.response.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageServiceImpl implements MessageService{
    @Autowired
    MessageDao messageDao;
    @Override
    public ResponseEntity<String> insert(Message message) {
        int count=messageDao.insert(message);
        if(count!=0){
            return ResponseFactory.success("Success to insert");
        }
        return ResponseFactory.badRequest("Fail to insert");
    }

    @Override
    public ResponseEntity<Message> messageDetail(Message message) {
        Message temp=messageDao.select(message);
        if(temp!=null){
            return ResponseFactory.success(temp);
        }
        return ResponseFactory.badRequest(null);
    }
}
