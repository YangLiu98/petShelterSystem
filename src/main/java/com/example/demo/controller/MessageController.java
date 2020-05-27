package com.example.demo.controller;

import com.example.demo.entity.Message;
import com.example.demo.service.MessageService;
import com.example.demo.util.annotation.NeedToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("message")
public class MessageController extends BaseController{
    @Autowired
    MessageService messageService;

    public MessageController(HttpServletRequest request) {
        super(request);
    }

    @NeedToken(function=NeedToken.Logged_User)
    @PostMapping("send")
    public ResponseEntity<String> send(@RequestBody Message message){
        Integer operator=getOperator();
        System.out.println(message);
        message.setSender_id(operator);
        return messageService.insert(message);
    }

    @NeedToken(function=NeedToken.Logged_User)
    @PostMapping("messageDetail")
    public ResponseEntity<Message> messageDetail(@RequestBody Message message){
        System.out.println(message);
        return messageService.messageDetail(message);
    }
}
