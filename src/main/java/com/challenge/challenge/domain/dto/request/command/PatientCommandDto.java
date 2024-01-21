package com.challenge.challenge.domain.dto.request.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientCommandDto {
    private String name;
    private Integer age;
    private PathologyCommandDto pathology;
}
