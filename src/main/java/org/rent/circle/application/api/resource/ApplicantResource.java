package org.rent.circle.application.api.resource;

import io.quarkus.security.Authenticated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.rent.circle.application.api.enums.Status;
import org.rent.circle.application.api.service.ApplicantService;

@AllArgsConstructor
@Authenticated
@Path("/applicant")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class ApplicantResource {

    private final ApplicantService applicantService;
    private final JsonWebToken jwt;

    @GET
    @Path("/application/status/count")
    public Long getApplicationStatusCount(@Email @QueryParam("email") String email,
        @NotNull @QueryParam("status") Status status) {
        return applicantService.getApplicationStatusCount(jwt.getName(), email, status);
    }
}
