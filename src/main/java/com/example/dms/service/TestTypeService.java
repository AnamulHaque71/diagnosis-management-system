package com.example.dms.service;

import com.example.dms.model.TestTypeModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestTypeService {
    TestTypeModel getTestTypeByTestId(String testTypeId);
    List<TestTypeModel> getAllTestType();
    void saveTestType(TestTypeModel testTypeModel);
    TestTypeModel getTestTypeById(Long id);
    void deleteTestType(Long id);
    String generateTestTypeId();
    Long getNextTestTypeId();
}
