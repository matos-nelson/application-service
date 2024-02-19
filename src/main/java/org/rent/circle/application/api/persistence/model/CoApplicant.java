package org.rent.circle.application.api.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id", insertable = false, updatable = false, nullable = false)
    private PrimaryApplicant primaryApplicant;
}
