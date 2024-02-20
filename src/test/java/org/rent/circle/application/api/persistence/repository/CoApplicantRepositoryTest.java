package org.rent.circle.application.api.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.rent.circle.application.api.persistence.model.CoApplicant;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class CoApplicantRepositoryTest {

    @Inject
    CoApplicantRepository coApplicantRepository;

    @Test
    @TestTransaction
    public void findCoApplicant_WhenCoApplicantDoesNotExist_ShouldReturnNull() {
        // Arrange

        // Act
        CoApplicant result = coApplicantRepository.findCoApplicant("no_co_applicant", 123L, 123L);

        // Assert
        assertNull(result);
    }

    @Test
    @TestTransaction
    public void findCoApplicant_WhenCoApplicantExists_ShouldReturnCoApplicant() {
        // Arrange

        // Act
        CoApplicant result = coApplicantRepository.findCoApplicant("auth_co_applicant", 700L, 600L);

        // Assert
        assertNotNull(result);
    }
}
