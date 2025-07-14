package com.example.dms.serviceImpl;

import com.example.dms.model.TestTypeModel;
import com.example.dms.repository.TestTypeRepository;
import com.example.dms.service.TestTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestTypeServiceImpl implements TestTypeService {

    @Autowired
    private TestTypeRepository testTypeRepository;

    @Override
    public TestTypeModel getTestTypeByTestId(String testTypeId) {
        return testTypeRepository.findByTestTypeId(testTypeId);
    }

    @Override
    public List<TestTypeModel> getAllTestType() {
        return testTypeRepository.findAll();
    }

    @Override
    public void saveTestType(TestTypeModel testTypeModel) {
        testTypeRepository.save(testTypeModel);
    }

    @Override
    public TestTypeModel getTestTypeById(Long id) {
        return testTypeRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteTestType(Long id) {
        testTypeRepository.deleteById(id);
    }

    @Override
    public String generateTestTypeId()
    {
        long count = testTypeRepository.count();
        return "CBC-" + (count + 1);
    }

    @Override
    public Long getNextTestTypeId()
    {
        long count = testTypeRepository.count();
        return count + 1;
    }
}
