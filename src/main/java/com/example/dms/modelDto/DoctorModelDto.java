package com.example.dms.modelDto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorModelDto {
    private long id;
    private String username;
    private String password;
    private String fullName;
    private String role;
    private String email;
    private String phone;
    private String address;
    private String gender;
    private String dob;
    @Column(name = "blood_group")
    private String bloodGroup;
    private MultipartFile image;
    private String imageName;
    @Column(name = "created_at")
    private String createdAt;
    private boolean verified;

    // Doctor-specific fields
    private String specialization;
    private String department;
    private String designation;
    private int experienceYears;
    private String degree;
    private String doctorId;
}
