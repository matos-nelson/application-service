package org.rent.circle.application.api.resource;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rent.circle.application.api.dto.UpdateCoApplicantDto;
import org.rent.circle.application.api.service.CoApplicantService;

@AllArgsConstructor
@Path("/coapplicant")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class CoApplicantResource {

    private final CoApplicantService coApplicantService;

    @PermitAll
    @PATCH
    @Path("/{id}/manager/{managerId}/application/{applicationId}")
    public void updateCoApplicant(@NotBlank @PathParam("managerId") String managerId,
        @PathParam("id") long coApplicantId,
        @PathParam("applicationId") long applicationId,
        @Valid UpdateCoApplicantDto updateCoApplicantInfo) {
        coApplicantService.updateCoApplicant(managerId, applicationId, coApplicantId, updateCoApplicantInfo);
    }
}
