package org.rent.circle.application.api.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rent.circle.application.api.dto.ApplicationDto;
import org.rent.circle.application.api.dto.SaveApplicationDto;
import org.rent.circle.application.api.enums.Status;
import org.rent.circle.application.api.persistence.model.Application;
import org.rent.circle.application.api.persistence.repository.ApplicationRepository;
import org.rent.circle.application.api.service.mapper.ApplicationMapper;

@AllArgsConstructor
@ApplicationScoped
@Slf4j
public class ApplicationService {

    private final ApplicationMapper applicationMapper;
    private final ApplicationRepository applicationRepository;

    @Transactional
    public Long saveApplication(SaveApplicationDto saveApplication) {
        Application application = applicationMapper.toModel(saveApplication);

        application.setStatus(Status.PENDING_APPROVAL.name());
        applicationRepository.persist(application);
        return application.getId();
    }

    @Transactional
    public void updateApplicationStatus(Long applicationId, Status status, String note) {

        Application application = applicationRepository.findById(applicationId);
        if (application == null) {
            return;
        }

        application.setStatus(status.name());
        application.setNote(note);

        applicationRepository.persist(application);
    }

    public ApplicationDto getApplication(Long id) {
        Application application = applicationRepository.findById(id);
        return applicationMapper.toDto(application);
    }
}

