package org.rent.circle.application.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
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
public class ResidentialHistoryDto {

    @NotNull
    private Long addressId;

    private Boolean isCurrentResidence;

    @NotNull
    @NotBlank
    private String residedFromMonth;

    @NotNull
    private Integer residedFromYear;

    @NotNull
    @NotBlank
    private String residedToMonth;

    @NotNull
    private Integer residedToYear;

    @NotNull
    private BigDecimal monthlyRent;

    @NotNull
    @NotBlank
    private String landlordName;

    @NotNull
    @NotBlank
    private String landlordPhone;

    @NotNull
    @NotBlank
    private String landlordEmail;

    private String leaveReason;
}
