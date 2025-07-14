package com.example.dms.serviceImpl;

import com.example.dms.model.TestModel;
import com.example.dms.repository.TestRepository;
import com.example.dms.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;

    @Override
    public List<TestModel> getAllTest() {
        return testRepository.findAll();
    }

    @Override
    public void saveTest(TestModel testModel) {
        testRepository.save(testModel);
    }

    @Override
    public TestModel getTestById(Long id) {
        return testRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }
}