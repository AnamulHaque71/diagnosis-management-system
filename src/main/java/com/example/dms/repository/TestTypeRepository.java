package com.example.dms.repository;

import com.example.dms.model.TestTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestTypeRepository extends JpaRepository<TestTypeModel,Long> {
    TestTypeModel findByTestTypeId(String testTypeId);
}
