package org.rent.circle.application.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.rent.circle.application.api.dto.ApplicationDto;
import org.rent.circle.application.api.dto.SaveApplicationDto;
import org.rent.circle.application.api.enums.Status;
import org.rent.circle.application.api.persistence.model.Application;
import org.rent.circle.application.api.persistence.repository.ApplicationRepository;
import org.rent.circle.application.api.service.mapper.ApplicationMapper;

@QuarkusTest
public class ApplicationServiceTest {

    @InjectMock
    ApplicationRepository applicationRepository;

    @InjectMock
    ApplicationMapper applicationMapper;

    @Inject
    ApplicationService applicationService;


    @Test
    public void saveApplication_WhenCalled_ShouldReturnSaveApplicationId() {
        // Arrange
        SaveApplicationDto saveApplication = SaveApplicationDto.builder()
            .propertyId(1L)
            .build();

        Application application = new Application();
        application.setId(100L);
        when(applicationMapper.toModel(saveApplication)).thenReturn(application);

        // Act
        Long result = applicationService.saveApplication(saveApplication);

        // Assert
        assertNotNull(result);
        assertEquals(application.getId(), result);
        assertEquals(Status.PENDING_APPROVAL.name(), application.getStatus());
    }

    @Test
    public void updateApplicationStatus_WhenApplicationCantBeFound_ShouldReturn() {
        // Arrange
        Application application = new Application();
        Long applicationId = 1L;
        when(applicationRepository.findById(applicationId)).thenReturn(null);

        // Act
        applicationService.updateApplicationStatus(applicationId, Status.APPROVED, "note");

        // Assert
        verify(applicationRepository, times(0)).persist(application);
    }

    @Test
    public void updateApplicationStatus_WhenApplicationIsFound_ShouldUpdateStatus() {
        // Arrange
        Long applicationId = 1L;
        String note = "my note";
        Application application = new Application();
        application.setId(applicationId);
        application.setStatus(Status.PENDING_APPROVAL.name());

        when(applicationRepository.findById(applicationId)).thenReturn(application);

        // Act
        applicationService.updateApplicationStatus(applicationId, Status.APPROVED, note);

        // Assert
        assertEquals(Status.APPROVED.name(), application.getStatus());
        assertEquals(note, application.getNote());
        verify(applicationRepository, times(1)).persist(application);
    }

    @Test
    public void getApplication_WhenApplicationIsNotFound_ShouldReturnNull() {
        // Arrange
        Long applicationId = 1L;

        when(applicationRepository.findById(applicationId)).thenReturn(null);

        // Act
        ApplicationDto result = applicationService.getApplication(applicationId);

        // Assert
        assertNull(result);
    }

    @Test
    public void getApplication_WhenApplicationIsFound_ShouldReturnApplication() {
        // Arrange
        Long applicationId = 1L;
        Application application = new Application();
        application.setId(applicationId);
        application.setStatus(Status.PENDING_APPROVAL.name());

        ApplicationDto applicationDto = ApplicationDto.builder()
            .id(applicationId)
            .status(Status.valueOf(application.getStatus()))
            .build();
        when(applicationRepository.findById(applicationId)).thenReturn(application);
        when(applicationMapper.toDto(application)).thenReturn(applicationDto);

        // Act
        ApplicationDto result = applicationService.getApplication(applicationId);

        // Assert
        assertNotNull(result);
        assertEquals(applicationDto, result);
    }

    @Test
    public void getApplications_WhenApplicationsWithGivenManagerIdAreNotFound_ShouldReturnEmptyList() {
        // Arrange
        Long ownerId = 1L;
        int page = 2;
        int pageSize = 10;

        when(applicationRepository.findApplications(ownerId, page, pageSize)).thenReturn(null);

        // Act
        List<ApplicationDto> result = applicationService.getApplications(ownerId, page, pageSize);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    public void getApplications_WhenApplicationWithGivenManagerIdIdAreFound_ShouldReturnList() {
        // Arrange
        Long ownerId = 1L;
        int page = 2;
        int pageSize = 10;
        List<Application> applications = Collections.singletonList(new Application());
        when(applicationRepository.findApplications(ownerId, page, pageSize)).thenReturn(applications);
        when(applicationMapper.toDtoList(applications)).thenReturn(
            Collections.singletonList(new ApplicationDto()));

        // Act
        List<ApplicationDto> result = applicationService.getApplications(ownerId, page, pageSize);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
    }
}
