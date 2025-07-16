package com.example.dms.serviceImpl;

import com.example.dms.model.PatientModel;
import com.example.dms.modelDto.PatientModelDto;
import com.example.dms.repository.PatientRepository;
import com.example.dms.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

     @Autowired
     private PatientRepository patientRepository;

     @Override
     public List<PatientModelDto> getAllPatient() {
         List<PatientModelDto> patientModelDtoList = new ArrayList<>();
         List<PatientModel> patientModel = patientRepository.findAll();

         for(PatientModel patient : patientModel) {
             PatientModelDto patientModelDto = new PatientModelDto();
             patientModelDto.setId(patient.getId());
             patientModelDto.setPatientId(patient.getPatientId());
             patientModelDto.setUsername(patient.getUser().getUsername());
             patientModelDto.setPassword(patient.getUser().getPassword());
             patientModelDto.setFullName(patient.getUser().getFullName());
             patientModelDto.setEmail(patient.getUser().getEmail());
             patientModelDto.setPhone(patient.getUser().getPhone());
             patientModelDto.setAddress(patient.getUser().getAddress());
             patientModelDto.setGender(patient.getUser().getGender());
             patientModelDto.setDob(patient.getUser().getDob());
             patientModelDto.setBloodGroup(patient.getUser().getBloodGroup());
             patientModelDto.setImage(patient.getUser().getImage());

             Date currentDate = new Date();
             patientModelDto.setCreatedAt(currentDate.toString());

             patientModelDtoList.add(patientModelDto);
         }

         return patientModelDtoList;
     }

    @Override
    public PatientModel getPatientByPatientId(String name) {
        return patientRepository.findByPatientId(name);
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

    @Override
    public int totalPatient() {
        return patientRepository.findAll().size();
    }
}
