package com.challenge.challenge.services;

import com.challenge.challenge.domain.Consult;
import com.challenge.challenge.domain.dto.command.ConsultCommandDto;
import com.challenge.challenge.domain.response.Response;

public interface IHospitalService {

    Consult createConsult(ConsultCommandDto consult);


    Response getPatientConsultsAndSymptoms(Long id);
}
