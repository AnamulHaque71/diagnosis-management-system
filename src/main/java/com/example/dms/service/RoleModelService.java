package com.example.dms.service;


import com.example.dms.model.RoleModel;
import com.example.dms.repository.RoleModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface RoleModelService {
    RoleModel findRoleById(Long id);
}
