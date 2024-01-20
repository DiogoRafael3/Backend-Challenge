package com.challenge.orm.repository;

import com.challenge.orm.models.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("patientJpaRepository")
public interface PatientRepository extends JpaRepository<PatientEntity, String> {
    
}
