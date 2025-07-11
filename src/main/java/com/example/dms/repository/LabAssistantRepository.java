package com.example.dms.repository;

import com.example.dms.model.LabAssistantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabAssistantRepository extends JpaRepository<LabAssistantModel,Long> {
}
