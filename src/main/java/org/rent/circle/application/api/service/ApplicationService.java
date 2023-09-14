package org.rent.circle.application.api.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rent.circle.application.api.dto.ApplicationDto;
import org.rent.circle.application.api.dto.CoSignerDto;
import org.rent.circle.application.api.dto.SaveApplicationDto;
import org.rent.circle.application.api.dto.UpdateApplicationStatusDto;
import org.rent.circle.application.api.enums.Status;
import org.rent.circle.application.api.persistence.model.Application;
import org.rent.circle.application.api.persistence.model.CoSigner;
import org.rent.circle.application.api.persistence.repository.ApplicationRepository;
import org.rent.circle.application.api.service.mapper.ApplicationMapper;
import org.rent.circle.application.api.service.mapper.CoSignerMapper;

@AllArgsConstructor
@ApplicationScoped
@Slf4j
public class ApplicationService {

    private final ApplicationMapper applicationMapper;
    private final CoSignerMapper coSignerMapper;
    private final ApplicationRepository applicationRepository;

    @Transactional
    public Long saveApplication(SaveApplicationDto saveApplication) {
        Application application = applicationMapper.toModel(saveApplication);

        application.setStatus(Status.PENDING_APPROVAL.name());
        applicationRepository.persist(application);
        return application.getId();
    }

    @Transactional
    public void updateApplicationStatus(Long applicationId, Long managerId, UpdateApplicationStatusDto updatedStatus) {

        Application application = applicationRepository.findApplication(applicationId, managerId);
        if (application == null) {
            return;
        }

        application.setStatus(updatedStatus.getStatus().name());
        application.setNote(updatedStatus.getNote());

        applicationRepository.persist(application);
    }

    public ApplicationDto getApplication(Long id, Long managerId) {
        Application application = applicationRepository.findApplication(id, managerId);
        return applicationMapper.toDto(application);
    }

    public List<ApplicationDto> getApplications(Long managerId, int page, int pageSize) {
        List<Application> applications = applicationRepository.findApplications(managerId, page, pageSize);
        return applicationMapper.toDtoList(applications);
    }

    @Transactional
    public Long saveCoSigner(long managerId, String applicantEmail, CoSignerDto coSignerInfo) {

        Application application = applicationRepository.findApplicantsPendingApplication(managerId, applicantEmail);
        if (application == null) {
            return null;
        }

        CoSigner coSigner = coSignerMapper.toModel(coSignerInfo);
        application.setCoSigner(coSigner);

        applicationRepository.persistAndFlush(application);
        return coSigner.getId();
    }
}
