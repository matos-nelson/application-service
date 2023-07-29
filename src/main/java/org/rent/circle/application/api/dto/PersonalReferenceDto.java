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
public class PersonalReferenceDto {

    private String name;
    private String relationship;
    private String email;
    private String phone;
}
