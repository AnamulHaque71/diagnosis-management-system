package com.example.dms.modelDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentModelDto {

    private Long id;


    private String doctorId;
    private String doctorName;

    private String status;
    private String createdAt;

    private String appointmentDate;

    private String patientId;
    private String patientName;

}
