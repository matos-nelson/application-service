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
public class EmployerDto {

    @NotNull
    private Long addressId;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String phoneNumber;

    @NotNull
    private BigDecimal monthlySalary;

    @NotNull
    @NotBlank
    private String positionHeld;

    @NotNull
    private Byte yearsWorked;

    @NotNull
    @NotBlank
    private String supervisorName;

    @NotNull
    @NotBlank
    private String supervisorEmail;
}
