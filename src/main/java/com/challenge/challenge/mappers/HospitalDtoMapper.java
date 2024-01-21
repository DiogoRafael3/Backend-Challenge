package com.challenge.challenge.mappers;

import com.challenge.challenge.domain.Consult;
import com.challenge.challenge.domain.dto.ConsultDto;
import com.challenge.challenge.domain.response.ConsultResponse;
import com.challenge.challenge.domain.response.Response;
import com.challenge.challenge.domain.response.TopSpecialtyResponse;
import com.challenge.challenge.domain.response.dto.ConsultResponseDto;
import com.challenge.challenge.domain.response.dto.ResponseDto;
import com.challenge.challenge.domain.response.dto.TopSpecialtyResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HospitalDtoMapper {

    ConsultDto toConsultDto(Consult consultDto);

    @Mapping(target = "consults", source = "consults", qualifiedByName = "consultResponsesDto")
    ResponseDto toResponseDto(Response response);

    @Named("consultResponsesDto")
    List<ConsultResponseDto> toConsultResponseDto(List<ConsultResponse> consultResponses);


    List<TopSpecialtyResponseDto> toTopSpecialtiesDto(List<TopSpecialtyResponse> topSpecialtyResponses);
}
