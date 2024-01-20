package com.challenge.challenge.mappers;

import com.challenge.challenge.domain.Consult;
import com.challenge.challenge.domain.dto.command.ConsultCommandDto;
import com.challenge.challenge.domain.dto.command.PathologyCommandDto;
import com.challenge.challenge.domain.dto.command.SymptomCommandDto;
import com.challenge.challenge.domain.orm.models.ConsultEntity;
import com.challenge.challenge.domain.orm.models.PathologyEntity;
import com.challenge.challenge.domain.orm.models.SymptomEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HospitalEntityMapper {

    Consult toConsult(ConsultEntity consultDto);

    ConsultEntity toConsultEntity(ConsultCommandDto consult);

    PathologyEntity toPathologyEntity(PathologyCommandDto pathologyCommandDto);

    List<SymptomEntity> toSymptomEntityList(List<SymptomCommandDto> symptomCommandDtoList);
}
