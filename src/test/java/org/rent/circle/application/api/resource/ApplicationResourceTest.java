package org.rent.circle.application.api.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.rent.circle.application.api.dto.ApplicantDto;
import org.rent.circle.application.api.dto.EmployerDto;
import org.rent.circle.application.api.dto.IdentificationDto;
import org.rent.circle.application.api.dto.ResidentialHistoryDto;
import org.rent.circle.application.api.dto.SaveApplicationDto;

@QuarkusTest
@TestHTTPEndpoint(ApplicationResource.class)
@QuarkusTestResource(H2DatabaseTestResource.class)
public class ApplicationResourceTest {

    @Test
    public void Post_WhenGivenAValidRequestToSave_ShouldReturnSavedApplicationId() {
        // Arrange
        ResidentialHistoryDto residentialHistoryDto = ResidentialHistoryDto.builder()
            .addressId(1L)
            .residedFromMonth(Month.JANUARY)
            .residedFromYear(2000)
            .residedToMonth(Month.JANUARY)
            .residedToYear(2010)
            .monthlyRent(BigDecimal.ONE)
            .landlordName("Landlord name")
            .landlordPhone("1231231234")
            .landlordEmail("email")
            .build();

        IdentificationDto identificationDto = IdentificationDto.builder()
            .dateOfBirth(LocalDate.now())
            .ssn("123-45-6789")
            .build();

        EmployerDto employerDto = EmployerDto.builder()
            .addressId(1L)
            .name("name")
            .phone("1231231234")
            .monthlySalary(BigDecimal.ONE)
            .positionHeld("Position")
            .yearsWorked((byte) 2)
            .supervisorName("supervisor name")
            .supervisorEmail("supervisor email")
            .build();
        ApplicantDto applicantDto = ApplicantDto.builder()
            .firstName("First")
            .lastName("Last")
            .email("email")
            .phone("1231231234")
            .residentialHistories(Collections.singletonList(residentialHistoryDto))
            .identification(identificationDto)
            .employer(employerDto)
            .build();

        SaveApplicationDto saveApplicationDto = SaveApplicationDto.builder()
            .propertyId(1L)
            .applicant(applicantDto)
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(saveApplicationDto)
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body(is("1"));
    }

    @Test
    public void Post_WhenGivenAnInValidRequestToSave_ShouldReturnBadRequest() {
        // Arrange
        SaveApplicationDto saveApplicationDto = SaveApplicationDto.builder()
            .propertyId(1L)
            .build();
        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(saveApplicationDto)
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
