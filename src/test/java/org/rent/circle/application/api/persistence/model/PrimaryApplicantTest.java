package org.rent.circle.application.api.persistence.model;

import io.quarkus.test.junit.QuarkusTest;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class PrimaryApplicantTest {

    @Test
    public void PrimaryApplicant_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();

        // Act
        beanTester.testBean(PrimaryApplicant.class);

        // Assert

    }
}
