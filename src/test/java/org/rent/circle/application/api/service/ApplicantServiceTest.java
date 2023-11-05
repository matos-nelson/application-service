package org.rent.circle.application.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.rent.circle.application.api.enums.Status;
import org.rent.circle.application.api.persistence.repository.ApplicantRepository;

@QuarkusTest
public class ApplicantServiceTest {

    @InjectMock
    ApplicantRepository applicantRepository;

    @Inject
    ApplicantService applicantService;

    @Test
    public void getApplicationStatusCount_WhenCalled_ShouldReturnCount() {
        // Arrange
        long managerId = 1L;
        String email = "john.doe@email.com";
        Status status = Status.PENDING_APPROVAL;
        long count = 10;

        when(applicantRepository.getApplicantApplicationsStatusCount(managerId, email, status.name())).thenReturn(count);

        // Act
        Long result = applicantService.getApplicationStatusCount(managerId, email, status);

        // Assert
        assertEquals(count, result);
    }

}
