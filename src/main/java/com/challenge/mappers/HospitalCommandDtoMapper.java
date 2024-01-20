package com.challenge.mappers;

import com.challenge.domain.Consult;
import com.challenge.domain.dto.command.ConsultCommandDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HospitalCommandDtoMapper {

    Consult toConsult(ConsultCommandDto consultCommandDto);

    ConsultCommandDto toConsultCommandDto(Consult consult);

}
