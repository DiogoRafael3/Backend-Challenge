package com.challenge.domain;

import lombok.Data;

import java.util.List;

@Data
public class Patient {
    private String patientId;
    private short age;
    private List<Consult> consults;
    private List<Pathology> pathologies;
}
