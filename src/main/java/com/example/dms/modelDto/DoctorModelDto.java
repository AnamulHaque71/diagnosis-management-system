package com.example.dms.modelDto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorModelDto {
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
    private String image;
    @Column(name = "created_at")
    private String createdAt;
    private boolean verified;

    // Doctor-specific fields
    private String specialization;
    private String department;
    private String designation;
    private int experienceYears;
    private String degree;
}
