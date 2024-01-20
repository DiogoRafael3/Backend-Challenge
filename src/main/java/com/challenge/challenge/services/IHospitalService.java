package com.challenge.challenge.services;

import com.challenge.challenge.domain.Consult;
import com.challenge.challenge.domain.dto.command.ConsultCommandDto;

public interface IHospitalService {

    Consult createConsult(ConsultCommandDto consult);
}
