package com.challenge.domain.dto.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialtyCommandDto {
    private String specialtyName;
    private DoctorCommandDto doctorCommandDto;
}
