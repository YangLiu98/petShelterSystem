package com.example.demo.service;


import com.example.demo.dao.AppointmentDao;
import com.example.demo.entity.Appointment;
import com.example.demo.util.response.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    private AppointmentDao appointmentDao;

    @Override
    public ResponseEntity<String> insert(Appointment appointment) {
        int count=appointmentDao.insert(appointment);
        if(count>0){
            return ResponseFactory.success("Success to insert");
        }
        return ResponseFactory.badRequest("Fail to insert");
    }

    @Override
    public ResponseEntity<List<Map<String,Object>>> allMyAppoint(Appointment appointment) {
        return ResponseFactory.success(appointmentDao.allMyAppointment(appointment));
    }

    @Override
    public ResponseEntity<Appointment> appointmentDetail(Appointment appointment) {
        Appointment temp=appointmentDao.appointmentDetail(appointment);
        if(temp!=null){
            return ResponseFactory.success(temp);
        }
        return ResponseFactory.notFound(null);
    }

    @Override
    public ResponseEntity<String> delete(Appointment appointment) {
        int count=appointmentDao.delete(appointment);
        if(count>0){
            return ResponseFactory.success("Success to delete");
        }
        return ResponseFactory.badRequest("Fail to delete");
    }

    @Override
    public ResponseEntity<String> update(Appointment appointment) {
        int count=appointmentDao.update(appointment);
        if(count>0){
            return ResponseFactory.success("Success to update");
        }
        return ResponseFactory.badRequest("Fail to update");
    }

    @Override
    public ResponseEntity<Integer> deleteAdmin(Appointment appointment) {
        return ResponseFactory.success(appointmentDao.deleteAdmin(appointment));
    }

    @Override
    public ResponseEntity<List<Map<String, Object>>> select(Appointment appointment) {
        return ResponseFactory.success(appointmentDao.select(appointment));
    }
}
