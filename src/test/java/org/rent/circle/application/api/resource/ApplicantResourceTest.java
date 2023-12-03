package org.rent.circle.application.api.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.security.jwt.Claim;
import io.quarkus.test.security.jwt.JwtSecurity;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.rent.circle.application.api.annotation.AuthManager;
import org.rent.circle.application.api.annotation.AuthUser;
import org.rent.circle.application.api.enums.Status;

@QuarkusTest
@TestHTTPEndpoint(ApplicantResource.class)
@QuarkusTestResource(H2DatabaseTestResource.class)
public class ApplicantResourceTest {

   @Test
    @AuthUser
    public void GET_getApplicationStatusCount_WhenFailsValidation_ShouldReturnBadRequest() {
        // Arrange
        String email = "john.doe";
        String status = Status.PENDING_APPROVAL.name();

        // Act
        // Assert
        given()
            .when()
            .get("/application" + "/status/count?email=" + email + "&status=" + status)
            .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @AuthManager
    public void GET_getApplicationStatusCount_WhenInvoked_ShouldReturnCount() {
        // Arrange
        String email = "john.doe@email.com";
        String status = Status.PENDING_APPROVAL.name();

        // Act
        // Assert
        given()
            .when()
            .get("/application" + "/status/count?email=" + email + "&status=" + status)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body(is("1"));
    }
}
