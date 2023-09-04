package org.rent.circle.application.api.service.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.rent.circle.application.api.dto.ApplicationDto;
import org.rent.circle.application.api.dto.SaveApplicationDto;
import org.rent.circle.application.api.persistence.model.Application;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "cdi")
public interface ApplicationMapper {

    Application toModel(SaveApplicationDto application);

    ApplicationDto toDto(Application application);

    List<ApplicationDto> toDtoList(List<Application> maintenanceRequests);
}
