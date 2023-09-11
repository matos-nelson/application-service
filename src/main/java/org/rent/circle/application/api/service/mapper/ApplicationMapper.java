package org.rent.circle.application.api.service.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.rent.circle.application.api.dto.ApplicationDto;
import org.rent.circle.application.api.dto.SaveApplicationDto;
import org.rent.circle.application.api.persistence.model.Application;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "cdi")
public interface ApplicationMapper {

    @Mapping(target = "primaryApplicant", source = "primaryApplicant")
    Application toModel(SaveApplicationDto application);

    //PrimaryApplicant temp(PrimaryApplicantDto primaryApplicantDto);

    ApplicationDto toDto(Application application);

    List<ApplicationDto> toDtoList(List<Application> maintenanceRequests);
}
