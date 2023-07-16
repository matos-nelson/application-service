package org.rent.circle.application.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
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
public class ApplicantDto {

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    private String middleName;

    private String suffix;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String phoneNumber;

    private Boolean isRecentlyEvicted;

    private String evictionExplanation;

    @NotNull
    @NotEmpty
    List<ResidentialHistoryDto> residentialHistories;

    List<PersonalReferenceDto> personalReferences;

    List<CoApplicantDto> coApplicants;

    List<OccupantDto> occupants;

    List<PetDto> pets;

    @NotNull
    IdentificationDto identificationDto;

    EmergencyContactDto emergencyContact;

    List<VehicleDto> vehicles;

    @NotNull
    EmployerDto employerDto;

    List<AdditionalIncomeSourceDto> additionalIncomeSources;
}
