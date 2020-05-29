package com.example.demo.controller;

import com.example.demo.entity.Appointment;
import com.example.demo.service.AppointmentService;
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
@RequestMapping("appointment")
public class AppointmentController extends BaseController{

    @Autowired
    AppointmentService appointmentService;

    public AppointmentController(HttpServletRequest request) {
        super(request);
    }

    /*该方法用于用户预约
     *操作权限：登陆用户
     */
    @NeedToken(function=NeedToken.Logged_User)
    @PostMapping("appoint")
    public ResponseEntity<String> appoint(@RequestBody Appointment appointment){
        Integer operator=getOperator();
        System.out.println(appointment);
        appointment.setUser_id(operator);
        return appointmentService.insert(appointment);
    }

    /*该方法用于展示所有用户预约
     *操作权限：登陆用户
     */
    @NeedToken(function=NeedToken.Logged_User)
    @PostMapping("allMyAppoint")
    public ResponseEntity<List<Map<String,Object>>> allMyAppoint(@RequestBody Appointment appointment){
        Integer operator=getOperator();
        System.out.println(appointment);
        appointment.setUser_id(operator);
        return appointmentService.allMyAppoint(appointment);
    }

    /*该方法用于展示用户指定id预约
     *操作权限：登陆用户
     */
    @NeedToken(function=NeedToken.Logged_User)
    @PostMapping("appointmenDetail")
    public ResponseEntity<Appointment> appointmenDetail(@RequestBody Appointment appointment){
        Integer operator=getOperator();
        System.out.println(appointment);
        appointment.setUser_id(operator);
        return appointmentService.appointmentDetail(appointment);
    }

    /*该方法用于展示用户指定id预约
     *操作权限：登陆用户
     */
    @NeedToken(function=NeedToken.Logged_User)
    @PostMapping("delete")
    public ResponseEntity<String> delete(@RequestBody Appointment appointment){
        Integer operator=getOperator();
        System.out.println(appointment);
        appointment.setUser_id(operator);
        return appointmentService.delete(appointment);
    }

    /*该方法用于审批指定id预约通过
     *操作权限：Admin
     */
    @NeedToken(function=NeedToken.Admin)
    @PostMapping("setPass")
    public ResponseEntity<String> setPass(@RequestBody Appointment appointment){
        System.out.println(appointment);
        return appointmentService.update(appointment);
    }

    /*该方法用于删除预约
     *操作权限：Admin
     */
    @NeedToken(function=NeedToken.Admin)
    @PostMapping("deleteAdmin")
    public ResponseEntity<Integer> deleteAdmin(@RequestBody Appointment appointment){
        System.out.println(appointment);
        return appointmentService.deleteAdmin(appointment);
    }

    /*该方法用于查找预约
     *操作权限：Admin
     */
    @NeedToken(function=NeedToken.Admin)
    @PostMapping("select")
    public ResponseEntity<List<Map<String,Object>>> select(@RequestBody Appointment appointment){
        System.out.println(appointment);
        return appointmentService.select(appointment);
    }
}
