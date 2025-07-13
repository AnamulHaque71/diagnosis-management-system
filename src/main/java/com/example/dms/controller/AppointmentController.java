package com.example.dms.controller;

import com.example.dms.model.AppointmentModel;
import com.example.dms.service.AppointmentService;
import com.example.dms.service.DoctorService;
import com.example.dms.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppointmentController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;

    @Autowired
    @Qualifier("appointmentService")
    private AppointmentService appointmentService;

    public AppointmentController(@Qualifier("appointmentService") AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/appointments/add")
    public String showAppointments(Model model) {
        model.addAttribute("title", "Book Appointment");
        model.addAttribute("content", "appointment/appointment-add :: content");
        model.addAttribute("appointment", new AppointmentModel());
        model.addAttribute("patientList", patientService.getAllPatient());
        model.addAttribute("doctorList", doctorService.getAllDoctors());

        return "layout";
    }

    @PostMapping("/api/appointments/add")
    public AppointmentModel addAppointment(@RequestBody AppointmentModel appointmentModel) {
        appointmentService.saveAppointment(appointmentModel);
        return appointmentModel;
    }
}
