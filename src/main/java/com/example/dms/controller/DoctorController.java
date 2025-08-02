package com.example.dms.controller;

import com.example.dms.model.DoctorModel;
import com.example.dms.model.UserModel;
import com.example.dms.modelDto.DoctorModelDto;
import com.example.dms.service.DoctorService;
import com.example.dms.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public String addDoctor(
            @Valid @ModelAttribute("doctor") DoctorModelDto doctorModelDto,
            BindingResult result,
            Model model
    )
    {
//        if (doctorModelDto.getImage() == null || doctorModelDto.getImage().isEmpty()){
//            result.addError(new FieldError("doctorModelDto", "imageFile", "The image file is required"));
//        }
//        if(result.hasErrors()){
//
//            return "patient-add";
//        }

//        MultipartFile image = doctorModelDto.getImage();
//        Date createdAt = new Date();
//        String stroageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();
//
//        try{
//            String uploadDir = "public/images/doctors/";
//            Path uploadPath = Paths.get(uploadDir, stroageFileName);
//
//            if (!Files.exists(uploadPath)){
//                Files.createDirectories(uploadPath);
//            }
//
//            try (InputStream inputStream = image.getInputStream()) {
//                Files.copy(inputStream, Paths.get(uploadDir + stroageFileName), StandardCopyOption.REPLACE_EXISTING);
//
//            }
//        } catch (Exception e){
//            System.out.println("Exception: " + e.getMessage());
//        }

        UserModel user = new UserModel();
        user.setUsername(doctorModelDto.getUsername());
        user.setPassword(doctorModelDto.getPassword());
        user.setFullName(doctorModelDto.getFullName());
//        user.setRole("Doctor");
        user.setEmail(doctorModelDto.getEmail());
        user.setPhone(doctorModelDto.getPhone());
        user.setAddress(doctorModelDto.getAddress());
        user.setGender(doctorModelDto.getGender());
        user.setDob(doctorModelDto.getDob());
        user.setBloodGroup(doctorModelDto.getBloodGroup());
//        user.setImage(stroageFileName);
        user.setCreatedAt(doctorModelDto.getCreatedAt());
        user.setVerified(doctorModelDto.isVerified());

        Date currentDate = new Date();
        user.setCreatedAt(currentDate.toString());


        UserModel savedUser = userService.saveUser(user);

        DoctorModel doctor = new DoctorModel();

        doctor.setUser(savedUser);

        doctor.setSpecialization(doctorModelDto.getSpecialization());
        doctor.setDepartment(doctorModelDto.getDepartment());
        doctor.setDesignation(doctorModelDto.getDesignation());
        doctor.setExperienceYears(doctorModelDto.getExperienceYears());
        doctor.setDegree(doctorModelDto.getDegree());

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
