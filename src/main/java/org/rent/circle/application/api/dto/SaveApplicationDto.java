package org.rent.circle.application.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveApplicationDto {

    @NotNull
    private Long managerId;

    @NotNull
    private Long propertyId;

    @NotNull
    @Valid
    private PrimaryApplicantDto primaryApplicant;
}
