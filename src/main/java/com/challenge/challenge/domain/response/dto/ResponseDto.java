package com.challenge.challenge.domain.response.dto;

import com.challenge.challenge.domain.dto.SymptomDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private List<ConsultResponseDto> consults;
    private List<SymptomDto> symptoms;
}
