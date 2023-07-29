package org.rent.circle.application.api.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.junit.QuarkusTest;
import java.time.LocalDate;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class IdentificationDtoTest {

    @Test
    public void IdentificationDto_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();
        beanTester.addExcludedField("dateOfBirth");

        // Act
        beanTester.testBean(IdentificationDto.class);

        // Assert

        // Test Excluded fields

        // Arrange
        IdentificationDto identificationDto = new IdentificationDto();

        // Act
        identificationDto.setDateOfBirth(LocalDate.now());

        // Assert
        assertNotNull(identificationDto.getDateOfBirth());
    }
}
