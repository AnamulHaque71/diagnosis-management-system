package com.example.dms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
