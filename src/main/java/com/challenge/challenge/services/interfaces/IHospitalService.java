package com.challenge.challenge.services.interfaces;

import com.challenge.challenge.domain.Consult;
import com.challenge.challenge.domain.Patient;
import com.challenge.challenge.domain.dto.request.PatientFilters;
import com.challenge.challenge.domain.dto.request.command.ConsultCommandDto;
import com.challenge.challenge.domain.response.Response;
import com.challenge.challenge.domain.response.TopSpecialtyResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IHospitalService {

    Consult createConsult(ConsultCommandDto consult);

    Response getPatientConsultsAndSymptoms(Long id);

    List<TopSpecialtyResponse> getTopSpecialties();

    Page<Patient> getAllPatients(PatientFilters filters, Pageable pageable);
}
