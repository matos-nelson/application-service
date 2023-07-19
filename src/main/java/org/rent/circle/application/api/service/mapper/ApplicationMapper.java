package org.rent.circle.application.api.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.rent.circle.application.api.dto.SaveApplicationDto;
import org.rent.circle.application.api.persistence.model.Application;

@Mapper(componentModel = "cdi")
public interface ApplicationMapper {

    @Mapping(target = "applicant", source = "application.applicant")
    @Mapping(target = "applicant.identification.applicant", source = "application.applicant")
    Application toModel(SaveApplicationDto application);
}
