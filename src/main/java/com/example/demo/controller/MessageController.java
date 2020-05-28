package com.example.demo.controller;

import com.example.demo.entity.Message;
import com.example.demo.service.MessageService;
import com.example.demo.util.annotation.NeedToken;
import com.example.demo.util.response.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("message")
public class MessageController extends BaseController{
    @Autowired
    MessageService messageService;

    public MessageController(HttpServletRequest request) {
        super(request);
    }

    /*该方法用于发送邮件
     *操作权限：登陆用户
     */
    @NeedToken(function=NeedToken.Logged_User)
    @PostMapping("send")
    public ResponseEntity<String> send(@RequestBody Message message){
        Integer operator=getOperator();
        System.out.println(message);
        message.setSender_id(operator);
        return messageService.insert(message);
    }


    /*该方法用于展示指定id邮件的细节
     *操作权限：登陆用户
     *注意！只能查看登陆用户本人相关的邮件
     */
    @NeedToken(function=NeedToken.Logged_User)
    @PostMapping("messageDetail")
    public ResponseEntity<Message> messageDetail(@RequestBody Message message){
        Integer operator=getOperator();
        System.out.println(message);
        Message temp= messageService.messageDetail(message);
        if(temp!=null){
            if(temp.getSender_id()==operator||temp.getReceiver_id()==operator){
                return ResponseFactory.success(temp);
            }
            return ResponseFactory.unauthorized(null);
        }
        return ResponseFactory.notFound(null);
    }


    /*该方法用于标记指定id邮件已读
     *操作权限：登陆用户
     *注意！只能标记登陆用户本人相关的邮件
     */
    @NeedToken(function=NeedToken.Logged_User)
    @PostMapping("messageReaded")
    public ResponseEntity<String> messageReaded(@RequestBody Message message){
        Integer operator=getOperator();
        System.out.println(message);
        Message temp= messageService.messageDetail(message);
        if(temp!=null){
            if(temp.getReceiver_id()==operator){
                int count=messageService.read(temp);
                if(count>0){
                    return ResponseFactory.success("Success to set readed");
                }
                return ResponseFactory.badRequest("Fail to set readed");
            }
            return ResponseFactory.unauthorized("Unauthorized");
        }
        return ResponseFactory.notFound("No match message found");
    }



    /*该方法用于返回登陆用户的发送邮件列表
     *操作权限：登陆用户
     */
    @NeedToken(function=NeedToken.Logged_User)
    @PostMapping("allMyMessageSend")
    public ResponseEntity<List<Map<String, Object>>> allMyMessageSend(){
        Integer operator=getOperator();
        Message message=new Message();
        message.setSender_id(operator);
        System.out.println(message);
        return messageService.allMyMessage(message);
    }


    /*该方法用于返回登陆用户的接收邮件列表
     *操作权限：登陆用户
     */
    @NeedToken(function=NeedToken.Logged_User)
    @PostMapping("allMyMessageReceive")
    public ResponseEntity<List<Map<String, Object>>> allMyMessageReceive(){
        Integer operator=getOperator();
        Message message=new Message();
        message.setReceiver_id(operator);
        System.out.println(message);
        return messageService.allMyMessage(message);
    }

    /*该方法用于展示指定参数的邮件的细节
     *操作权限：管理员
     */
    @NeedToken(function=NeedToken.Admin)
    @PostMapping("messageDetailAdmin")
    public ResponseEntity<Message> messageDetailAdmin(@RequestBody Message message){
        System.out.println(message);
        Message temp= messageService.messageDetail(message);
        if(temp!=null){
            return ResponseFactory.success(temp);
        }
        return ResponseFactory.notFound(null);
    }

    /*该方法用于删除指定参数的邮件的细节
     *操作权限：管理员
     *返回值：删除数量
     */
    @NeedToken(function=NeedToken.Admin)
    @PostMapping("delete")
    public ResponseEntity<Integer> delete(@RequestBody Message message){
        System.out.println(message);
        return messageService.delete(message);
    }
}
