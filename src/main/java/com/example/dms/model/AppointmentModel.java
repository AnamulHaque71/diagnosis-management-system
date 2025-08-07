package com.example.dms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointments")
public class AppointmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "doctor_id")
    private String doctorId;
    private String doctorName;

    private String status;
    private String createdAt;

    @Column(name = "appointment_date")
    private String appointmentDate;

    @Column(name = "patient_id")
    private String patientId;
    private String patientName;

}
