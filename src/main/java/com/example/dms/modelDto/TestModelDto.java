package com.example.dms.modelDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestModelDto {
    private Long id;
    private String testName;
    private String doctorName;
    private String patientName;
    private String status;
}
