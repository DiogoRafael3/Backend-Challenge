package com.challenge.challenge.domain.orm.repository;

import com.challenge.challenge.domain.orm.models.ConsultEntity;
import com.challenge.challenge.domain.response.TopSpecialtyResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("consultJpaRepository")
public interface ConsultRepository extends JpaRepository<ConsultEntity, Long> {
    List<ConsultEntity> findAllByPatientId(Long patientId);

    @Query("SELECT new com.challenge.challenge.domain.response.TopSpecialtyResponse(s.specialtyName, COUNT(c)) " +
            "FROM ConsultEntity c " +
            "JOIN c.specialty s " +
            "GROUP BY s.specialtyName " +
            "HAVING COUNT(c) > :consultCountThreshold")
    List<TopSpecialtyResponse> findSpecialtiesWithMoreThanConsults(@Param("consultCountThreshold") long consultCountThreshold);
}
