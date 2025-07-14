package com.example.dms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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


    @JoinColumn(name = "test_type_id")
    private String testTypeId;


    @JoinColumn(name = "patient_id")
    private String patientId;

    private String status;

    private String createdAt; // Consider using LocalDateTime for better handling
}
