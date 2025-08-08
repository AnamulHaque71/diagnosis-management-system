package com.example.dms.controller;

import com.example.dms.model.PatientModel;
import com.example.dms.model.UserModel;
import com.example.dms.service.PatientService;
import com.example.dms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorPageController {

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/denied")
    public String showAccessDeniedPage(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            // Get logged-in user email (username)
            String email = authentication.getName();

            // Load full user from DB (assuming userService exists)
            UserModel user = userService.findByEmail(email);
            PatientModel patientModel = patientService.findByUserId(user.getId());

            // Add user to model
            model.addAttribute("patient", patientModel);
        }
        return "access-denied";
    }
}