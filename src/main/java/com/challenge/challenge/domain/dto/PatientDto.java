package com.challenge.challenge.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {
    private Long patientId;
    private String name;
    private short age;
    private List<ConsultDto> consultDtos;
    private List<PathologyDto> pathologyDtos;
}
