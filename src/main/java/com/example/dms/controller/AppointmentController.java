package com.example.dms.controller;

import com.example.dms.model.AppointmentModel;
import com.example.dms.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/appointments/add")
    public String showAppointments(Model model) {
        model.addAttribute("title", "Add Appointments");
        model.addAttribute("content", "appointment-add :: content");
        model.addAttribute("appointmentModel", new AppointmentModel());
        return "layout";
    }

    @PostMapping("/api/appointments/add")
    public AppointmentModel addAppointment(@RequestBody AppointmentModel appointmentModel) {
        appointmentService.saveAppointment(appointmentModel);
        return appointmentModel;
    }
}
