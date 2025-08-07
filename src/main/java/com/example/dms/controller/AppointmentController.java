package com.example.dms.controller;

import com.example.dms.model.AppointmentModel;
import com.example.dms.modelDto.AppointmentModelDto;
import com.example.dms.modelDto.DoctorModelDto;
import com.example.dms.service.AppointmentService;
import com.example.dms.service.DoctorService;
import com.example.dms.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppointmentController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;

    @Autowired
    @Qualifier("appointmentService")
    private AppointmentService appointmentService;

    public AppointmentController(@Qualifier("appointmentService") AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    @GetMapping("/appointment/show")
    public String showAppointmentPage(Model model) {
        model.addAttribute("title", "Appointments List");
        model.addAttribute("content", "appointment/appointments-list :: content");
        model.addAttribute("appointments", appointmentService.findAllAppointments());
        return "layout";
    }

    @GetMapping("/appointment/add")
    public String showAppointments(
            @RequestParam(value = "department", required = false) String department,
            Model model) {

        model.addAttribute("title", "Book Appointment");
        model.addAttribute("content", "appointment/appointment-add :: content");
        model.addAttribute("appointment", new AppointmentModel());

        model.addAttribute("patientList", patientService.getAllPatient());

        List<DoctorModelDto> allDoctors = doctorService.getAllDoctors();

        // Unique department list
        List<String> departments = allDoctors.stream()
                .map(DoctorModelDto::getDepartment)
                .distinct()
                .toList();
        model.addAttribute("departments", departments);
        model.addAttribute("selectedDepartment", department);

        // Filter doctors by selected department
        List<DoctorModelDto> filteredDoctors = (department != null && !department.isEmpty())
                ? allDoctors.stream()
                .filter(d -> d.getDepartment().equalsIgnoreCase(department))
                .toList()
                : List.of();
        model.addAttribute("doctorList", filteredDoctors);

        return "layout";
    }

    @PostMapping("/appointment/add")
    public String addAppointment(@ModelAttribute("appointment") AppointmentModel appointmentModel) {
        appointmentModel.setPatientName(patientService.getPatientByPatientId(appointmentModel.getPatientId()).getUser().getFullName());
        appointmentModel.setDoctorName(doctorService.getDoctorByDoctorId(appointmentModel.getDoctorId()).getUser().getFullName());
        appointmentService.saveAppointment(appointmentModel);
        return "redirect:/appointment/show";
    }

    @GetMapping("/appointment/edit/{id}")
    public String editAppointment(
            @RequestParam(value = "department", required = false) String department,
            @PathVariable Long id, Model model){
        model.addAttribute("title", "Edit Appointment");
        model.addAttribute("content", "appointment/appointment-edit :: content");
//        model.addAttribute("appointment", new AppointmentModel());

        model.addAttribute("patientList", patientService.getAllPatient());
        model.addAttribute("doctorList", doctorService.getAllDoctors());
        AppointmentModelDto appointmentModelDto = appointmentService.getAppointmentId(id);
        model.addAttribute("appointment", appointmentModelDto);

        return "layout";
    }
    @PostMapping("/appointment/edit/{id}")
    public String updateAppointment(@PathVariable Long id, @ModelAttribute("appointment") AppointmentModel appointmentModel) {
        appointmentModel.setId(id);
        appointmentModel.setPatientName(patientService.getPatientByPatientId(appointmentModel.getPatientId()).getUser().getFullName());
        appointmentModel.setDoctorName(doctorService.getDoctorByDoctorId(appointmentModel.getDoctorId()).getUser().getFullName());
        appointmentService.saveAppointment(appointmentModel);
        return "redirect:/appointment/show";
    }

}
