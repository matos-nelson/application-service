package org.rent.circle.application.api.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rent.circle.application.api.enums.Status;
import org.rent.circle.application.api.persistence.repository.ApplicantRepository;

@AllArgsConstructor
@ApplicationScoped
@Slf4j
public class ApplicantService {

    @Inject
    ApplicantRepository applicantRepository;

    public long getApplicationStatusCount(String managerId, String email, Status status) {
        return applicantRepository.getApplicantApplicationsStatusCount(managerId, email, status.name());
    }
}
