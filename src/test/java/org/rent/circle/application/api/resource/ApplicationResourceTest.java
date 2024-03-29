package org.rent.circle.application.api.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.rent.circle.application.api.annotation.AuthNewUser;
import org.rent.circle.application.api.annotation.AuthUser;
import org.rent.circle.application.api.dto.AdditionalIncomeSourceDto;
import org.rent.circle.application.api.dto.ApplicationDto;
import org.rent.circle.application.api.dto.CoSignerDto;
import org.rent.circle.application.api.dto.EmployerDto;
import org.rent.circle.application.api.dto.IdentificationDto;
import org.rent.circle.application.api.dto.PrimaryApplicantDto;
import org.rent.circle.application.api.dto.ResidentialHistoryDto;
import org.rent.circle.application.api.dto.SaveApplicationDto;
import org.rent.circle.application.api.dto.SaveCoSignerDto;
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
        PrimaryApplicantDto primaryApplicantDto = PrimaryApplicantDto.builder()
            .firstName("First")
            .lastName("Last")
            .email("email@email.com")
            .phone("1231231234")
            .residentialHistories(Collections.singletonList(residentialHistoryDto))
            .identification(identificationDto)
            .employer(employerDto)
            .occupants(Collections.emptyList())
            .additionalIncomeSources(Collections.emptyList())
            .pets(Collections.emptyList())
            .vehicles(Collections.emptyList())
            .personalReferences(Collections.emptyList())
            .coApplicants(Collections.emptyList())
            .build();

        SaveApplicationDto saveApplicationDto = SaveApplicationDto.builder()
            .propertyId(1L)
            .managerId("2")
            .primaryApplicant(primaryApplicantDto)
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
    @AuthUser
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
    @AuthUser
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
    @AuthUser
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
    @AuthUser
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
                "managerId", is("auth_user"),
                "note", is(nullValue()),
                "status", is("PENDING_APPROVAL"),
                "primaryApplicant.firstName", is("First"),
                "primaryApplicant.lastName", is("Last"),
                "primaryApplicant.email", is("first.last@email.com"),
                "primaryApplicant.phone", is("1234567890"),
                "primaryApplicant.recentlyEvicted", is(false),
                "primaryApplicant.residentialHistories", is(Matchers.hasSize(1)),
                "primaryApplicant.employer", is(notNullValue()),
                "primaryApplicant.identification", is(notNullValue()),
                "primaryApplicant.personalReferences", is(Matchers.hasSize(0)),
                "primaryApplicant.coApplicants", is(Matchers.hasSize(0)),
                "primaryApplicant.occupants", is(Matchers.hasSize(0)),
                "primaryApplicant.pets", is(Matchers.hasSize(0)),
                "primaryApplicant.emergencyContact", is(nullValue()),
                "primaryApplicant.vehicles", is(Matchers.hasSize(0)),
                "primaryApplicant.additionalIncomeSources", is(Matchers.hasSize(0))
            );
    }

    @Test
    @AuthNewUser
    public void GET_getApplications_WhenApplicationsCantBeFound_ShouldReturnNoRequests() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("?page=0&pageSize=10")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body(is("[]"));
    }

    @Test
    @AuthUser
    public void GET_getApplications_WhenApplicationsAreFound_ShouldReturnRequests() {
        // Arrange

        // Act
        List<ApplicationDto> result = given()
            .when()
            .get("?page=0&pageSize=10")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .as(new TypeRef<>() {
            });

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(100L, result.get(0).getId());
        assertEquals(1L, result.get(0).getPropertyId());
        assertEquals("auth_user", result.get(0).getManagerId());
        assertEquals(Status.PENDING_APPROVAL, result.get(0).getStatus());
        assertNull(result.get(0).getNote());
        assertNotNull(result.get(0).getPrimaryApplicant());
    }

    @Test
    @AuthUser
    public void GET_getApplications_WhenFailsValidation_ShouldReturnBadRequest() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("?page=0")
            .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void PUT_WhenGivenAValidRequestToSaveCoSigner_ShouldReturnOk() {
        // Arrange
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

        AdditionalIncomeSourceDto adi = AdditionalIncomeSourceDto.builder()
            .name("CoSigner Side Hustle")
            .monthlyIncome(BigDecimal.valueOf(1000))
            .build();

        CoSignerDto coSignerDto = CoSignerDto.builder()
            .addressId(1L)
            .firstName("First")
            .lastName("Last")
            .email("email@email.com")
            .phone("1231231234")
            .identification(identificationDto)
            .employer(employerDto)
            .additionalIncomeSources(List.of(adi))
            .build();

        SaveCoSignerDto saveCoSignerDto = SaveCoSignerDto.builder()
            .applicantEmail("tom.jerry@email.com")
            .coSigner(coSignerDto)
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(saveCoSignerDto)
            .when()
            .put("/cosigner/manager/auth_manager")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body(is("1"));
    }

    @Test
    public void PUT_WhenGivenAValidRequestToSaveCoSignerButApplicationDoesNotExist_ShouldReturnNoContent() {
        // Arrange
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

        AdditionalIncomeSourceDto adi = AdditionalIncomeSourceDto.builder()
            .name("CoSigner Side Hustle")
            .monthlyIncome(BigDecimal.valueOf(1000))
            .build();

        CoSignerDto coSignerDto = CoSignerDto.builder()
            .addressId(1L)
            .firstName("First")
            .lastName("Last")
            .email("email@email.com")
            .phone("1231231234")
            .identification(identificationDto)
            .employer(employerDto)
            .additionalIncomeSources(List.of(adi))
            .build();

        SaveCoSignerDto saveCoSignerDto = SaveCoSignerDto.builder()
            .applicantEmail("tom.jerry@email.com")
            .coSigner(coSignerDto)
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(saveCoSignerDto)
            .when()
            .put("/cosigner/manager/3131")
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void PUT_WhenGivenAnInValidRequestToSaveCoSigner_ShouldReturnBadRequest() {
        // Arrange
        SaveCoSignerDto saveCoSignerDto = SaveCoSignerDto.builder().build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(saveCoSignerDto)
            .when()
            .put("/cosigner/manager/3")
            .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
