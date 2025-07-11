package com.example.dms.serviceImpl;

import com.example.dms.model.LabAssistantModel;
import com.example.dms.modelDto.LabAssistantModelDto;
import com.example.dms.repository.LabAssistantRepository;
import com.example.dms.service.LabAssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LabAssistantServiceImpl implements LabAssistantService {
    @Autowired
    private LabAssistantRepository labAssistantRepository;

    @Override
    public List<LabAssistantModelDto> getAllLabAssistant() {
        List<LabAssistantModel> LabAssists = labAssistantRepository.findAll();
        List<LabAssistantModelDto> labAssistantModelDtoList = new ArrayList<>();
        for (LabAssistantModel labAssist : LabAssists) {
            LabAssistantModelDto labAssistantModelDto = new LabAssistantModelDto();

            labAssistantModelDto.setUsername(labAssist.getUser().getUsername());
            labAssistantModelDto.setPassword(labAssist.getUser().getPassword());
            labAssistantModelDto.setRole(labAssist.getUser().getRole());
            labAssistantModelDto.setFullName(labAssist.getUser().getFullName());
            labAssistantModelDto.setEmail(labAssist.getUser().getEmail());
            labAssistantModelDto.setPhone(labAssist.getUser().getPhone());
            labAssistantModelDto.setAddress(labAssist.getUser().getAddress());
            labAssistantModelDto.setGender(labAssist.getUser().getGender());
            labAssistantModelDto.setDob(labAssist.getUser().getDob());
            labAssistantModelDto.setBloodGroup(labAssist.getUser().getBloodGroup());
            labAssistantModelDto.setImage(labAssist.getUser().getImage());

            Date currentDate = new Date();
            labAssistantModelDto.setCreatedAt(currentDate.toString());

            labAssistantModelDto.setVerified(labAssist.getUser().isVerified());

            labAssistantModelDto.setSpecialization(labAssist.getSpecialization());
            labAssistantModelDto.setDepartment(labAssist.getDepartment());
            labAssistantModelDto.setDesignation(labAssist.getDesignation());
            labAssistantModelDto.setExperienceYears(labAssist.getExperienceYears());
            labAssistantModelDto.setDegree(labAssist.getDegree());

            labAssistantModelDtoList.add(labAssistantModelDto);

        }

        return labAssistantModelDtoList;
    }

    @Override
    public String generateLabAssistantId() {
        long count = labAssistantRepository.count();
        return "la-" + (count + 1);
    }

    @Override
    public Long getNextLabAssistId() {
        Long count = labAssistantRepository.count(); // count existing doctors
        return count + 1;
    }

    @Override
    public void saveLabAssistant(LabAssistantModel labAssistantModel) {
        labAssistantRepository.save(labAssistantModel);
    }
}
