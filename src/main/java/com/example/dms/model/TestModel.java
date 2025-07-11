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

    // Foreign key to Doctor
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorModel doctor;

    // Foreign key to TestType
    @ManyToOne
    @JoinColumn(name = "test_type_id")
    private TestTypeModel testType;

    // Foreign key to Patient
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientModel patient;

    private String status;

    private String createdAt; // Consider using LocalDateTime for better handling
}
