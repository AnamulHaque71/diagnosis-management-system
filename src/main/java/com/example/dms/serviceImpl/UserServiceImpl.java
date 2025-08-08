package com.example.dms.serviceImpl;

import com.example.dms.model.RoleModel;
import com.example.dms.model.UserModel;
import com.example.dms.repository.DoctorRepository;
import com.example.dms.repository.PatientRepository;
import com.example.dms.repository.RoleModelRepository;
import com.example.dms.repository.UserRepository;
import com.example.dms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private RoleModelRepository roleModelRepository;

    @Override
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserModel saveUser(UserModel user) {
        Date currentDate = new Date();
        user.setCreatedAt(currentDate.toString());
        return userRepository.save(user);
    }

    @Override
    public UserModel findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserModel getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public boolean registerUser(UserModel user) {
        try{
            user.setPassword(encoder.encode(user.getPassword()));

            RoleModel userRole = roleModelRepository.findByRoleName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Default role not found"));

            user.getRoles().add(userRole);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public UserModel loginUser(String email, String password) {
        UserModel user = userRepository.findByEmail(email);
        if (user != null && encoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }


}
