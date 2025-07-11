package com.example.dms.repository;

import com.example.dms.model.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientModel,Long> {
    @Query("SELECT MAX(CAST(SUBSTRING(p.patientId, 9) AS long)) FROM PatientModel p")
    Optional<Long> findMaxPatientIdNumber();
}
