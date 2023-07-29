package org.rent.circle.application.api.persistence.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.junit.QuarkusTest;
import java.time.LocalDate;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class OccupantTest {

    @Test
    public void Occupant_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();
        beanTester.addExcludedField("dateOfBirth");

        // Act
        beanTester.testBean(Occupant.class);

        // Assert

        // Test Excluded fields

        // Arrange
        Occupant occupant = new Occupant();

        // Act
        occupant.setDateOfBirth(LocalDate.now());

        // Assert
        assertNotNull(occupant.getDateOfBirth());
    }
}
