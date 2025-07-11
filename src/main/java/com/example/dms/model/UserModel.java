package com.example.dms.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fullName;
    private String username;
    private String password;
    private String role;
    private String email;
    private String phone;
    private String address;
    private String gender;
    private String dob;
    private String bloodGroup;
    private String image;
    private String createdAt;
    private boolean verified;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private PatientModel patient;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private DoctorModel doctor;

}
