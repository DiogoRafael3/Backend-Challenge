package com.challenge.challenge.domain.dto.request.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PathologyCommandDto {
    private List<SymptomCommandDto> symptoms;
}
