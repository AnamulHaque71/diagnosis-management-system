package com.example.dms.controller;

import com.example.dms.modelDto.AppointmentModelDto;
import com.example.dms.service.DoctorService;
import com.example.dms.service.PatientService;
import com.example.dms.service.TestService;
import com.example.dms.serviceImpl.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DashboardController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private AppointmentServiceImpl appointmentService;
    @Autowired
    private TestService testService;

    @GetMapping("/dashboard")
    public String showDashboard (Model model){
        model.addAttribute("title", "Dashboard");
        model.addAttribute("content", "dashboard :: content");  // **This must
        model.addAttribute("tatalPatient", patientService.totalPatient());
        model.addAttribute("totalDoctor", doctorService.totalDoctor());
        model.addAttribute("appointments", appointmentService.findAllAppointments());
        model.addAttribute("totalAppointments", appointmentService.totalAppointment());
        model.addAttribute("totalTest", testService.totalTest());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "layout";
    }
}
