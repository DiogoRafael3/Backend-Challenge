package com.challenge.mappers;

import com.challenge.domain.Consult;
import com.challenge.orm.models.ConsultEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HospitalEntityMapper {

    Consult toConsult(ConsultEntity consultDto);

    ConsultEntity toConsultEntity(Consult consult);

}
