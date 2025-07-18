package com.example.dms.controller;

import com.example.dms.service.DoctorService;
import com.example.dms.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    @GetMapping("/dashboard")
    public String showDashboard (Model model){
        model.addAttribute("title", "Dashboard");
        model.addAttribute("content", "dashboard :: content");  // **This must
        model.addAttribute("tatalPatient", patientService.totalPatient());
        model.addAttribute("totalDoctor", doctorService.totalDoctor());
        return "layout";
    }
}
