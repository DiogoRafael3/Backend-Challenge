package com.challenge.challenge.domain.orm.repository;

import com.challenge.challenge.domain.orm.models.PathologyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("pathologyJpaRepository")
public interface PathologyRepository extends JpaRepository<PathologyEntity, Long> {

    List<PathologyEntity> findAllByPatientId(Long patientId);

}
