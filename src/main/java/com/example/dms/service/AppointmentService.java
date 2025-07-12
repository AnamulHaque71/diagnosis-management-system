package com.example.dms.service;

import com.example.dms.model.AppointmentModel;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AppointmentService {
    List<AppointmentModel> findByPatientId(String patientId);
    List<AppointmentModel> findByDoctorId(String patientId);
    List<AppointmentModel> findAllAppointments();
    void deleteById(Long id);
    void saveAppointment(AppointmentModel appointmentModel);
    String generateAppointmentId();
    Long getNextAppointmentId();

}
