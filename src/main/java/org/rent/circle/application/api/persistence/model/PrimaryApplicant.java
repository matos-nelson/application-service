package org.rent.circle.application.api.persistence.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "primary_applicant")
@Setter
@Getter
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class PrimaryApplicant extends Applicant {

    @Column(name = "recent_eviction")
    private boolean recentlyEvicted;

    @Column(name = "eviction_explanation")
    private String evictionExplanation;

    @OneToOne(mappedBy = "primaryApplicant")
    private Application application;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "applicant_id", referencedColumnName = "id", nullable = false)
    private List<CoApplicant> coApplicants;
}
