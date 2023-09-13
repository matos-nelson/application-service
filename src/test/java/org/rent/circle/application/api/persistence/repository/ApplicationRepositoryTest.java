package org.rent.circle.application.api.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.rent.circle.application.api.persistence.model.Application;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class ApplicationRepositoryTest {

    @Inject
    ApplicationRepository applicationRepository;

    @Test
    @TestTransaction
    public void findApplication_WhenApplicationDoesNotExist_ShouldReturnNull() {
        // Arrange

        // Act
        Application result = applicationRepository.findApplication(123L, 456L);

        // Assert
        assertNull(result);
    }

    @Test
    @TestTransaction
    public void findApplication_WhenApplicationDoesExist_ShouldReturnApplication() {
        // Arrange

        // Act
        Application result = applicationRepository.findApplication(100L, 2L);

        // Assert
        assertNotNull(result);
    }

    @Test
    @TestTransaction
    public void findApplications_WhenApplicationsDoNotExist_ShouldReturnNoApplications() {
        // Arrange

        // Act
        List<Application> result = applicationRepository.findApplications(456L, 0, 10);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @TestTransaction
    public void findApplications_WhenApplicationsDoExist_ShouldReturnApplications() {
        // Arrange

        // Act
        List<Application> result = applicationRepository.findApplications(2L, 0, 10);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    @TestTransaction
    public void findApplications_WhenApplicationsDoNotExistInPage_ShouldReturnNoApplications() {
        // Arrange

        // Act
        List<Application> result = applicationRepository.findApplications(2L, 10, 10);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @TestTransaction
    public void findApplicantsPendingApplication_WhenApplicationDoesNotExist_ShouldReturnNull() {
        // Arrange

        // Act
        Application result = applicationRepository.findApplicantsPendingApplication(3L, "nonehere@gmail.com");

        // Assert
        assertNull(result);
    }

    @Test
    @TestTransaction
    public void findApplicantsPendingApplication_WhenApplicationsDoExist_ShouldReturnApplication() {
        // Arrange

        // Act
        Application result = applicationRepository.findApplicantsPendingApplication(3L, "john.doe@email.com");

        // Assert
        assertNotNull(result);
    }
}
