package com.challenge.domain.dto.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PathologyCommandDto {
    private PatientCommandDto patientCommandDto;
    private List<SymptomCommandDto> symptomCommandDtos;
}
