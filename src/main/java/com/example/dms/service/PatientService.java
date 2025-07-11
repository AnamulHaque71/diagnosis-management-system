package com.example.dms.service;

import com.example.dms.model.PatientModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {
    List<PatientModel> getAllPatient();

    void savePatient(PatientModel patient);

    PatientModel getPatientById(Long id);

    String generatePatientId();

    void deletePatient(Long id);

    Long getNextPatientId();

}
