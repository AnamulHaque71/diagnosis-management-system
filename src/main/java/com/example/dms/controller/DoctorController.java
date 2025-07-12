package com.example.dms.controller;

import com.example.dms.model.DoctorModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DoctorController {
    @GetMapping("/doctor/add")
    public String showDoctorsAdd(Model model) {
        model.addAttribute("title", "Add Doctor");
        model.addAttribute("content", "doctor/doctor-add :: content");
        model.addAttribute("doctor", new DoctorModel());
        return "layout";
    }
    @PostMapping("/doctor/add")
    public String DoctorAdd() {
        return "doctor-add";
    }
}
