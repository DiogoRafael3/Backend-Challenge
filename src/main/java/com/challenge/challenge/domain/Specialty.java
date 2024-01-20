package com.challenge.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Specialty {
    private Long specialtyId;
    private String specialtyName;
    private Doctor doctor;
}
