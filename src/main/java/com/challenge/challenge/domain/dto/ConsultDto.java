package com.challenge.challenge.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultDto {
    private Long consultId;
    private DoctorDto doctorDto;
    private SpecialtyDto specialtyDto;
    private PatientDto patientDto;
}
