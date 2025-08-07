package com.example.dms.controller;

import com.example.dms.model.TestModel;
import com.example.dms.modelDto.TestModelDto;
import com.example.dms.service.DoctorService;
import com.example.dms.service.PatientService;
import com.example.dms.service.TestService;
import com.example.dms.service.TestTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    private final String UPLOAD_DIR = new File("public/uploads").getAbsolutePath();



    @GetMapping("/reportUpload/{id}")
    public String showUploadPage(@PathVariable Long id, Model model) {
        model.addAttribute("testId", id);
        model.addAttribute("content", "test/upload-report :: content");
        return "layout";
    }

    @PostMapping("/reportUpload/{id}")
    public String uploadResultFile(@PathVariable Long id,
                                   @RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        try {
            if (!file.isEmpty()) {
                String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
                Path filepath = Paths.get(UPLOAD_DIR, filename);
                Files.copy(file.getInputStream(), filepath, StandardCopyOption.REPLACE_EXISTING);

                TestModel testModel = testService.getTestById(id);
                testModel.setResultName(filename);
                testService.saveTest(testModel);

                redirectAttributes.addFlashAttribute("message", "PDF uploaded successfully.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Upload failed: " + e.getMessage());
        }
        return "redirect:/test/show"; // or wherever your list is
    }

    @GetMapping("/viewResult/{id}")
    public ResponseEntity<Resource> viewResult(@PathVariable Long id) throws MalformedURLException {
        TestModel testModel = testService.getTestById(id);
        Path path = Paths.get(UPLOAD_DIR).resolve(testModel.getResultName());
        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping("/downloadResult/{id}")
    public ResponseEntity<Resource> downloadResult(@PathVariable Long id) throws MalformedURLException {
        TestModel testModel = testService.getTestById(id);
        Path path = Paths.get(UPLOAD_DIR).resolve(testModel.getResultName());
        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + testModel.getResultName() + "\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }



    @GetMapping("/test/add")
    public String addTest(Model model) {
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

        testModel.setTestTypeName(testTypeService.getTestTypeByTestId(testModel.getTestTypeId()).getTestName());
        testModel.setDoctorName(doctorService.getDoctorByDoctorId(testModel.getDoctorId()).getUser().getFullName());
        testModel.setPatientName(patientService.getPatientByPatientId(testModel.getPatientId()).getUser().getFullName());

        testService.saveTest(testModel);

        return "redirect:/test/show";
    }

    @GetMapping("/test/show")
    public String showTestModel(Model model) {

        model.addAttribute("title", "View Test");
        model.addAttribute("content", "test/test-list:: content");
        List<TestModelDto> testModelDtoList = new ArrayList<>();
        List<TestModel> testModelList = testService.getAllTest();
        for (TestModel test : testModelList) {
            TestModelDto testDto = new TestModelDto();
            testDto.setId(test.getId());
            testDto.setTestName(testTypeService.getTestTypeByTestId(test.getTestTypeId()).getTestName());
            testDto.setStatus(test.getStatus());
            testDto.setDoctorName(doctorService.getDoctorByDoctorId(test.getDoctorId()).getUser().getFullName());
            testDto.setPatientName(patientService.getPatientByPatientId(test.getPatientId()).getUser().getFullName());

            testDto.setResultName(test.getResultName());
            testModelDtoList.add(testDto);
        }
        model.addAttribute("testModelDtoList", testModelDtoList);

        return "layout";
    }

    @GetMapping("/test/edit/{id}")
    public String editTest(@PathVariable Long id, Model model) {
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
    public String deleteTest(@PathVariable Long id) {
        testService.deleteTest(id);
        return "redirect:/test/show";
    }
}
