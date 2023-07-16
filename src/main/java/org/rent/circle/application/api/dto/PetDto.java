package org.rent.circle.application.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class PetDto {

    private String name;

    @NotNull
    @NotEmpty
    private String breed;

    private Integer weight;

    private Byte age;
}
