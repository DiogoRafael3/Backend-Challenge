package com.challenge.challenge.mappers;

import com.challenge.challenge.domain.Consult;
import com.challenge.challenge.domain.dto.ConsultDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HospitalDtoMapper {

    Consult toConsult(ConsultDto consultDto);

    ConsultDto toConsultDto(Consult consultDto);
}
