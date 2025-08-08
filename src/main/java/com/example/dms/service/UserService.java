package com.example.dms.service;

import com.example.dms.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserModel> getAllUsers();

    UserModel saveUser(UserModel user);
    UserModel findByEmail(String email);
    UserModel getUserById(Long id);

    void deleteUser(Long id);
    boolean registerUser(UserModel user);
    boolean emailExists(String email);
    UserModel loginUser(String email, String password);
}
