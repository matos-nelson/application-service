package org.rent.circle.application.api.resource;

import io.quarkus.security.Authenticated;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.rent.circle.application.api.dto.ApplicationDto;
import org.rent.circle.application.api.dto.SaveApplicationDto;
import org.rent.circle.application.api.dto.SaveCoSignerDto;
import org.rent.circle.application.api.dto.UpdateApplicationStatusDto;
import org.rent.circle.application.api.service.ApplicationService;

@AllArgsConstructor
@Path("/application")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class ApplicationResource {

    private final ApplicationService applicationService;
    private final JsonWebToken jwt;

    @POST
    @PermitAll
    public Long saveApplication(@Valid SaveApplicationDto saveApplication) {
        return applicationService.saveApplication(saveApplication);
    }

    @PATCH
    @Path("/{id}/status")
    @Authenticated
    public void updateApplicationStatus(
        @NotNull @PathParam("id") Long applicationId,
        @Valid UpdateApplicationStatusDto updatedApplicationStatus) {
        applicationService.updateApplicationStatus(applicationId, jwt.getName(), updatedApplicationStatus);
    }

    @GET
    @Path("/{id}")
    @Authenticated
    public ApplicationDto getApplication(@NotNull @PathParam("id") Long applicationId) {
        return applicationService.getApplication(applicationId, jwt.getName());
    }

    @GET
    @Authenticated
    public List<ApplicationDto> getApplications(
        @NotNull @QueryParam("page") @Min(0) Integer page,
        @NotNull @QueryParam("pageSize") @Min(1) Integer pageSize) {
        return applicationService.getApplications(jwt.getName(), page, pageSize);
    }

    @PUT
    @Path("/cosigner/manager/{managerId}")
    @PermitAll
    public Long saveCosigner(
        @NotBlank @PathParam("managerId") String managerId,
        @Valid SaveCoSignerDto saveCoSignerDto) {
        return applicationService.saveCoSigner(managerId, saveCoSignerDto.getApplicantEmail(), saveCoSignerDto.getCoSigner());
    }
}
