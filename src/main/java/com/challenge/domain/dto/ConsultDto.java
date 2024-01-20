package com.challenge.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultDto {
    private int consultId;
    private DoctorDto doctorDto;
    private SpecialtyDto specialtyDto;
    private PatientDto patientDto;
}
