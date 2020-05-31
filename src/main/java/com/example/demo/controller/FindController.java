package com.example.demo.controller;

import com.example.demo.entity.Find;
import com.example.demo.service.FindService;
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
@RequestMapping("find")
public class FindController extends BaseController{
    @Autowired
    FindService findService;

    public FindController(HttpServletRequest request) {
        super(request);
    }

    /*该方法用于用户寻找遗失宠物
     *操作权限：登陆用户
     */
    @NeedToken(function=NeedToken.Logged_User)
    @PostMapping("find")
    public ResponseEntity<String> find(@RequestBody Find find){
        Integer operator=getOperator();
        System.out.println(find);
        find.setUser_id(operator);
        return findService.insert(find);
    }

    /*该方法用于展示所有用户寻找遗失宠物情况
     *操作权限：登陆用户
     */
    @NeedToken(function=NeedToken.Logged_User)
    @PostMapping("allMyFind")
    public ResponseEntity<List<Map<String,Object>>> allMyFind(@RequestBody Find find){
        Integer operator=getOperator();
        System.out.println(find);
        find.setUser_id(operator);
        return findService.allMyFind(find);
    }

    /*该方法用于展示用户指定id寻找遗失宠物情况
     *操作权限：登陆用户
     */
    @NeedToken(function=NeedToken.Logged_User)
    @PostMapping("findDetail")
    public ResponseEntity<Find> findDetail(@RequestBody Find find){
        Integer operator=getOperator();
        System.out.println(find);
        find.setUser_id(operator);
        return findService.getDetail(find);
    }

    /*该方法用于删除用户指定id的寻求
     *操作权限：登陆用户
     */
    @NeedToken(function=NeedToken.Logged_User)
    @PostMapping("delete")
    public ResponseEntity<String> delete(@RequestBody Find find){
        Integer operator=getOperator();
        System.out.println(find);
        find.setUser_id(operator);
        return findService.delete(find);
    }
}
