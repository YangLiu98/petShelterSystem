package com.example.demo.controller;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Feed;
import com.example.demo.service.FeedService;
import com.example.demo.util.annotation.NeedToken;
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
@RequestMapping("feed")
public class FeedController extends BaseController{
    @Autowired
    FeedService feedService;

    public FeedController(HttpServletRequest request) {
        super(request);
    }

    /*该方法用于用户代养宠物
     *操作权限：登陆用户
     */
    @NeedToken(function=NeedToken.Logged_User)
    @PostMapping("feed")
    public ResponseEntity<String> feed(@RequestBody Feed feed){
        Integer operator=getOperator();
        System.out.println(feed);
        feed.setUser_id(operator);
        return feedService.insert(feed);
    }

    /*该方法用于展示所有用户代养情况
     *操作权限：登陆用户
     */
    @NeedToken(function=NeedToken.Logged_User)
    @PostMapping("allMyFeed")
    public ResponseEntity<List<Map<String,Object>>> allMyFeed(@RequestBody Feed feed){
        Integer operator=getOperator();
        System.out.println(feed);
        feed.setUser_id(operator);
        return feedService.allMyFeed(feed);
    }

    /*该方法用于展示用户指定id代养情况
     *操作权限：登陆用户
     */
    @NeedToken(function=NeedToken.Logged_User)
    @PostMapping("feedDetail")
    public ResponseEntity<Feed> feedDetail(@RequestBody Feed feed){
        Integer operator=getOperator();
        System.out.println(feed);
        feed.setUser_id(operator);
        return feedService.getDetail(feed);
    }

    /*该方法用于删除用户指定id代养
     *操作权限：登陆用户
     */
    @NeedToken(function=NeedToken.Logged_User)
    @PostMapping("delete")
    public ResponseEntity<String> delete(@RequestBody Feed feed){
        Integer operator=getOperator();
        System.out.println(feed);
        feed.setUser_id(operator);
        return feedService.delete(feed);
    }

    /*该方法用于审批指定id预约通过
     *操作权限：Admin
     */
    @NeedToken(function=NeedToken.Admin)
    @PostMapping("setPass")
    public ResponseEntity<String> setPass(@RequestBody Feed feed){
        System.out.println(feed);
        return feedService.update(feed);
    }

    /*该方法用于删除代养
     *操作权限：Admin
     */
    @NeedToken(function=NeedToken.Admin)
    @PostMapping("deleteAdmin")
    public ResponseEntity<Integer> deleteAdmin(@RequestBody Feed feed){
        System.out.println(feed);
        return feedService.deleteAdmin(feed);
    }
}
