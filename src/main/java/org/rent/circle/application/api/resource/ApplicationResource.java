package org.rent.circle.application.api.resource;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rent.circle.application.api.dto.SaveApplicationDto;
import org.rent.circle.application.api.dto.UpdateApplicationStatusDto;
import org.rent.circle.application.api.service.ApplicationService;

@AllArgsConstructor
@Path("/application")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class ApplicationResource {

    private final ApplicationService applicationService;

    @POST
    public Long saveApplication(@Valid SaveApplicationDto saveApplication) {
        return applicationService.saveApplication(saveApplication);
    }

    @PATCH
    @Path("/{id}/status")
    public void updateApplicationStatus(
        @NotNull @PathParam("id") Long applicationId,
        @Valid UpdateApplicationStatusDto applicationStatus) {
        applicationService.updateApplicationStatus(applicationId, applicationStatus.getStatus(), applicationStatus.getNote());
    }
}
