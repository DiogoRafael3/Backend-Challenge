package com.challenge.challenge.domain.orm.repository;

import com.challenge.challenge.domain.orm.models.SymptomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("symptomJpaRepository")
public interface SymptomRepository extends JpaRepository<SymptomEntity, Long> {
}
