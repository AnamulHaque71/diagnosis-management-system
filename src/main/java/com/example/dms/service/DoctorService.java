package com.example.dms.service;

import com.example.dms.model.DoctorModel;
import com.example.dms.modelDto.DoctorModelDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DoctorService {
    List<DoctorModelDto> getAllDoctors();
    String generateDoctorId();
    Long getNextDoctorId();
    void saveDoctor(DoctorModel doctor);

}
