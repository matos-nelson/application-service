package org.rent.circle.application.api.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.rent.circle.application.api.enums.Status;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class ApplicantRepositoryTest {

    @Inject
    ApplicantRepository applicantRepository;

    @Test
    @TestTransaction
    public void getApplicantApplicationsStatusCount_WhenApplicantDoesNotExist_ShouldReturn0() {
        // Arrange

        // Act
        long result = applicantRepository.getApplicantApplicationsStatusCount(123L, "email@email.com", Status.PENDING_APPROVAL.name());

        // Assert
        assertEquals(0, result);
    }

    @Test
    @TestTransaction
    public void getApplicantApplicationsStatusCount_WhenApplicantDoesExist_ShouldReturn1() {
        // Arrange

        // Act
        long result = applicantRepository.getApplicantApplicationsStatusCount(3L, "john.doe@email.com", Status.PENDING_APPROVAL.name());

        // Assert
        assertEquals(1, result);
    }
}
