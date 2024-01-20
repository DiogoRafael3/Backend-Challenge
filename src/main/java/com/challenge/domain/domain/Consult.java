package com.challenge.domain.domain;

import lombok.Data;

@Data
public class Consult {
    private int consultId;
    private Doctor doctor;
    private Specialty specialty;
    private Patient patient;
}
