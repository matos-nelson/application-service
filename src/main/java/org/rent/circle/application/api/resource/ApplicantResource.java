package org.rent.circle.application.api.resource;

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
import org.rent.circle.application.api.enums.Status;
import org.rent.circle.application.api.service.ApplicantService;

@AllArgsConstructor
@Path("/applicant")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class ApplicantResource {

    private final ApplicantService applicantService;

    @GET
    @Path("/application/manager/{managerId}/status/count")
    public Long getApplicationStatusCount(@NotNull @PathParam("managerId") long managerId,
        @Email @QueryParam("email") String email, @NotNull @QueryParam("status") Status status) {
        return applicantService.getApplicationStatusCount(managerId, email, status);
    }
}
