package com.example.dms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lab_assistants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabAssistantModel {
    @Id
    private long id;
    @Column(name = "lab_assistant_id", unique = true)
    private String labAssistantId;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserModel user;

    private String specialization; // e.g., biochemistry, pathology, etc.
    private String department; // microbiology, hematology, etc.
    private String designation; // senior lab assistant, junior lab assistant, etc.
    private int experienceYears;
    private String degree; // mmbs

}
