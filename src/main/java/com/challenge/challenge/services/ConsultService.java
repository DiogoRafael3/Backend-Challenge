package com.challenge.challenge.services;

import com.challenge.challenge.domain.Consult;
import com.challenge.challenge.domain.orm.models.DoctorEntity;
import com.challenge.challenge.domain.orm.repository.DoctorRepository;
import com.challenge.challenge.domain.orm.repository.PathologyRepository;
import com.challenge.challenge.domain.orm.repository.SpecialtyRepository;
import com.challenge.challenge.domain.orm.repository.SymptomRepository;
import com.challenge.challenge.mappers.HospitalEntityMapper;
import com.challenge.challenge.domain.orm.models.ConsultEntity;
import com.challenge.challenge.domain.orm.repository.ConsultRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ConsultService implements IHospitalService {

    HospitalEntityMapper entityMapper;
    ConsultRepository consultRepository;
    DoctorRepository doctorRepository;
    SpecialtyRepository specialtyRepository;


    @Autowired
    public ConsultService(HospitalEntityMapper entityMapper,
                          ConsultRepository consultRepository,
                          DoctorRepository doctorRepository,
                          SpecialtyRepository specialtyRepository) {
        this.entityMapper = entityMapper;
        this.consultRepository = consultRepository;
        this.doctorRepository = doctorRepository;
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Consult createConsult(Consult consult) {
        doctorRepository.findByName(consult.getDoctor().getName())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        specialtyRepository.findBySpecialtyName(consult.getSpecialty().getSpecialtyName())
                .orElseThrow(() -> new RuntimeException("Specialty not found"));

        ConsultEntity consultEntity = entityMapper.toConsultEntity(consult);
        ConsultEntity savedEntity = consultRepository.save(consultEntity);
        return entityMapper.toConsult(savedEntity);
    }
}
