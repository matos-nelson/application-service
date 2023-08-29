package org.rent.circle.application.api.resource;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rent.circle.application.api.dto.ApplicationDto;
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
    @Path("/{id}/manager/{managerId}/status")
    public void updateApplicationStatus(
        @NotNull @PathParam("id") Long applicationId,
        @NotNull @PathParam("managerId") Long managerId,
        @Valid UpdateApplicationStatusDto updatedApplicationStatus) {
        applicationService.updateApplicationStatus(applicationId, managerId, updatedApplicationStatus);
    }

    @GET
    @Path("/{id}/manager/{managerId}")
    public ApplicationDto getApplication(@NotNull @PathParam("id") Long applicationId, @NotNull @PathParam("managerId") Long managerId) {
        return applicationService.getApplication(applicationId, managerId);
    }

    @GET
    @Path("/manager/{managerId}")
    public List<ApplicationDto> getApplications(@NotNull @PathParam("managerId") Long managerId,
        @NotNull @QueryParam("page") @Min(0) Integer page,
        @NotNull @QueryParam("pageSize") @Min(1) Integer pageSize) {
        return applicationService.getApplications(managerId, page, pageSize);
    }
}
