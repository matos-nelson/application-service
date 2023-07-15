package org.rent.circle.application.api.persistence.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.junit.QuarkusTest;
import java.math.BigDecimal;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class AdditionalIncomeSourceTest {

    @Test
    public void AdditionalIncomeSource_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();
        beanTester.addExcludedField("monthlyIncome");

        // Act
        beanTester.testBean(AdditionalIncomeSource.class);

        // Assert

        // Test Excluded fields

        // Arrange
        AdditionalIncomeSource additionalIncomeSource = new AdditionalIncomeSource();

        // Act
        additionalIncomeSource.setMonthlyIncome(BigDecimal.ONE);

        // Assert
        assertNotNull(additionalIncomeSource.getMonthlyIncome());
    }
}
