package com.challenge.challenge.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PathologyDto {
    private Long pathologyId;
    private PatientDto patientDto;
    private List<SymptomDto> symptomDtos;
}
