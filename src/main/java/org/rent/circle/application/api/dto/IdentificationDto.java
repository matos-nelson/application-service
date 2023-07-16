package org.rent.circle.application.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
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
public class IdentificationDto {

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    @NotEmpty
    private String ssn;

    private String governmentIssuedId;

    private String issuedLocation;
}
