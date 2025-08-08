package com.example.dms.service;

import com.example.dms.model.PatientModel;
import com.example.dms.model.TestModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestService {
    List<TestModel> getAllTest();
    void saveTest(TestModel testModel);

    TestModel getTestById(Long id);

    void deleteTest(Long id);
    int totalTest();

}