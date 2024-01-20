package com.challenge.domain.dto.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultCommandDto {
    private DoctorCommandDto doctorCommandDto;
    private SpecialtyCommandDto specialtyCommandDto;
    private PatientCommandDto patientCommandDto;
}
