package com.example.dms.controller;

import com.example.dms.model.TestTypeModel;
import com.example.dms.service.TestTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    private TestTypeService testTypeService;

    @GetMapping("/api/test_type/show")
    public List<TestTypeModel> getTestTypePage() {
        return testTypeService.getAllTestType();
    }
    @PostMapping("/api/test_type/add")
    public TestTypeModel addTestType(TestTypeModel testTypeModel) {
        testTypeModel.setTest_type_id(testTypeService.generateTestTypeId());
        testTypeService.saveTestType(testTypeModel);
        return testTypeModel;
    }
}
