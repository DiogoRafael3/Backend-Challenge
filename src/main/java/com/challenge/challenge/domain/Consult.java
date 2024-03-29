package com.challenge.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Consult {
    private Long id;
    private Doctor doctor;
    private Specialty specialty;
    private Patient patient;
}
