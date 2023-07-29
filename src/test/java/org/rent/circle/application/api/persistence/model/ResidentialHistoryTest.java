package org.rent.circle.application.api.persistence.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.junit.QuarkusTest;
import java.math.BigDecimal;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ResidentialHistoryTest {

    @Test
    public void ResidentialHistory_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();
        beanTester.addExcludedField("monthlyRent");

        // Act
        beanTester.testBean(ResidentialHistory.class);

        // Assert

        // Test Excluded fields

        // Arrange
        ResidentialHistory residentialHistory = new ResidentialHistory();

        // Act
        residentialHistory.setMonthlyRent(BigDecimal.ONE);

        // Assert
        assertNotNull(residentialHistory.getMonthlyRent());
    }
}
