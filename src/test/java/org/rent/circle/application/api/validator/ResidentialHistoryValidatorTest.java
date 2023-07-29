package org.rent.circle.application.api.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.math.BigDecimal;
import java.time.Month;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.rent.circle.application.api.dto.ResidentialHistoryDto;
import org.rent.circle.application.api.validator.annotation.ValidHistory;

@QuarkusTest
public class ResidentialHistoryValidatorTest {

    @Inject
    Validator validator;

    public class TestDto {

        @ValidHistory
        ResidentialHistoryDto residentialHistoryDto;

        public TestDto(ResidentialHistoryDto residentialHistoryDto) {
            this.residentialHistoryDto = residentialHistoryDto;
        }
    }

    @Test
    public void isValid_WhenGivenAnInvalidHistory_ShouldReturnViolations() {
        // Arrange
        ResidentialHistoryDto residentialHistory = ResidentialHistoryDto.builder()
            .addressId(1L)
            .monthlyRent(BigDecimal.ONE)
            .landlordName("name")
            .landlordPhone("phone")
            .landlordEmail("email")
            .residedFromMonth(Month.JANUARY)
            .residedFromYear(2020)
            .residedToMonth(Month.JANUARY)
            .residedToYear(2000)
            .build();

        TestDto testDto = new TestDto(residentialHistory);

        // Act
        Set<ConstraintViolation<TestDto>> violations = validator.validate(testDto);

        // Assert
        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Must Provide From Dates That Occur Before To Dates");
    }

    @Test
    public void isValid_WhenGivenAValidHistory_ShouldReturnNoViolation() {
        // Arrange
        ResidentialHistoryDto residentialHistory = ResidentialHistoryDto.builder()
            .addressId(1L)
            .monthlyRent(BigDecimal.ONE)
            .landlordName("name")
            .landlordPhone("phone")
            .landlordEmail("email")
            .residedFromMonth(Month.JANUARY)
            .residedFromYear(2000)
            .residedToMonth(Month.FEBRUARY)
            .residedToYear(2000)
            .build();

        TestDto testDto = new TestDto(residentialHistory);

        // Act
        Set<ConstraintViolation<TestDto>> violations = validator.validate(testDto);

        // Assert
        assertEquals(violations.size(), 0);
    }
}
