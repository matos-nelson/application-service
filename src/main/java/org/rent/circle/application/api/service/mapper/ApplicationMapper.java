package org.rent.circle.application.api.service.mapper;

import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.rent.circle.application.api.dto.ApplicationDto;
import org.rent.circle.application.api.dto.SaveApplicationDto;
import org.rent.circle.application.api.persistence.model.Applicant;
import org.rent.circle.application.api.persistence.model.Application;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "cdi")
public interface ApplicationMapper {

    Application toModel(SaveApplicationDto application);

    ApplicationDto toDto(Application application);

    List<ApplicationDto> toDtoList(List<Application> maintenanceRequests);

    @AfterMapping
    default void afterMapping(@MappingTarget Application target) {
        Applicant applicant = target.getApplicant();

        if (applicant == null) {
            return;
        }

        if (applicant.getEmployer() != null) {
            applicant.getEmployer().setApplicant(applicant);
        }

        if (applicant.getEmergencyContact() != null) {
            applicant.getEmergencyContact().setApplicant(applicant);
        }

        if (applicant.getIdentification() != null) {
            applicant.getIdentification().setApplicant(applicant);
        }

        if (applicant.getResidentialHistories() != null) {
            applicant.getResidentialHistories().forEach(r -> r.setApplicant(applicant));
        }

        if (applicant.getPersonalReferences() != null) {
            applicant.getPersonalReferences().forEach(r -> r.setApplicant(applicant));
        }

        if (applicant.getCoApplicants() != null) {
            applicant.getCoApplicants().forEach(r -> r.setApplicant(applicant));
        }

        if (applicant.getOccupants() != null) {
            applicant.getOccupants().forEach(r -> r.setApplicant(applicant));
        }

        if (applicant.getPets() != null) {
            applicant.getPets().forEach(r -> r.setApplicant(applicant));
        }

        if (applicant.getVehicles() != null) {
            applicant.getVehicles().forEach(r -> r.setApplicant(applicant));
        }

        if (applicant.getAdditionalIncomeSources() != null) {
            applicant.getAdditionalIncomeSources().forEach(r -> r.setApplicant(applicant));
        }

    }
}
