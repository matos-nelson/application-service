package org.rent.circle.application.api.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.rent.circle.application.api.dto.UpdateCoApplicantDto;
import org.rent.circle.application.api.persistence.model.CoApplicant;
import org.rent.circle.application.api.persistence.repository.CoApplicantRepository;
import org.rent.circle.application.api.service.mapper.ApplicationMapper;

@QuarkusTest
public class CoApplicantServiceTest {

    @InjectMock
    CoApplicantRepository coApplicantRepository;

    @InjectMock
    ApplicationMapper applicationMapper;

    @Inject
    CoApplicantService coApplicantService;

    @Test
    public void updateCoApplicant_WhenCoApplicantCantBeFound_ShouldNotUpdate() {
        // Arrange
        String managerId = "managerId";
        long applicationId = 1L;
        long coApplicantId = 2L;
        UpdateCoApplicantDto updateCoApplicantDto = UpdateCoApplicantDto.builder()
            .build();

        when(coApplicantRepository.findCoApplicant(managerId, applicationId, coApplicantId)).thenReturn(null);

        // Act
        coApplicantService.updateCoApplicant(managerId, applicationId, coApplicantId, updateCoApplicantDto);

        // Assert
        verify(coApplicantRepository, times(0)).persist((CoApplicant) Mockito.any());
        verify(applicationMapper, times(0)).updateCoApplicant(Mockito.any(), Mockito.any());
    }

    @Test
    public void updateCoApplicant_WhenCalled_ShouldUpdate() {
        // Arrange
        String managerId = "managerId";
        long applicationId = 1L;
        long coApplicantId = 2L;
        UpdateCoApplicantDto updateCoApplicantDto = UpdateCoApplicantDto.builder()
            .build();
        CoApplicant coApplicant = new CoApplicant();

        when(coApplicantRepository.findCoApplicant(managerId, applicationId, coApplicantId)).thenReturn(coApplicant);

        // Act
        coApplicantService.updateCoApplicant(managerId, applicationId, coApplicantId, updateCoApplicantDto);

        // Assert
        verify(coApplicantRepository, times(1)).persist(coApplicant);
        verify(applicationMapper, times(1)).updateCoApplicant(updateCoApplicantDto, coApplicant);
    }
}
