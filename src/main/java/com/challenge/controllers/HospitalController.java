package com.challenge.controllers;

import com.challenge.api.IHospitalInterface;
import com.challenge.domain.dto.ConsultDto;
import com.challenge.domain.dto.PatientDto;
import com.challenge.orm.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HospitalController implements IHospitalInterface {

    @Autowired
    PatientRepository patientRepository;

    @Override
    public ResponseEntity<PatientDto> createConsult(ConsultDto consult) {
        return null;
    }
}
