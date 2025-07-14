package com.example.dms.controller;

import com.example.dms.model.TestModel;
import com.example.dms.modelDto.TestModelDto;
import com.example.dms.service.DoctorService;
import com.example.dms.service.PatientService;
import com.example.dms.service.TestService;
import com.example.dms.service.TestTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class Testing {
//    @GetMapping("/appointments/add")
//    public String showAppointments(Model model) {
//        model.addAttribute("title", "Appointments");
//        model.addAttribute("content", "appointments :: content");  // **This must be valid**
//        return "layout";
//    }
    @GetMapping("/appointments")
    public String appointmentPage(Model model) {
        model.addAttribute("title", "Appointments");
        model.addAttribute("content", "appointments :: content");

        return "layout";
    }

    @GetMapping("/patient/view")
    public String showAppointments(Model model) {
        model.addAttribute("title", "Appointments");
        model.addAttribute("content", "patient/patient-view :: content");  // **This must be valid**
        return "layout";
    }



}
