package com.example.dms.modelDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllTestShowDTO {
    private String testTypeId;
    private String TestName;
    private String patientId;
    private String patientName;
    private String createdAt;
    private String result;
    private String reportFileName;
    private String status;
}
