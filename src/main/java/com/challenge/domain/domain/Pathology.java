package com.challenge.domain.domain;

import lombok.Data;

import java.util.List;

@Data
public class Pathology {
    private long pathologyId;
    private Patient patient;
    private List<Symptom> symptoms;
}
