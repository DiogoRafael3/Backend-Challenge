package com.challenge.challenge.mappers;

import com.challenge.challenge.domain.Consult;
import com.challenge.challenge.domain.orm.models.ConsultEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HospitalEntityMapper {

    Consult toConsult(ConsultEntity consultDto);

    ConsultEntity toConsultEntity(Consult consult);

}
