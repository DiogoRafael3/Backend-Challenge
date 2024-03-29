package com.challenge.challenge.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultDto {
    private Long id;
    private DoctorDto doctor;
    private SpecialtyDto specialty;
    private PatientDto patient;
}
