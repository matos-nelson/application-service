package org.rent.circle.application.api.persistence.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.junit.QuarkusTest;
import java.math.BigDecimal;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class EmployerTest {

    @Test
    public void Employer_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();
        beanTester.addExcludedField("monthlySalary");

        // Act
        beanTester.testBean(Employer.class);

        // Assert

        // Test Excluded fields

        // Arrange
        Employer employer = new Employer();

        // Act
        employer.setMonthlySalary(BigDecimal.ONE);

        // Assert
        assertNotNull(employer.getMonthlySalary());
    }
}
