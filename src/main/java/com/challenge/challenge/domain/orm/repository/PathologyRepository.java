package com.challenge.challenge.domain.orm.repository;

import com.challenge.challenge.domain.orm.models.ConsultEntity;
import com.challenge.challenge.domain.orm.models.PathologyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("pathologyJpaRepository")
public interface PathologyRepository extends JpaRepository<PathologyEntity, Long> {

}
