package org.rent.circle.application.api.dto;

import jakarta.validation.Valid;
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
public class CoSignerDto {

    @NotNull
    private Long addressId;

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

    @NotNull
    @Valid
    IdentificationDto identification;

    @NotNull
    @Valid
    EmployerDto employer;

    @Valid
    List<AdditionalIncomeSourceDto> additionalIncomeSources;
}
