package com.example.dms.serviceImpl;

import com.example.dms.model.DoctorModel;
import com.example.dms.modelDto.DoctorModelDto;
import com.example.dms.repository.DoctorRepository;
import com.example.dms.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("doctorService")
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;


    @Override
    public List<DoctorModelDto> getAllDoctors() {
        List<DoctorModel> doctors = doctorRepository.findAll();
        List<DoctorModelDto> doctorDtoList = new ArrayList<>();
        for (DoctorModel doctor : doctors) {
            DoctorModelDto doctorDto = new DoctorModelDto();
            doctorDto.setId(doctor.getId());
            doctorDto.setDoctorId(doctor.getDoctorId());
            doctorDto.setUsername(doctor.getUser().getUsername());
            doctorDto.setPassword(doctor.getUser().getPassword());
//            doctorDto.setRole(doctor.getUser().getRole());
            doctorDto.setFullName(doctor.getUser().getFullName());
            doctorDto.setEmail(doctor.getUser().getEmail());
            doctorDto.setPhone(doctor.getUser().getPhone());
            doctorDto.setAddress(doctor.getUser().getAddress());
            doctorDto.setGender(doctor.getUser().getGender());
            doctorDto.setDob(doctor.getUser().getDob());
            doctorDto.setBloodGroup(doctor.getUser().getBloodGroup());
            doctorDto.setImageName(doctor.getUser().getImage());

            Date currentDate = new Date();
            doctorDto.setCreatedAt(currentDate.toString());

            doctorDto.setVerified(doctor.getUser().isVerified());

            doctorDto.setSpecialization(doctor.getSpecialization());
            doctorDto.setDepartment(doctor.getDepartment());
            doctorDto.setDesignation(doctor.getDesignation());
            doctorDto.setExperienceYears(doctor.getExperienceYears());
            doctorDto.setDegree(doctor.getDegree());

            doctorDtoList.add(doctorDto);

        }

        return doctorDtoList;
    }

    @Override
    public DoctorModel getDoctorByDoctorId(String doctorId) {
        DoctorModel doctor = doctorRepository.findByDoctorId(doctorId);
        return doctor;
    }

    @Override
    public String generateDoctorId() {
        long count = doctorRepository.count(); // e.g., 3 doctors
        return "dr-" + (count + 1); // -> "dr-4"
    }

    @Override
    public Long getNextDoctorId() {
        Long count = doctorRepository.count(); // count existing doctors
        return count + 1;
    }

    @Override
    public void saveDoctor(DoctorModel doctor) {
        doctorRepository.save(doctor);
    }

    @Override
    public DoctorModel getDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @Override
    public int totalDoctor() {
        return (int) doctorRepository.count();
    }

}
