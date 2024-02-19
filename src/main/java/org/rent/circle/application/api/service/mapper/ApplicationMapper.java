package org.rent.circle.application.api.service.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.rent.circle.application.api.dto.ApplicationDto;
import org.rent.circle.application.api.dto.SaveApplicationDto;
import org.rent.circle.application.api.dto.UpdateCoApplicantDto;
import org.rent.circle.application.api.persistence.model.Application;
import org.rent.circle.application.api.persistence.model.CoApplicant;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "cdi")
public interface ApplicationMapper {

    @Mapping(target = "primaryApplicant", source = "primaryApplicant")
    Application toModel(SaveApplicationDto application);

    @Mapping(target = "coSigner", source = "coSigner")
    ApplicationDto toDto(Application application);

    List<ApplicationDto> toDtoList(List<Application> applications);

    void updateCoApplicant(UpdateCoApplicantDto updateCoApplicant, @MappingTarget CoApplicant coApplicant);
}
