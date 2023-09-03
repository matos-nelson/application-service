package org.rent.circle.application.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Month;
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

    private boolean currentResidence;

    @NotNull
    private Month residedFromMonth;

    @NotNull
    private Integer residedFromYear;

    @NotNull
    private Month residedToMonth;

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

    @Email
    private String landlordEmail;

    private String leaveReason;
}
