package com.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    private String patientId;
    private short age;
    private List<Consult> consults;
    private List<Pathology> pathologies;
}
