package com.challenge.controllers;

import com.challenge.api.IHospitalInterface;
import com.challenge.domain.Consult;
import com.challenge.domain.dto.ConsultDto;
import com.challenge.domain.dto.PatientDto;
import com.challenge.mappers.HospitalEntityMapper;
import com.challenge.orm.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HospitalController implements IHospitalInterface {

    PatientRepository patientRepository;
    HospitalEntityMapper hospitalEntityMapper;

    @Autowired
    public HospitalController(PatientRepository patientRepository, HospitalEntityMapper hospitalEntityMapper) {
        this.patientRepository = patientRepository;
        this.hospitalEntityMapper = hospitalEntityMapper;
    }

    @Override
    public ResponseEntity<ConsultDto> createConsult(ConsultDto consultDto) {
        Consult consult = hospitalEntityMapper.toConsult(consultDto);
        System.out.println(consult);
        return null;
    }
}
