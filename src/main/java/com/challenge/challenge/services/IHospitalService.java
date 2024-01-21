package com.challenge.challenge.services;

import com.challenge.challenge.domain.Consult;
import com.challenge.challenge.domain.dto.command.ConsultCommandDto;
import com.challenge.challenge.domain.response.Response;
import com.challenge.challenge.domain.response.TopSpecialtyResponse;

import java.util.List;

public interface IHospitalService {

    Consult createConsult(ConsultCommandDto consult);


    Response getPatientConsultsAndSymptoms(Long id);

    List<TopSpecialtyResponse> getTopSpecialties();
}
