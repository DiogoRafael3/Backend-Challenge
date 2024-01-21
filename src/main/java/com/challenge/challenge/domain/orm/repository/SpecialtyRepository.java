package com.challenge.challenge.domain.orm.repository;

import com.challenge.challenge.domain.orm.models.SpecialtyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("specialtyRepository")
public interface SpecialtyRepository extends JpaRepository<SpecialtyEntity, Long> {
    Optional<SpecialtyEntity> findBySpecialtyName(String specialtyName);
}
