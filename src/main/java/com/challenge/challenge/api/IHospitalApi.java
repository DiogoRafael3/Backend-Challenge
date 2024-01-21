package com.challenge.challenge.api;

import com.challenge.challenge.domain.dto.PatientDto;
import com.challenge.challenge.domain.dto.request.PatientFilters;
import com.challenge.challenge.domain.dto.request.command.ConsultCommandDto;
import com.challenge.challenge.domain.response.dto.ResponseDto;
import com.challenge.challenge.domain.response.dto.TopSpecialtyResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping(path = "/hospitalApi")
@Tag(name = "hospital")
public interface IHospitalApi {

    @Operation(method = "createConsult", summary = "Registers new consults that happen in the hospital")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<String> createConsult(@RequestBody @NotNull ConsultCommandDto consultCommandDto);

    @Operation(method = "getConsultsAndSymptoms", summary = "For a specific patient, return a list of all the consults that the patient has had and also all the symptoms he has presented in each consult.")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<ResponseDto> getPatientConsultAndSymptoms(@PathVariable Long patientId);

    @Operation(method = "getTopSpecialties", summary = "Lists which specialties have more than 2 patientes.")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<TopSpecialtyResponseDto>> getTopSpecialties();

    @Operation(method = "getAllPatients", summary = "return the data for all the patients with pagination, filtering and sorting by either age or name.")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<PatientDto>> getAllPatients(@RequestBody PatientFilters filters);

}
