package com.example.dms.controller;

import com.example.dms.model.PatientModel;
import com.example.dms.model.RoleModel;
import com.example.dms.model.UserModel;
import com.example.dms.modelDto.PatientModelDto;
import com.example.dms.modelDto.UserDto;
import com.example.dms.repository.RoleModelRepository;
import com.example.dms.service.PatientService;
import com.example.dms.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
@ControllerAdvice
public class AuthController {

    @Autowired
    private UserService userService; // Assuming you have a UserService to handle user registration
    @Autowired
    private PatientService patientService;
    @Autowired
    private RoleModelRepository roleModelRepository;

    @GetMapping("/registration")
    public String openRegistrationPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registerUser(
            @Valid
            @ModelAttribute("patientModelDto") PatientModelDto patientModelDto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

//        pks@gmail.com
        boolean emailExists = userService.emailExists(patientModelDto.getEmail());
        if (emailExists) {
            result.rejectValue("email", "error.UserDto", "Email already exists. Please use a different email.");
            return "auth/registration";
        } else{
            UserModel user = new UserModel();
            user.setFullName(patientModelDto.getFullName()); // match your entity field name
            user.setEmail(patientModelDto.getEmail());
            user.setPassword(patientModelDto.getPassword());
            user.setUsername(patientModelDto.getUsername());
            user.setPhone(patientModelDto.getPhone());
            user.setAddress(patientModelDto.getAddress());
            user.setGender(patientModelDto.getGender());
            user.setDob(patientModelDto.getDob());
            user.setBloodGroup(patientModelDto.getBloodGroup());
            user.setImage(patientModelDto.getImageName());

// Default values
            user.setCreatedAt(new Date().toString());
            user.setVerified(false);

// Optional: Assign default role
            RoleModel defaultRole = roleModelRepository.findByRoleName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Default role not found"));
            user.getRoles().add(defaultRole);

            PatientModel patientModel = new PatientModel();
            patientModel.setUser(user);
            patientModel.setPatientId(patientService.generatePatientId());



            boolean status = userService.registerUser(user);
            patientService.savePatient(patientModel);

            if (status) {
                redirectAttributes.addFlashAttribute("message", "Registration successful! Please log in.");
                return "redirect:/login"; // use redirect, not view name
            } else {
                redirectAttributes.addFlashAttribute("error", "Registration failed. Please try again.");
                return "redirect:/registration";
            }
        }
    }

    @GetMapping("/login")
    public String openLoginPage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid email or password. Please try again.");
        }
        if (logout != null) {
            model.addAttribute("logoutMessage", "You have been logged out successfully.");
        }
        model.addAttribute("user", new UserModel());
        return "auth/login";
    }


//    @PostMapping("/login")
//    public String LoginUser(
//            @ModelAttribute("user") User user,
//            Model model,
//            RedirectAttributes redirectAttributes
//    ){
//        User validUser = userService.loginUser(user.getEmail(), user.getPassword());
//        System.out.println(validUser==null);
//        if(validUser != null) {
//            redirectAttributes.addFlashAttribute("message", "Login successful! Welcome, " + validUser.getName() + "!");
//            return "redirect:/products";
//        } else {
//            model.addAttribute("error", "Invalid email or password. Please try again.");
//            return "auth/login";
//        }
//    }
}