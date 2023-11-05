package org.rent.circle.application.api.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class PrimaryApplicantDtoTest {

    @Test
    public void PrimaryApplicantDto_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();

        // Act
        beanTester.testBean(PrimaryApplicantDto.class);

        // Assert
    }
}
