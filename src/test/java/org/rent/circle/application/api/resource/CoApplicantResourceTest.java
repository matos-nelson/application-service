package org.rent.circle.application.api.resource;

import static io.restassured.RestAssured.given;

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
import org.rent.circle.application.api.annotation.AuthUser;
import org.rent.circle.application.api.dto.EmployerDto;
import org.rent.circle.application.api.dto.IdentificationDto;
import org.rent.circle.application.api.dto.ResidentialHistoryDto;
import org.rent.circle.application.api.dto.UpdateCoApplicantDto;

@QuarkusTest
@TestHTTPEndpoint(CoApplicantResource.class)
@QuarkusTestResource(H2DatabaseTestResource.class)
public class CoApplicantResourceTest {

    @Test
    public void PATCH_WhenGivenAValidRequestToUpdateCoApplicant_ShouldReturnOk() {
        // Arrange
        long coApplicantId = 600L;
        long applicationId = 700L;
        ResidentialHistoryDto residentialHistoryDto = ResidentialHistoryDto.builder()
            .addressId(1L)
            .residedFromMonth(Month.JANUARY)
            .residedFromYear(2000)
            .residedToMonth(Month.JANUARY)
            .residedToYear(2010)
            .monthlyRent(BigDecimal.ONE)
            .landlordName("Landlord name")
            .landlordPhone("1231231234")
            .landlordEmail("landlord@email.com")
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
            .supervisorEmail("supervisor@email.com")
            .build();
        UpdateCoApplicantDto updateCoApplicantDto = UpdateCoApplicantDto.builder()
            .phone("1239902909")
            .pets(Collections.emptyList())
            .vehicles(Collections.emptyList())
            .personalReferences(Collections.emptyList())
            .residentialHistories(Collections.singletonList(residentialHistoryDto))
            .employer(employerDto)
            .occupants(Collections.emptyList())
            .additionalIncomeSources(Collections.emptyList())
            .identification(identificationDto)
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(updateCoApplicantDto)
            .when()
            .patch("/" + coApplicantId + "/manager/auth_co_applicant/application/" + applicationId)
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @AuthUser
    public void PATCH_WhenGivenAnInValidRequestToUpdateCoApplicant_ShouldReturnBadRequest() {
        // Arrange
        long coApplicantId = 600L;
        long applicationId = 700L;
        UpdateCoApplicantDto updateCoApplicantDto = UpdateCoApplicantDto.builder()
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(updateCoApplicantDto)
            .when()
            .patch("/" + coApplicantId + "/manager/auth_user/application/" + applicationId)
            .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
