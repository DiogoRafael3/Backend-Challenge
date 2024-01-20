package com.challenge.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {
    private String patientId;
    private short age;
    private List<ConsultDto> consultDtos;
    private List<PathologyDto> pathologies;
}
