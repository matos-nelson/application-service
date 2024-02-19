package org.rent.circle.application.api.dto;

import jakarta.validation.Valid;
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
public class UpdateCoApplicantDto {

    private String middleName;

    private Suffix suffix;

    @NotNull
    @NotBlank
    private String phone;

    private boolean recentlyEvicted;

    private String evictionExplanation;

    @NotNull
    @NotEmpty
    @Valid
    private List<ResidentialHistoryDto> residentialHistories;

    private List<PersonalReferenceDto> personalReferences;

    @NotNull
    @Valid
    private List<OccupantDto> occupants;

    @NotNull
    @Valid
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
    @NotNull
    private List<AdditionalIncomeSourceDto> additionalIncomeSources;
}
