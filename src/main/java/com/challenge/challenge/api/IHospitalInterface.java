package com.challenge.challenge.api;

import com.challenge.challenge.domain.dto.ConsultDto;
import com.challenge.challenge.domain.dto.ResponseDto;
import com.challenge.challenge.domain.dto.command.ConsultCommandDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotNull;

@RequestMapping(path = "/hospitalApi")
@Tag(name = "hospital")
public interface IHospitalInterface {

    @Operation(method = "createConsult", summary = "Registers new consults that happen in the hospital")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<String> createConsult(@RequestBody @NotNull ConsultCommandDto consultCommandDto);

    @Operation(method = "consultsAndSymptoms", summary = "For a specific patient, return a list of all the consults that the patient has had and also all the symptoms he has presented in each consult.")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseDto> getPatientConsultAndSymptoms(@PathVariable Long id);

}
