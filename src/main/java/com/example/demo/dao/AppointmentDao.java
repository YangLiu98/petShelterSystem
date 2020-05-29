package com.example.demo.dao;

import com.example.demo.entity.Appointment;

import java.util.List;
import java.util.Map;

public interface AppointmentDao {
    public int insert(Appointment appointment);

    public List<Map<String,Object>> allMyAppointment(Appointment appointment);

    public Appointment appointmentDetail(Appointment appointment);

    public int delete(Appointment appointment);

    public int update(Appointment appointment);

    public int deleteAdmin(Appointment appointment);

    public List<Map<String,Object>> select(Appointment appointment);
}
