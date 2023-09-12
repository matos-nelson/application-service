package org.rent.circle.application.api.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.rent.circle.application.api.dto.AdditionalIncomeSourceDto;
import org.rent.circle.application.api.dto.CoSignerDto;
import org.rent.circle.application.api.dto.EmployerDto;
import org.rent.circle.application.api.dto.IdentificationDto;
import org.rent.circle.application.api.enums.Suffix;
import org.rent.circle.application.api.persistence.model.CoSigner;

@QuarkusTest
public class CoSignerMapperTest {

    @Inject
    CoSignerMapper coSignerMapper;

    @Test
    public void toModel_WhenGivenNull_ShouldReturnNull() {
        // Arrange

        // Act
        CoSigner result = coSignerMapper.toModel(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void toModel_WhenGivenCoSignerDto_ShouldMap() {
        // Arrange
        CoSignerDto coSignerDto = CoSignerDto.builder()
            .firstName("first")
            .lastName("last")
            .middleName("middle")
            .suffix(Suffix.IV)
            .email("applicant@email.com")
            .phone("1234567890")
            .build();

        // Act
        CoSigner result = coSignerMapper.toModel(coSignerDto);

        // Assert
        assertNotNull(result);
        assertEquals(coSignerDto.getFirstName(), result.getFirstName());
        assertEquals(coSignerDto.getLastName(), result.getLastName());
        assertEquals(coSignerDto.getMiddleName(), result.getMiddleName());
        assertEquals(coSignerDto.getSuffix().value, result.getSuffix());
        assertEquals(coSignerDto.getEmail(), result.getEmail());
        assertEquals(coSignerDto.getPhone(), result.getPhone());
    }

    @Test
    public void toModel_WhenGivenACoSignerWithIdentification_ShouldMap() {
        // Arrange
        IdentificationDto identification = IdentificationDto.builder()
            .dateOfBirth(LocalDate.of(2020, 3, 12))
            .ssn("SSN")
            .governmentIssuedId("Driver Licence")
            .issuedLocation("State")
            .build();
        CoSignerDto coSignerDto = CoSignerDto.builder()
            .identification(identification)
            .build();

        // Act
        CoSigner result = coSignerMapper.toModel(coSignerDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getIdentification());
        assertEquals(identification.getDateOfBirth(), result.getIdentification().getDateOfBirth());
        assertEquals(identification.getSsn(), result.getIdentification().getSsn());
        assertEquals(identification.getGovernmentIssuedId(), result.getIdentification().getGovernmentIssuedId());
        assertEquals(identification.getIssuedLocation(), result.getIdentification().getIssuedLocation());
    }

    @Test
    public void toModel_WhenGivenACoSignerWithEmployer_ShouldMap() {
        // Arrange
        EmployerDto employer = EmployerDto.builder()
            .addressId(1L)
            .name("Employer Name")
            .phone("1234567890")
            .monthlySalary(BigDecimal.valueOf(123.3))
            .positionHeld("Position")
            .yearsWorked((byte) 10)
            .supervisorName("Supervisor Name")
            .supervisorEmail("supervisor@email.com")
            .build();
        CoSignerDto coSignerDto = CoSignerDto.builder()
            .employer(employer)
            .build();

        // Act
        CoSigner result = coSignerMapper.toModel(coSignerDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getEmployer());
        assertEquals(employer.getAddressId(), result.getEmployer().getAddressId());
        assertEquals(employer.getName(), result.getEmployer().getName());
        assertEquals(employer.getPhone(), result.getEmployer().getPhone());
        assertEquals(employer.getMonthlySalary(), result.getEmployer().getMonthlySalary());
        assertEquals(employer.getPositionHeld(), result.getEmployer().getPositionHeld());
        assertEquals(employer.getYearsWorked(), result.getEmployer().getYearsWorked());
        assertEquals(employer.getSupervisorName(), result.getEmployer().getSupervisorName());
        assertEquals(employer.getSupervisorEmail(), result.getEmployer().getSupervisorEmail());
    }

    @Test
    public void toModel_WhenGivenACoSignerWithAdditionalIncomeSource_ShouldMap() {
        // Arrange
        AdditionalIncomeSourceDto additionalIncome = AdditionalIncomeSourceDto.builder()
            .name("Additional Income")
            .monthlyIncome(BigDecimal.valueOf(567.32))
            .build();
        CoSignerDto coSignerDto = CoSignerDto.builder()
            .additionalIncomeSources(Collections.singletonList(additionalIncome))
            .build();

        // Act
        CoSigner result = coSignerMapper.toModel(coSignerDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getAdditionalIncomeSources());
        assertEquals(coSignerDto.getAdditionalIncomeSources().size(), coSignerDto.getAdditionalIncomeSources().size());
        assertEquals(additionalIncome.getName(), result.getAdditionalIncomeSources().get(0).getName());
        assertEquals(additionalIncome.getMonthlyIncome(), result.getAdditionalIncomeSources().get(0).getMonthlyIncome());
    }
}
