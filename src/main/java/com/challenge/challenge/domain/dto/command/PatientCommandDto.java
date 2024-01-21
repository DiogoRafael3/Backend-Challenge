package com.challenge.challenge.domain.dto.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientCommandDto {
    private String name;
    private short age;
    private PathologyCommandDto pathology;
}
