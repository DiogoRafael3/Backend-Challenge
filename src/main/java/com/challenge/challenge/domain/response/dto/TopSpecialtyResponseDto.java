package com.challenge.challenge.domain.response.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopSpecialtyResponseDto {
    String specialtyName;
    Long numberOfPatients;
}
