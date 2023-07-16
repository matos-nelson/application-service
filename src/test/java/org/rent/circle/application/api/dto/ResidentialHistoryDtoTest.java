package org.rent.circle.application.api.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.junit.QuarkusTest;
import java.math.BigDecimal;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ResidentialHistoryDtoTest {

    @Test
    public void ResidentialHistoryDto_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();
        beanTester.addExcludedField("monthlyRent");

        // Act
        beanTester.testBean(ResidentialHistoryDto.class);

        // Assert

        // Test Excluded fields

        // Arrange
        ResidentialHistoryDto residentialHistoryDto = new ResidentialHistoryDto();

        // Act
        residentialHistoryDto.setMonthlyRent(BigDecimal.ONE);

        // Assert
        assertNotNull(residentialHistoryDto.getMonthlyRent());
    }
}
