package org.rent.circle.application.api.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.junit.QuarkusTest;
import java.math.BigDecimal;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class AdditionalIncomeSourceDtoTest {

    @Test
    public void AdditionalIncomeSourceDto_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();
        beanTester.addExcludedField("monthlyIncome");

        // Act
        beanTester.testBean(AdditionalIncomeSourceDto.class);

        // Assert

        // Test Excluded fields

        // Arrange
        AdditionalIncomeSourceDto additionalIncomeSourceDto = new AdditionalIncomeSourceDto();

        // Act
        additionalIncomeSourceDto.setMonthlyIncome(BigDecimal.ONE);

        // Assert
        assertNotNull(additionalIncomeSourceDto.getMonthlyIncome());
    }
}
