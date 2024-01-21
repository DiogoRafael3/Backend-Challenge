package com.challenge.challenge.domain.response;

import com.challenge.challenge.domain.Doctor;
import com.challenge.challenge.domain.Specialty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultResponse {
    private Long id;
    private Doctor doctor;
    private Specialty specialty;
}
