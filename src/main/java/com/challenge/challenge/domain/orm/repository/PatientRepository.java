package com.challenge.challenge.domain.orm.repository;

import com.challenge.challenge.domain.orm.models.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("patientJpaRepository")
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

}
