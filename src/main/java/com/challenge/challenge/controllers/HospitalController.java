package com.challenge.challenge.controllers;

import com.challenge.challenge.domain.dto.command.ConsultCommandDto;
import com.challenge.challenge.mappers.HospitalCommandDtoMapper;
import com.challenge.challenge.mappers.HospitalDtoMapper;
import com.challenge.challenge.services.IHospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("/api/hospital")
@Slf4j
public class HospitalController {

    IHospitalService hospitalService;
    HospitalDtoMapper hospitalDtoMapper;

    @Autowired
    public HospitalController(IHospitalService hospitalService, HospitalDtoMapper hospitalDtoMapper, HospitalCommandDtoMapper hospitalCommandDtoMapper) {
        this.hospitalService = hospitalService;
        this.hospitalDtoMapper = hospitalDtoMapper;
    }

    @PostMapping("/createConsult")
    public ResponseEntity<String> createConsult(@RequestBody ConsultCommandDto consultCommandDto) {
        hospitalService.createConsult(consultCommandDto);
        return new ResponseEntity<>("Consult was created succesfully!", HttpStatus.CREATED);
    }
}
