package com.challenge.challenge.controllers;

import com.challenge.challenge.api.IHospitalInterface;
import com.challenge.challenge.domain.Consult;
import com.challenge.challenge.domain.dto.ConsultDto;
import com.challenge.challenge.domain.dto.command.ConsultCommandDto;
import com.challenge.challenge.mappers.HospitalCommandDtoMapper;
import com.challenge.challenge.mappers.HospitalDtoMapper;
import com.challenge.challenge.services.IHospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Component
@RestController
@RequestMapping("/api/hospital")
@Slf4j
public class HospitalController {

    IHospitalService hospitalService;
    HospitalDtoMapper hospitalDtoMapper;
    HospitalCommandDtoMapper hospitalCommandDtoMapper;

    @Autowired
    public HospitalController(IHospitalService hospitalService, HospitalDtoMapper hospitalDtoMapper, HospitalCommandDtoMapper hospitalCommandDtoMapper) {
        this.hospitalService = hospitalService;
        this.hospitalDtoMapper = hospitalDtoMapper;
        this.hospitalCommandDtoMapper = hospitalCommandDtoMapper;
    }

    @PostMapping("/createConsult")
    public ResponseEntity<ConsultDto> createConsult(@RequestBody ConsultCommandDto consultCommandDto) {
        Consult consult = hospitalCommandDtoMapper.toConsult(consultCommandDto);
        Consult consultCreated = hospitalService.createConsult(consult);
        ConsultDto retObj = hospitalDtoMapper.toConsultDto(consultCreated);

        URI location = this.createUri("/{consultId}", retObj.getConsultId());

        return ResponseEntity.created(location).body(retObj);
    }

    private  URI createUri(String path, Object... values) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path(path)
                .buildAndExpand(values)
                .toUri();
    }
}
