package org.rent.circle.application.api.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "co_applicant")
@Setter
@Getter
@ToString
public class CoApplicant extends Applicant {

    @Column(name = "applicant_id", insertable = false, updatable = false, nullable = false)
    private Long applicantId;
}
