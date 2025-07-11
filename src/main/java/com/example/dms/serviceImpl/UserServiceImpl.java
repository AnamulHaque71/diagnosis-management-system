package com.example.dms.serviceImpl;

import com.example.dms.model.UserModel;
import com.example.dms.repository.DoctorRepository;
import com.example.dms.repository.PatientRepository;
import com.example.dms.repository.UserRepository;
import com.example.dms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserModel saveUser(UserModel user) {
        Date currentDate = new Date();
        user.setCreatedAt(currentDate.toString());
        return userRepository.save(user);
    }

    @Override
    public UserModel getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

//    @Override
//    public UserModel registerUser(UserModel user) {
//        UserModel savedUser = userRepository.save(user);
//
//        switch (user.getRole()) {
//            case "Patient" -> {
//                PatientModel patient = new PatientModel();
//                patient.setUser(savedUser);
//
//                Long nextNumber = patientRepository.findMaxPatientIdNumber().orElse(109L) + 1;
//                patient.setPatientId("popular-" + nextNumber);
//
//                patientRepository.save(patient);
//            }
//            case "Doctor" -> {
//                DoctorModel doctor = new DoctorModel();
//                doctor.setUser(savedUser);
//                doctor.setDepartment("Cardiology"); // Set from input
//                doctor.setDesignation("Consultant");
//                doctorRepository.save(doctor);
//            }
//
//            // Future roles:
//
//            // case "Lab Assistant" -> { createLab(); }
//        }
//
//        return savedUser;
//    }


}
