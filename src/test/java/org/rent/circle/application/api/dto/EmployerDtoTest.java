package org.rent.circle.application.api.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.junit.QuarkusTest;
import java.math.BigDecimal;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class EmployerDtoTest {

    @Test
    public void EmployerDto_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();
        beanTester.addExcludedField("monthlySalary");

        // Act
        beanTester.testBean(EmployerDto.class);

        // Assert

        // Test Excluded fields

        // Arrange
        EmployerDto employerDto = new EmployerDto();

        // Act
        employerDto.setMonthlySalary(BigDecimal.ONE);

        // Assert
        assertNotNull(employerDto.getMonthlySalary());
    }
}
