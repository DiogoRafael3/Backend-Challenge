package com.challenge.controllers;

import com.challenge.api.IHospitalInterface;
import com.challenge.domain.Consult;
import com.challenge.domain.dto.ConsultDto;
import com.challenge.domain.dto.command.ConsultCommandDto;
import com.challenge.mappers.HospitalCommandDtoMapper;
import com.challenge.mappers.HospitalDtoMapper;
import com.challenge.services.IHospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@Slf4j
public class HospitalController implements IHospitalInterface {

    IHospitalService hospitalService;
    HospitalDtoMapper hospitalDtoMapper;
    HospitalCommandDtoMapper hospitalCommandDtoMapper;

    @Autowired
    public HospitalController(IHospitalService hospitalService, HospitalDtoMapper hospitalDtoMapper, HospitalCommandDtoMapper hospitalCommandDtoMapper) {
        this.hospitalService = hospitalService;
        this.hospitalDtoMapper = hospitalDtoMapper;
        this.hospitalCommandDtoMapper = hospitalCommandDtoMapper;
    }

    @Override
    public ResponseEntity<ConsultDto> createConsult(ConsultCommandDto consultCommandDto) {
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
