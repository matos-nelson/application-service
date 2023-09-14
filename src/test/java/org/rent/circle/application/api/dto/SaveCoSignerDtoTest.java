package org.rent.circle.application.api.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class SaveCoSignerDtoTest {

    @Test
    public void SaveCoSignerDto_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();

        // Act
        beanTester.testBean(SaveCoSignerDto.class);

        // Assert
    }
}
