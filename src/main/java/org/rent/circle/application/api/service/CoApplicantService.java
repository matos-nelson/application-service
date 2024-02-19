package org.rent.circle.application.api.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rent.circle.application.api.dto.UpdateCoApplicantDto;
import org.rent.circle.application.api.persistence.model.CoApplicant;
import org.rent.circle.application.api.persistence.repository.CoApplicantRepository;
import org.rent.circle.application.api.service.mapper.ApplicationMapper;

@AllArgsConstructor
@ApplicationScoped
@Slf4j
public class CoApplicantService {

    private final CoApplicantRepository coApplicantRepository;
    private final ApplicationMapper applicationMapper;

    @Transactional
    public void updateCoApplicant(String managerId, long applicationId, long coApplicantId,
        UpdateCoApplicantDto updateCoApplicantInfo) {

        CoApplicant coApplicant = coApplicantRepository.findCoApplicant(managerId, applicationId, coApplicantId);
        if (coApplicant == null) {
            log.info("Could Not Find CoApplicant With Given Application Id {} CoApplicantId {}", applicationId,
                coApplicantId);
            return;
        }

        applicationMapper.updateCoApplicant(updateCoApplicantInfo, coApplicant);
        coApplicantRepository.persistAndFlush(coApplicant);
    }
}
