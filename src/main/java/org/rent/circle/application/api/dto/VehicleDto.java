package org.rent.circle.application.api.dto;

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
public class VehicleDto {

    private String make;

    private String model;

    private Integer year;

    private String color;

    private String licenceNumber;
}
