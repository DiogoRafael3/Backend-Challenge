package com.challenge.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pathology {
    private Long pathologyId;
    private Patient patient;
    private List<Symptom> symptoms;
}
