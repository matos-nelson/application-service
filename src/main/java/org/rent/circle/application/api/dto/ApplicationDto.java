package org.rent.circle.application.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rent.circle.application.api.enums.Status;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDto {

    private Long id;
    private String managerId;
    private Long propertyId;
    private Status status;
    private String note;
    private PrimaryApplicantDto primaryApplicant;
    private CoSignerDto coSigner;
}
