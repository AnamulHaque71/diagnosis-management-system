package com.example.dms.controller;

import com.example.dms.model.TestTypeModel;
import com.example.dms.service.TestTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Controller
public class TestTypeController {
    @Autowired
    private TestTypeService testTypeService;

    @GetMapping("/test-type/add")
    public String addTest(Model model)
    {
        model.addAttribute("title", "Add Test Type");
        model.addAttribute("content", "test-type/test-type-add :: content");
        TestTypeModel testTypeModel = new TestTypeModel();
        model.addAttribute("testtype", testTypeModel);
        return "layout";
    }

    @PostMapping("/test-type/add")
    public String addTestModel(TestTypeModel testTypeModel) {

        Date currentDate = new Date();
        testTypeModel.setTestTypeId(testTypeService.generateTestTypeId());
        testTypeService.saveTestType(testTypeModel);

        return "redirect:/test-type/show";
    }

    @GetMapping("/test-type/show")
    public String showTestTypeModel(Model model) {
        List<TestTypeModel> testTypeModelList = testTypeService.getAllTestType();
        model.addAttribute("title", "Show Test Type");
        model.addAttribute("content", "test-type/test-type-list :: content");
        model.addAttribute("testTypeModelList",testTypeModelList);
        return "layout";
    }
}
