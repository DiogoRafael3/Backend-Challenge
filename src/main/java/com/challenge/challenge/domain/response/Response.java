package com.challenge.challenge.domain.response;

import com.challenge.challenge.domain.Consult;
import com.challenge.challenge.domain.Symptom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private List<ConsultResponse> consults;
    private List<Symptom> symptoms;
}
