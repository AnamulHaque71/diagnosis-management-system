package com.example.dms.controller;

import com.example.dms.model.DoctorModel;
import com.example.dms.model.UserModel;
import com.example.dms.modelDto.DoctorModelDto;
import com.example.dms.service.DoctorService;
import com.example.dms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class DoctorController {

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("doctorService")
    private DoctorService doctorService;

    public DoctorController(@Qualifier("doctorService") DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/doctor/show")
    public String showDoctor(Model model) {
        model.addAttribute("title", "Show Doctor");
        model.addAttribute("content", "doctor/doctors-list :: content");
        List<DoctorModelDto> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "layout";

    }

    @GetMapping("/doctor/add")
    public String getDoctorPage(Model model) {
        model.addAttribute("title", "Add Doctor - DMS");
        model.addAttribute("content", "doctor/doctor-add :: content");
        DoctorModelDto doctor = new DoctorModelDto();
        model.addAttribute("doctor", doctor);

        return "layout";
    }
    @PostMapping("/doctor/add")
    public String addDoctor(DoctorModelDto doctorDto) {

        UserModel user = new UserModel();
        user.setUsername(doctorDto.getUsername());
        user.setPassword(doctorDto.getPassword());
        user.setFullName(doctorDto.getFullName());
        user.setRole("Doctor");
        user.setEmail(doctorDto.getEmail());
        user.setPhone(doctorDto.getPhone());
        user.setAddress(doctorDto.getAddress());
        user.setGender(doctorDto.getGender());
        user.setDob(doctorDto.getDob());
        user.setBloodGroup(doctorDto.getBloodGroup());
        user.setImage(doctorDto.getImage());
        user.setCreatedAt(doctorDto.getCreatedAt());
        user.setVerified(doctorDto.isVerified());

        Date currentDate = new Date();
        user.setCreatedAt(currentDate.toString());


        UserModel savedUser = userService.saveUser(user);

        DoctorModel doctor = new DoctorModel();

        doctor.setUser(savedUser);

        doctor.setSpecialization(doctorDto.getSpecialization());
        doctor.setDepartment(doctorDto.getDepartment());
        doctor.setDesignation(doctorDto.getDesignation());
        doctor.setExperienceYears(doctorDto.getExperienceYears());
        doctor.setDegree(doctorDto.getDegree());

        doctor.setDoctorId(doctorService.generateDoctorId());
        doctorService.saveDoctor(doctor);

        return "redirect:/doctor/show";
    }
    @GetMapping("/doctors/by-department")
    @ResponseBody
    public List<Map<String, String>> getDoctorsByDepartment(@RequestParam String department) {
        List<DoctorModelDto> filtered = doctorService.getAllDoctors().stream()
                .filter(d -> d.getDepartment().equalsIgnoreCase(department))
                .toList();

        List<Map<String, String>> result = new ArrayList<>();
        for (DoctorModelDto doctor : filtered) {
            Map<String, String> map = new HashMap<>();
            map.put("doctorId", doctor.getDoctorId());
            map.put("fullName", doctor.getFullName());
            result.add(map);
        }
        return result;
    }

    @GetMapping("/doctor/delete/{id}")
    public String deleteDoctor(@PathVariable Long id){
        userService.deleteUser(doctorService.getDoctorById(id).getUser().getId());
        return "redirect:/doctor/show";
    }


}
