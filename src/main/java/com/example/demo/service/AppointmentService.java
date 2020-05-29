package com.example.demo.service;

import com.example.demo.entity.Appointment;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface AppointmentService {
    public ResponseEntity<String> insert(Appointment appointment);

    public ResponseEntity<List<Map<String,Object>>> allMyAppoint(Appointment appointment);

    public ResponseEntity<Appointment> appointmentDetail(Appointment appointment);

    public ResponseEntity<String> delete(Appointment appointment);

    public ResponseEntity<String> update(Appointment appointment);

    public ResponseEntity<Integer> deleteAdmin(Appointment appointment);

    public ResponseEntity<List<Map<String,Object>>> select(Appointment appointment);
}
