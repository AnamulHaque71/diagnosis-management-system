package com.example.dms.service;

import com.example.dms.model.LabAssistantModel;
import com.example.dms.modelDto.LabAssistantModelDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LabAssistantService {
    List<LabAssistantModelDto> getAllLabAssistant();
    String generateLabAssistantId();
    Long getNextLabAssistId();
    void saveLabAssistant(LabAssistantModel labAssistantModel);
}
