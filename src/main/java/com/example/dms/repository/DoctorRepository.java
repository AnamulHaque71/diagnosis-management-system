package com.example.dms.repository;

import com.example.dms.model.DoctorModel;
import com.example.dms.modelDto.DoctorModelDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorModel, Long> {
    DoctorModel findByDoctorId(String doctorId);
    @Query("SELECT MAX(d.id) FROM DoctorModel d")
    Optional<Long> findMaxId();
}
