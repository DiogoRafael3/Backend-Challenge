package com.challenge.challenge.domain.orm.repository;

import com.challenge.challenge.domain.orm.models.ConsultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("consultJpaRepository")
public interface ConsultRepository extends JpaRepository<ConsultEntity, Long> {
    
}
