package com.challenge.challenge.controllers;

import com.challenge.challenge.api.IHospitalApi;
import com.challenge.challenge.domain.Consult;
import com.challenge.challenge.domain.Patient;
import com.challenge.challenge.domain.dto.ConsultDto;
import com.challenge.challenge.domain.dto.PatientDto;
import com.challenge.challenge.domain.dto.request.PatientFilters;
import com.challenge.challenge.domain.dto.request.command.ConsultCommandDto;
import com.challenge.challenge.domain.response.Response;
import com.challenge.challenge.domain.response.TopSpecialtyResponse;
import com.challenge.challenge.domain.response.dto.ResponseDto;
import com.challenge.challenge.domain.response.dto.TopSpecialtyResponseDto;
import com.challenge.challenge.mappers.HospitalDtoMapper;
import com.challenge.challenge.services.interfaces.IHospitalService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Component
@RestController
@RequestMapping("/api/hospital")
@Slf4j
@AllArgsConstructor
public class HospitalController implements IHospitalApi {

    private final IHospitalService hospitalService;
    private final HospitalDtoMapper hospitalDtoMapper;

    @PostMapping("/createConsult")
    public ResponseEntity<ConsultDto> createConsult(@RequestBody ConsultCommandDto consultCommandDto) {
        Consult consult = hospitalService.createConsult(consultCommandDto);
        ConsultDto consultDto = hospitalDtoMapper.toConsultDto(consult);

        URI location = this.createUri("/{id}", consultDto.getId());

        return ResponseEntity.created(location).body(consultDto);
    }

    @GetMapping("/consultsAndSymptoms/{patientId}")
    public ResponseEntity<ResponseDto> getPatientConsultAndSymptoms(@PathVariable Long patientId) {
        Response response = hospitalService.getPatientConsultsAndSymptoms(patientId);
        return ResponseEntity.ok(hospitalDtoMapper.toResponseDto(response));
    }

    @GetMapping("/topSpecialties")
    public ResponseEntity<List<TopSpecialtyResponseDto>> getTopSpecialties() {
        List<TopSpecialtyResponse> topSpecialties = hospitalService.getTopSpecialties();
        return ResponseEntity.ok(hospitalDtoMapper.toTopSpecialtiesDto(topSpecialties));
    }

    @GetMapping("/getAllPatients")
    public ResponseEntity<Page<PatientDto>> getAllPatients(PatientFilters filters, Pageable pageable) {
        Page<Patient> filteredPatients = hospitalService.getAllPatients(filters, pageable);
        return ResponseEntity.ok(filteredPatients.map(hospitalDtoMapper::toPatientDto));
    }

    private URI createUri(String path, Object... values) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path(path)
                .buildAndExpand(values)
                .toUri();
    }
}
