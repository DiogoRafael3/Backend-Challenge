package com.challenge.challenge.domain.orm.repository;

import com.challenge.challenge.domain.orm.models.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("patientJpaRepository")
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
    Optional<PatientEntity> findByNameAndAge(String name, Integer age);

    Optional<Page<PatientEntity>> findAll(Specification<PatientEntity> specification, Pageable pageable);
}
