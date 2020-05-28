package com.example.demo.service;

import com.example.demo.dao.MessageDao;
import com.example.demo.entity.Message;
import com.example.demo.util.response.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
    public Message messageDetail(Message message) {
        return messageDao.selectByMessageId(message);
    }

    @Override
    public ResponseEntity<List<Map<String, Object>>> allMyMessage(Message message) {
        List<Map<String, Object>> result=messageDao.select(message);
        if(result.size()>0){
            return ResponseFactory.success(messageDao.select(message));
        }
        return ResponseFactory.notFound(null);
    }

    @Override
    public int read(Message message) {
        return messageDao.read(message);
    }

    @Override
    public ResponseEntity<Integer> delete(Message message) {
        int count=messageDao.delete(message);
        if(count>0){
            return ResponseFactory.success(count);
        }
        return ResponseFactory.notFound(count);
    }
}
