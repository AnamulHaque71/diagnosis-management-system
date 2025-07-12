package com.example.dms.repository;

import com.example.dms.model.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentModel,Long> {
    List<AppointmentModel> findByPatientId(String patientId);
    List<AppointmentModel> findByDoctorId(String patientId);
}
