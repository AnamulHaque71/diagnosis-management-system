package com.example.dms.controller;

import com.example.dms.model.DoctorModel;
import com.example.dms.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DoctorController {
    @Autowired
    @Qualifier("doctorService")
    private DoctorService doctorService;

    public DoctorController(@Qualifier("doctorService") DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/doctor/add")
    public String showDoctorsAdd(Model model) {
        model.addAttribute("title", "Add Doctor - DMS");
        model.addAttribute("content", "doctor/doctor-add :: content");
        model.addAttribute("doctor", new DoctorModel());

        return "layout";
    }
    @PostMapping("/doctor/add")
    public String DoctorAdd(@ModelAttribute("doctor") DoctorModel doctorModel) {
        doctorModel.setId(1);
        doctorService.saveDoctor(doctorModel);
        return "doctor-add";
    }
}
