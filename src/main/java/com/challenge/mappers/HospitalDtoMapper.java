package com.challenge.mappers;

import com.challenge.domain.Consult;
import com.challenge.domain.dto.ConsultDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HospitalDtoMapper {

    Consult toConsult(ConsultDto consultDto);

    ConsultDto toConsultDto(Consult consultDto);
}
