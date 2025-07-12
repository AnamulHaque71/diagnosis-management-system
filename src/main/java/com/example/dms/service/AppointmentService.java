package com.example.dms.service;

import com.example.dms.model.AppointmentModel;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface AppointmentService {
    List<AppointmentModel> findByPatientId(Long patientId);
    List<AppointmentModel> findByDoctorId(Long patientId);
    List<AppointmentModel> findAll();
    void deleteById(Long id);
    void saveAppointment(AppointmentModel appointmentModel);
    String generateAppointmentId();
    Long getNextAppointmentId();

}
