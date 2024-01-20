package com.challenge.challenge.domain.orm.repository;

import com.challenge.challenge.domain.orm.models.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("doctorJpaRepository")
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {

    Optional<DoctorEntity> findByName(String name);
}
