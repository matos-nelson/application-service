package org.rent.circle.application.api.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    public void findMaintenanceRequests_WhenRequestsDoNotExist_ShouldReturnNoRequests() {
        // Arrange

        // Act
        List<Application> result = applicationRepository.findApplications(456L, 0, 10);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @TestTransaction
    public void findMaintenanceRequests_WhenRequestsDoExist_ShouldReturnRequests() {
        // Arrange

        // Act
        List<Application> result = applicationRepository.findApplications(2L, 0, 10);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    @TestTransaction
    public void findMaintenanceRequests_WhenRequestsDoNotExistInPage_ShouldReturnNoRequests() {
        // Arrange

        // Act
        List<Application> result = applicationRepository.findApplications(2L, 10, 10);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
