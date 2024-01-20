package com.challenge.orm.repository;

import com.challenge.orm.models.ConsultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("consultJpaRepository")
public interface ConsultRepository extends JpaRepository<ConsultEntity, String> {
    
}
