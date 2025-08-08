package com.example.dms.service;

import com.example.dms.model.PatientModel;
import com.example.dms.modelDto.PatientModelDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {
    List<PatientModelDto> getAllPatient();

    PatientModel getPatientByPatientId(String name);
    PatientModel findByUserId(Long id);
    void savePatient(PatientModel patient);

    PatientModel getPatientById(Long id);

    String generatePatientId();

    void deletePatient(Long id);

    Long getNextPatientId();

    int totalPatient();

}
