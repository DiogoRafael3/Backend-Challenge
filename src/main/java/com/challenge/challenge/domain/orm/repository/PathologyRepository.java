package com.challenge.challenge.domain.orm.repository;

import com.challenge.challenge.domain.orm.models.PathologyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("pathologyJpaRepository")
public interface PathologyRepository extends JpaRepository<PathologyEntity, Long> {

    Optional<List<PathologyEntity>> findAllByPatientId(Long patientId);

}
