package com.example.dms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "tests")
public class TestModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "doctor_id")
    private String doctorId;
    private String doctorName;


    @JoinColumn(name = "test_type_id")
    private String testTypeId;
    private String testTypeName;


    @JoinColumn(name = "patient_id")
    private String patientId;
    private String patientName;

    private String status;

    private String resultName;

    private String createdAt;
}
