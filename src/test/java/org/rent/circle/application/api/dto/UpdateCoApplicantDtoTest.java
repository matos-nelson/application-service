package org.rent.circle.application.api.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class UpdateCoApplicantDtoTest {

    @Test
    public void UpdateCoApplicantDto_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();

        // Act
        beanTester.testBean(UpdateCoApplicantDto.class);

        // Assert

    }
}
