package com.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Consult {
    private int consultId;
    private Doctor doctor;
    private Specialty specialty;
    private Patient patient;
}
