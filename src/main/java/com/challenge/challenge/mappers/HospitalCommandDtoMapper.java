package com.challenge.challenge.mappers;

import com.challenge.challenge.domain.Consult;
import com.challenge.challenge.domain.dto.command.ConsultCommandDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HospitalCommandDtoMapper {

    Consult toConsult(ConsultCommandDto consultCommandDto);

    ConsultCommandDto toConsultCommandDto(Consult consult);

}
