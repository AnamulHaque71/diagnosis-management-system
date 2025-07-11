package com.example.dms.serviceImpl;

import com.example.dms.model.PatientModel;
import com.example.dms.repository.PatientRepository;
import com.example.dms.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

     @Autowired
     private PatientRepository patientRepository;

     @Override
     public List<PatientModel> getAllPatient() {
         List<PatientModel> patients =  patientRepository.findAll();
            return patientRepository.findAll();
     }

     @Override
     public void savePatient(PatientModel patient) {
            patientRepository.save(patient);
     }

     @Override
     public PatientModel getPatientById(Long id) {
            return patientRepository.findById(id).orElse(null);
     }

     @Override
     public String generatePatientId(){
         long count = patientRepository.count(); // e.g., 3 doctors
         return "pt-" + (count + 1);
     }

     @Override
     public void deletePatient(Long id) {
         patientRepository.deleteById(id);
     }

    @Override
    public Long getNextPatientId() {
        return patientRepository.findMaxPatientIdNumber().orElse(201L) + 1;
    }
}
