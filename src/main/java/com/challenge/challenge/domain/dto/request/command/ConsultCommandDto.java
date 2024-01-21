package com.challenge.challenge.domain.dto.request.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultCommandDto {
    private String doctor;
    private String specialty;
    private PatientCommandDto patient;
}
