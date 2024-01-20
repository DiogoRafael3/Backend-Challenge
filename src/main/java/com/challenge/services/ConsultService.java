package com.challenge.services;

import com.challenge.domain.Consult;
import com.challenge.mappers.HospitalEntityMapper;
import com.challenge.orm.models.ConsultEntity;
import com.challenge.orm.repository.ConsultRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsultService implements IHospitalService {

    HospitalEntityMapper entityMapper;
    ConsultRepository consultRepository;

    @Autowired
    public ConsultService(HospitalEntityMapper entityMapper, ConsultRepository consultRepository) {
        this.entityMapper = entityMapper;
        this.consultRepository = consultRepository;
    }

    @Override
    public Consult createConsult(Consult consult) {
        ConsultEntity consultEntity = entityMapper.toConsultEntity(consult);
        ConsultEntity savedEntity = consultRepository.save(consultEntity);
        return entityMapper.toConsult(savedEntity);
    }
}
