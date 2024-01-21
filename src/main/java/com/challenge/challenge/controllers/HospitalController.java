package com.challenge.challenge.controllers;

import com.challenge.challenge.api.IHospitalInterface;
import com.challenge.challenge.domain.response.Response;
import com.challenge.challenge.domain.response.dto.ResponseDto;
import com.challenge.challenge.domain.dto.command.ConsultCommandDto;
import com.challenge.challenge.mappers.HospitalDtoMapper;
import com.challenge.challenge.services.IHospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("/api/hospital")
@Slf4j
public class HospitalController implements IHospitalInterface {

    IHospitalService hospitalService;
    HospitalDtoMapper hospitalDtoMapper;

    @Autowired
    public HospitalController(IHospitalService hospitalService, HospitalDtoMapper hospitalDtoMapper) {
        this.hospitalService = hospitalService;
        this.hospitalDtoMapper = hospitalDtoMapper;
    }

    @PostMapping("/createConsult")
    public ResponseEntity<String> createConsult(@RequestBody ConsultCommandDto consultCommandDto) {
        //TODO: Should return consult
        //TODO: should map commanddto to command first
        hospitalService.createConsult(consultCommandDto);
        return new ResponseEntity<>("Consult was created succesfully!", HttpStatus.CREATED);
    }

    @GetMapping("/consultsAndSymptoms/{patientId}")
    public ResponseEntity<ResponseDto> getPatientConsultAndSymptoms(@PathVariable Long patientId) {
        Response response = hospitalService.getPatientConsultsAndSymptoms(patientId);
        return ResponseEntity.ok(hospitalDtoMapper.toResponseDto(response));
    }
}
