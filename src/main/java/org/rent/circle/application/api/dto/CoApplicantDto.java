package org.rent.circle.application.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class CoApplicantDto {

    @NotNull
    @NotBlank
    private String firstName;

    private String middleName;

    @NotNull
    @NotBlank
    private String lastName;

    private Suffix suffix;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    private boolean recentlyEvicted;
    private String evictionExplanation;
    private List<ResidentialHistoryDto> residentialHistories;
    private List<PersonalReferenceDto> personalReferences;
    private List<OccupantDto> occupants;
    private List<PetDto> pets;
    private IdentificationDto identification;
    private EmergencyContactDto emergencyContact;
    private List<VehicleDto> vehicles;
    private EmployerDto employer;
    private List<AdditionalIncomeSourceDto> additionalIncomeSources;
}
