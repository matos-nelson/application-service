package org.rent.circle.application.api.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.rent.circle.application.api.dto.ApplicantDto;
import org.rent.circle.application.api.dto.EmployerDto;
import org.rent.circle.application.api.dto.IdentificationDto;
import org.rent.circle.application.api.dto.ResidentialHistoryDto;
import org.rent.circle.application.api.dto.SaveApplicationDto;
import org.rent.circle.application.api.dto.UpdateApplicationStatusDto;
import org.rent.circle.application.api.enums.Status;

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

    @Test
    public void PATCH_WhenGivenAValidRequestToUpdateApplicationStatus_ShouldReturnOk() {
        // Arrange
        UpdateApplicationStatusDto updateApplicationStatusDto = UpdateApplicationStatusDto.builder()
            .status(Status.APPROVED)
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(updateApplicationStatusDto)
            .when()
            .patch("/300/status")
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void PATCH_WhenGivenAnInValidRequestToUpdateApplicationStatus_ShouldReturnBadRequest() {
        // Arrange
        UpdateApplicationStatusDto updateApplicationStatusDto = UpdateApplicationStatusDto.builder()
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(updateApplicationStatusDto)
            .when()
            .patch("/1/status")
            .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void GET_WhenAnApplicationCantBeFound_ShouldReturnNoContent() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("/1")
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void GET_WhenApplicationIsFound_ShouldReturnApplication() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("/100")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body("id", is(100),
                "propertyId", is(1),
                "note", is(nullValue()),
                "status", is("PENDING_APPROVAL"),
                "applicant.firstName", is("First"),
                "applicant.lastName", is("Last"),
                "applicant.email", is("first.last@email.com"),
                "applicant.phone", is("1234567890"),
                "applicant.recentlyEvicted", is(false),
                "applicant.residentialHistories", is(Matchers.hasSize(1)),
                "applicant.employer", is(notNullValue()),
                "applicant.identification", is(notNullValue()),
                "applicant.personalReferences", is(Matchers.hasSize(0)),
                "applicant.coApplicants", is(Matchers.hasSize(0)),
                "applicant.occupants", is(Matchers.hasSize(0)),
                "applicant.pets", is(Matchers.hasSize(0)),
                "applicant.emergencyContact", is(nullValue()),
                "applicant.vehicles", is(Matchers.hasSize(0)),
                "applicant.additionalIncomeSources", is(Matchers.hasSize(0))
            );
    }
}
