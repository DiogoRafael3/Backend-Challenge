package com.challenge.challenge.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopSpecialtyResponse {
    String specialtyName;
    Long numberOfPatients;
}
