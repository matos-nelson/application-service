package org.rent.circle.application.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rent.circle.application.api.enums.Suffix;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrimaryApplicantDto {

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    private String middleName;

    private Suffix suffix;

    @Email
    private String email;

    @NotNull
    @NotBlank
    private String phone;

    private boolean recentlyEvicted;

    private String evictionExplanation;

    @NotNull
    @NotEmpty
    @Valid
    private List<ResidentialHistoryDto> residentialHistories;

    @NotNull
    private List<PersonalReferenceDto> personalReferences;

    @Valid
    @NotNull
    private List<CoApplicantDto> coApplicants;

    @Valid
    @NotNull
    private List<OccupantDto> occupants;

    @Valid
    @NotNull
    private List<PetDto> pets;

    @NotNull
    @Valid
    private IdentificationDto identification;

    private EmergencyContactDto emergencyContact;

    @NotNull
    private List<VehicleDto> vehicles;

    @NotNull
    @Valid
    private EmployerDto employer;

    @Valid
    private List<AdditionalIncomeSourceDto> additionalIncomeSources;
}
