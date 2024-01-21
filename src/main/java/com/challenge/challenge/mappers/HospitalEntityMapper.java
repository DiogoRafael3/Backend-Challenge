package com.challenge.challenge.mappers;

import com.challenge.challenge.domain.Consult;
import com.challenge.challenge.domain.dto.command.ConsultCommandDto;
import com.challenge.challenge.domain.dto.command.PathologyCommandDto;
import com.challenge.challenge.domain.dto.command.PatientCommandDto;
import com.challenge.challenge.domain.dto.command.SymptomCommandDto;
import com.challenge.challenge.domain.orm.models.ConsultEntity;
import com.challenge.challenge.domain.orm.models.DoctorEntity;
import com.challenge.challenge.domain.orm.models.PathologyEntity;
import com.challenge.challenge.domain.orm.models.PatientEntity;
import com.challenge.challenge.domain.orm.models.SpecialtyEntity;
import com.challenge.challenge.domain.orm.models.SymptomEntity;
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
    PatientEntity toPatientEntity(PatientCommandDto patientCommandDto);

    PathologyEntity toPathologyEntity(PathologyCommandDto pathologyCommandDto);

    List<SymptomEntity> toSymptomEntityList(List<SymptomCommandDto> symptomCommandDtoList);

    @Named("stringToDoctor")
    DoctorEntity stringToDoctor(String name);

    @Named("stringToSpecialty")
    SpecialtyEntity stringToSpecialty(String specialty);
}
