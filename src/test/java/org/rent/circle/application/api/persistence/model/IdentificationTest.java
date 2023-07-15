package org.rent.circle.application.api.persistence.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.junit.QuarkusTest;
import java.time.LocalDate;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class IdentificationTest {

    @Test
    public void Identification_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();
        beanTester.addExcludedField("dateOfBirth");

        // Act
        beanTester.testBean(Identification.class);

        // Assert

        // Test Excluded fields

        // Arrange
        Identification identification = new Identification();

        // Act
        identification.setDateOfBirth(LocalDate.now());

        // Assert
        assertNotNull(identification.getDateOfBirth());
    }
}
