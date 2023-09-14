package org.rent.circle.application.api.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.rent.circle.application.api.dto.CoSignerDto;
import org.rent.circle.application.api.persistence.model.CoSigner;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "cdi")
public interface CoSignerMapper {

    CoSigner toModel(CoSignerDto coSigner);
}
