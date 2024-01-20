package com.challenge.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    private Long patientId;
    private String name;
    private short age;
    private List<Consult> consults;
    private List<Pathology> pathologies;
}
