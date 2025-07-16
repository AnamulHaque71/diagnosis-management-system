package com.example.dms.controller;

import com.example.dms.model.TestModel;
import com.example.dms.model.TestTypeModel;
import com.example.dms.modelDto.TestModelDto;
import com.example.dms.service.DoctorService;
import com.example.dms.service.PatientService;
import com.example.dms.service.TestService;
import com.example.dms.service.TestTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    private TestTypeService testTypeService;
    @Autowired
    private TestService testService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/test/add")
    public String addTest(Model model)
    {
        model.addAttribute("title", "Add Test");
        model.addAttribute("content", "test/test-add :: content");
        TestModel testModel = new TestModel();
        model.addAttribute("addtest", testModel);
        model.addAttribute("testTypeList", testTypeService.getAllTestType());
        model.addAttribute("doctorList", doctorService.getAllDoctors());
        model.addAttribute("patientList", patientService.getAllPatient());

        return "layout";
    }

    @PostMapping("/test/add")
    public String addTestModel(TestModel testModel) {

        Date currentDate = new Date();
        testModel.setCreatedAt(currentDate.toString());

        testService.saveTest(testModel);

        return "redirect:/test/show";
    }

    @GetMapping("/test/show")
    public String showTestModel(Model model) {

        model.addAttribute("title", "View Test");
        model.addAttribute("content", "test/test-list:: content");
        List<TestModelDto> testModelDtoList = new ArrayList<>();
        List<TestModel> testModelList = testService.getAllTest();
        for(TestModel test : testModelList) {
            TestModelDto testDto = new TestModelDto();
            testDto.setId(test.getId());
            testDto.setTestName(testTypeService.getTestTypeByTestId(test.getTestTypeId()).getTestName());
            testDto.setStatus(test.getStatus());
            testDto.setDoctorName(doctorService.getDoctorByDoctorId(test.getDoctorId()).getUser().getFullName());
            testDto.setPatientName(patientService.getPatientByPatientId(test.getPatientId()).getUser().getFullName());

            testModelDtoList.add(testDto);
        }
        model.addAttribute("testModelDtoList", testModelDtoList);

        return "layout";
    }

    @GetMapping("/test/edit/{id}")
    public String editTest(@PathVariable Long id, Model model)
    {
        model.addAttribute("title", "Edit Test");
        model.addAttribute("content", "test/test-edit :: content");
        TestModel testModel = testService.getTestById(id);
        model.addAttribute("test", testModel);
        model.addAttribute("testTypeList", testTypeService.getAllTestType());
        model.addAttribute("doctorList", doctorService.getAllDoctors());
        model.addAttribute("patientList", patientService.getAllPatient());

        return "layout";
    }

    @PostMapping("/test/edit/{id}")
    public String updateTest(@PathVariable Long id, TestModel testModel) {
        testModel.setId(id);
        testModel.setCreatedAt(testService.getTestById(id).getCreatedAt());
        testService.saveTest(testModel);
        return "redirect:/test/show";
    }
    @GetMapping("/test/delete/{id}")
    public String deleteTest(@PathVariable Long id)
    {
        testService.deleteTest(id);
        return "redirect:/test/show";
    }
}
