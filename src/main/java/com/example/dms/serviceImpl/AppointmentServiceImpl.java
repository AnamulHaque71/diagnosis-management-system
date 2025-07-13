package com.example.dms.serviceImpl;

import com.example.dms.model.AppointmentModel;
import com.example.dms.repository.AppointmentRepository;
import com.example.dms.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("appointmentService")
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public List<AppointmentModel> findByPatientId(String patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    @Override
    public List<AppointmentModel> findByDoctorId(String doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<AppointmentModel> findAllAppointments() {
        return appointmentRepository.findAll();
    }


    @Override
    public void deleteById(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public void saveAppointment(AppointmentModel appointmentModel) {
        Date currentDate = new Date();
        appointmentModel.setCreatedAt(currentDate.toString());
        appointmentRepository.save(appointmentModel);
    }

    @Override
    public String generateAppointmentId() {
        long count = appointmentRepository.count(); // e.g., 3 doctors
        return "ap-" + (count + 1);
    }

    @Override
    public Long getNextAppointmentId() {
        Long count = appointmentRepository.count(); // count existing doctors
        return count + 1;
    }
}
