package org.rent.circle.application.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
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
}
