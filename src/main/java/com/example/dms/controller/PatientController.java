package com.example.dms.controller;

import com.example.dms.model.AppointmentModel;
import com.example.dms.model.DoctorModel;
import com.example.dms.model.PatientModel;
import com.example.dms.model.UserModel;
import com.example.dms.modelDto.AppointmentModelDto;
import com.example.dms.modelDto.DoctorModelDto;
import com.example.dms.modelDto.PatientModelDto;
import com.example.dms.repository.PatientRepository;
import com.example.dms.service.PatientService;
import com.example.dms.service.DoctorService;
import com.example.dms.service.UserService;
import com.example.dms.serviceImpl.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private UserService userService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AppointmentServiceImpl appointmentService;

    @GetMapping("/patient/show")
    public String showPatient(Model model) {
        model.addAttribute("title", "Show Patient");
        model.addAttribute("content", "patient/patients-list :: content");
        List<PatientModelDto> patients = patientService.getAllPatient();
        model.addAttribute("patients", patients);
        return "layout";
    }
    @GetMapping("/patient/add")
    public String getPatientPage(Model model) {
        model.addAttribute("title", "Add Patient");
        model.addAttribute("content", "patient/patient-add :: content");
        PatientModelDto patient = new PatientModelDto();
        model.addAttribute("patient", patient);
        return "layout";
    }

    @PostMapping("/patient/add")
    public String addPatient(@ModelAttribute("patient") PatientModelDto patientModelDto){
        UserModel user = new UserModel();

        user.setUsername(patientModelDto.getUsername());
        user.setPassword(patientModelDto.getPassword());
        user.setFullName(patientModelDto.getFullName());
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

        return "redirect:/patient/show";

    }
    @GetMapping("/patient/view/{id}")
    public String patientView(@PathVariable Long id, Model model) {


        model.addAttribute("title", "View Patient");
        model.addAttribute("content", "patient/patient-view :: content");
        model.addAttribute("patient", patientService.getPatientById(id));
        List<DoctorModel> doctorList = new ArrayList<>();
        List<AppointmentModel> appointments = appointmentService.findByPatientId(patientService.getPatientById(id).getPatientId());
        for (AppointmentModel appointment : appointments) {
            DoctorModel doctorModel = doctorService.getDoctorByDoctorId(appointment.getDoctorId());
            doctorList.add(doctorModel);
        }

//        List<DoctorModel> doctors = new ArrayList<>();
//
//        List<AppointmentModelDto> appointmentModels = appointmentService.findAllAppointments();
//        for(AppointmentModelDto appointmentModel : appointmentModels){
//            if(appointmentModel.getPatientId().equals(patientService.getPatientById(id).getPatientId()))
//            {
//                doctors.add(doctorService.getDoctorByDoctorId(appointmentModel.getDoctorId()));
//            }
//        }
        model.addAttribute("appointments", appointments);
        model.addAttribute("doctors", doctorList);
        return "layout";
    }
    @GetMapping("/patient/edit/{id}")
    public String editPatientPage(@PathVariable Long id, Model model) {
        model.addAttribute("title", "Add Patient");
        model.addAttribute("content", "patient/patient-edit :: content");
        PatientModelDto patient = new PatientModelDto();
        PatientModel p = patientService.getPatientById(id);

        patient.setId(p.getId());
        patient.setPatientId(p.getPatientId());
        patient.setUsername(p.getUser().getUsername());
        patient.setPassword(p.getUser().getPassword());
        patient.setFullName(p.getUser().getFullName());
        patient.setRole(p.getUser().getRole());
        patient.setEmail(p.getUser().getEmail());
        patient.setPhone(p.getUser().getPhone());
        patient.setAddress(p.getUser().getAddress());
        patient.setGender(p.getUser().getGender());
        patient.setDob(p.getUser().getDob());
        patient.setBloodGroup(p.getUser().getBloodGroup());
        patient.setImage(p.getUser().getImage());
        patient.setCreatedAt(p.getUser().getCreatedAt());
        patient.setVerified(p.getUser().isVerified());

        model.addAttribute("patient", patient);
        return "layout";
    }

    @PostMapping("/patient/edit/{id}")
    public String editPatient(@PathVariable Long id,@ModelAttribute("patient") PatientModelDto patientModelDto){
        UserModel user = patientService.getPatientById(id).getUser();

        user.setUsername(patientModelDto.getUsername());
        user.setPassword(patientModelDto.getPassword());
        user.setFullName(patientModelDto.getFullName());
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

        userService.saveUser(user);


        return "redirect:/patient/show";

    }
    @GetMapping("/patient/delete/{id}")
    public String deletePatient(@PathVariable Long id)
    {
        userService.deleteUser(patientService.getPatientById(id).getUser().getId());
        return "redirect:/patient/show";
    }


}