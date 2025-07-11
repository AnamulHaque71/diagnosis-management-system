package com.example.dms.controller;


import com.example.dms.model.DoctorModel;
import com.example.dms.model.LabAssistantModel;
import com.example.dms.model.PatientModel;
import com.example.dms.model.UserModel;
import com.example.dms.modelDto.DoctorModelDto;
import com.example.dms.modelDto.LabAssistantModelDto;
import com.example.dms.modelDto.PatientModelDto;
import com.example.dms.repository.PatientRepository;
import com.example.dms.service.DoctorService;
import com.example.dms.service.LabAssistantService;
import com.example.dms.service.PatientService;
import com.example.dms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private LabAssistantService labAssistantService;


    @GetMapping("/api/user/show")
    public List<UserModel> getUserPage() {
        return userService.getAllUsers();
    }

    @PostMapping("/api/user/add")
    public UserModel getUserPage(@RequestBody UserModel user) {
        UserModel savedUser = userService.saveUser(user);

        return savedUser;
    }
//============================================================================================================//


    @GetMapping("/api/patient/show")
    public List<PatientModel> getPatientPage(){
        return patientService.getAllPatient();
    }
    @PostMapping("/api/patient/add")
    public PatientModel getPatientPage(@RequestBody PatientModelDto patientModelDto){
        UserModel user = new UserModel();

        user.setUsername(patientModelDto.getUsername());
        user.setPassword(patientModelDto.getPassword());
        user.setFullName(patientModelDto.getFullName());
        user.setFullName("patientModelDto.getFullName()");
        user.setRole("Patient");
        user.setEmail(patientModelDto.getEmail());
        user.setPhone(patientModelDto.getPhone());
        user.setAddress(patientModelDto.getAddress());
        user.setGender(patientModelDto.getGender());
        user.setDob(patientModelDto.getDob());
        user.setBloodGroup(patientModelDto.getBloodGroup());
        user.setImage(patientModelDto.getImage());
        user.setCreatedAt(patientModelDto.getCreatedAt());
        user.setVerified(patientModelDto.isVerified());

        Date currentDate = new Date();
        user.setCreatedAt(currentDate.toString());


        UserModel savedUser = userService.saveUser(user);

        PatientModel patient = new PatientModel();
        patient.setUser(savedUser);

        patient.setPatientId(patientService.generatePatientId());

        patientRepository.save(patient);

        return patient;

    }

//==========================================================================================================//


    @GetMapping("/api/doctor/show")
    public List<DoctorModelDto> getDoctorPage() {
        List<DoctorModelDto> doctors = doctorService.getAllDoctors();
        return doctors;
    }
    @PostMapping("/api/doctor/add")
    public DoctorModel addDoctor(@RequestBody DoctorModelDto doctorDto) {

        UserModel user = new UserModel();
        user.setUsername(doctorDto.getUsername());
        user.setPassword(doctorDto.getPassword());
        user.setFullName(doctorDto.getFullName());
        user.setRole("Doctor");
        user.setEmail(doctorDto.getEmail());
        user.setPhone(doctorDto.getPhone());
        user.setAddress(doctorDto.getAddress());
        user.setGender(doctorDto.getGender());
        user.setDob(doctorDto.getDob());
        user.setBloodGroup(doctorDto.getBloodGroup());
        user.setImage(doctorDto.getImage());
        user.setCreatedAt(doctorDto.getCreatedAt());
        user.setVerified(doctorDto.isVerified());

        Date currentDate = new Date();
        user.setCreatedAt(currentDate.toString());


        UserModel savedUser = userService.saveUser(user);

        DoctorModel doctor = new DoctorModel();

        doctor.setUser(savedUser);

        doctor.setSpecialization(doctorDto.getSpecialization());
        doctor.setDepartment(doctorDto.getDepartment());
        doctor.setDesignation(doctorDto.getDesignation());
        doctor.setExperienceYears(doctorDto.getExperienceYears());
        doctor.setDegree(doctorDto.getDegree());

        doctor.setDoctorId(doctorService.generateDoctorId());
        doctorService.saveDoctor(doctor);
        return doctor;
    }


//==========================================================================================================//


    @GetMapping("/api/lab_assistant/show")
    public List<LabAssistantModelDto> getLabAssistancePage() {
        List<LabAssistantModelDto> labAssistant = labAssistantService.getAllLabAssistant();
        return labAssistant;
    }

    @PostMapping("/api/lab_assistant/add")
    public LabAssistantModel addLabAssist(@RequestBody LabAssistantModelDto labAssistantModelDto) {

        UserModel user = new UserModel();
        user.setUsername(labAssistantModelDto.getUsername());
        user.setPassword(labAssistantModelDto.getPassword());
        user.setFullName(labAssistantModelDto.getFullName());
        user.setRole("Lab_Assistant");
        user.setEmail(labAssistantModelDto.getEmail());
        user.setPhone(labAssistantModelDto.getPhone());
        user.setAddress(labAssistantModelDto.getAddress());
        user.setGender(labAssistantModelDto.getGender());
        user.setDob(labAssistantModelDto.getDob());
        user.setBloodGroup(labAssistantModelDto.getBloodGroup());
        user.setImage(labAssistantModelDto.getImage());
        user.setCreatedAt(labAssistantModelDto.getCreatedAt());
        user.setVerified(labAssistantModelDto.isVerified());

        Date currentDate = new Date();
        user.setCreatedAt(currentDate.toString());


        UserModel savedUser = userService.saveUser(user);

        LabAssistantModel labAssistantModel = new LabAssistantModel();

        labAssistantModel.setUser(savedUser);

        labAssistantModel.setSpecialization(labAssistantModelDto.getSpecialization());
        labAssistantModel.setDepartment(labAssistantModelDto.getDepartment());
        labAssistantModel.setDesignation(labAssistantModelDto.getDesignation());
        labAssistantModel.setExperienceYears(labAssistantModelDto.getExperienceYears());
        labAssistantModel.setDegree(labAssistantModelDto.getDegree());


        Long nextNumber = labAssistantService.getNextLabAssistId(); // Implement this
        labAssistantModel.setLabAssistantId(labAssistantService.generateLabAssistantId());

        labAssistantService.saveLabAssistant(labAssistantModel);

        return labAssistantModel;
    }


}
