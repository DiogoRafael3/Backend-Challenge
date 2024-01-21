package com.challenge.challenge.mappers;

import com.challenge.challenge.domain.Consult;
import com.challenge.challenge.domain.Patient;
import com.challenge.challenge.domain.Symptom;
import com.challenge.challenge.domain.dto.request.command.ConsultCommandDto;
import com.challenge.challenge.domain.orm.models.ConsultEntity;
import com.challenge.challenge.domain.orm.models.DoctorEntity;
import com.challenge.challenge.domain.orm.models.PatientEntity;
import com.challenge.challenge.domain.orm.models.SpecialtyEntity;
import com.challenge.challenge.domain.orm.models.SymptomEntity;
import com.challenge.challenge.domain.response.ConsultResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HospitalEntityMapper {

    Consult toConsult(ConsultEntity consultDto);

    @Mapping(target = "doctor", source = "doctor", qualifiedByName = "stringToDoctor")
    @Mapping(target = "specialty", source = "specialty", qualifiedByName = "stringToSpecialty")
    ConsultEntity toConsultEntity(ConsultCommandDto consult);

    List<ConsultResponse> toConsultResponseList(List<ConsultEntity> consultEntityList);

    List<Symptom> toSymptomList(List<SymptomEntity> symptomEntityList);

    Patient toPatient(PatientEntity patientEntity);

    @Named("stringToDoctor")
    DoctorEntity stringToDoctor(String name);

    @Named("stringToSpecialty")
    SpecialtyEntity stringToSpecialty(String specialty);

}
