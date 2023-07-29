package org.rent.circle.application.api.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.junit.QuarkusTest;
import java.time.LocalDate;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class OccupantDtoTest {

    @Test
    public void OccupantDto_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();
        beanTester.addExcludedField("dateOfBirth");

        // Act
        beanTester.testBean(OccupantDto.class);

        // Assert

        // Test Excluded fields

        // Arrange
        OccupantDto occupantDto = new OccupantDto();

        // Act
        occupantDto.setDateOfBirth(LocalDate.now());

        // Assert
        assertNotNull(occupantDto.getDateOfBirth());
    }
}
