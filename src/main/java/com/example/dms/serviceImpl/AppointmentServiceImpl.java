package com.example.dms.serviceImpl;

import com.example.dms.model.AppointmentModel;
import com.example.dms.modelDto.AppointmentModelDto;
import com.example.dms.repository.AppointmentRepository;
import com.example.dms.service.AppointmentService;
import com.example.dms.service.DoctorService;
import com.example.dms.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("appointmentService")
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;

    @Override
    public List<AppointmentModel> findByPatientId(String patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    @Override
    public List<AppointmentModel> findByDoctorId(String doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<AppointmentModelDto> findAllAppointments() {

        List<AppointmentModelDto> appointmentModelDtoList = new ArrayList<>();

        List<AppointmentModel> appointmentModel = appointmentRepository.findAll();

        for(AppointmentModel appointment : appointmentModel) {
            AppointmentModelDto appointmentDto = new AppointmentModelDto();
            appointmentDto.setId(appointment.getId());
            appointmentDto.setDoctorId(appointment.getDoctorId());
            appointmentDto.setDoctorName(appointment.getDoctorName());
            appointmentDto.setStatus(appointment.getStatus());
            appointmentDto.setCreatedAt(appointment.getCreatedAt());
            appointmentDto.setAppointmentDate(appointment.getAppointmentDate());
            appointmentDto.setPatientId(appointment.getPatientId());
            appointmentDto.setPatientName(appointment.getPatientName());

            appointmentModelDtoList.add(appointmentDto);
        }


        return appointmentModelDtoList;
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
    @Override
    public AppointmentModelDto getAppointmentId(Long id) {
        AppointmentModel appointmentModel =  appointmentRepository.findById(id).orElse(null);
        AppointmentModelDto appointmentModelDto = new AppointmentModelDto();


        appointmentModelDto.setId(appointmentModel.getId());
        appointmentModelDto.setDoctorId(appointmentModel.getDoctorId());
        appointmentModelDto.setDoctorName(appointmentModel.getDoctorName());
        appointmentModelDto.setStatus(appointmentModel.getStatus());
        appointmentModelDto.setCreatedAt(appointmentModel.getCreatedAt());
        appointmentModelDto.setAppointmentDate(appointmentModel.getAppointmentDate());
        appointmentModelDto.setPatientId(appointmentModel.getPatientId());
        appointmentModelDto.setPatientName(appointmentModel.getPatientName());

        return appointmentModelDto;
    }
}
