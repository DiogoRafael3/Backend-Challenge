package com.challenge.challenge.domain.response.dto;

import com.challenge.challenge.domain.dto.DoctorDto;
import com.challenge.challenge.domain.dto.SpecialtyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultResponseDto {
    private Long id;
    private DoctorDto doctor;
    private SpecialtyDto specialty;
}
