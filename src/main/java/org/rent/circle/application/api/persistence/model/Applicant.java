package org.rent.circle.application.api.persistence.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "applicant")
@Setter
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
public class Applicant extends ApplicantInfoBase {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "applicant_id", referencedColumnName = "id", nullable = false)
    private List<ResidentialHistory> residentialHistories;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "applicant_id", referencedColumnName = "id", nullable = false)
    private List<PersonalReference> personalReferences;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "applicant_id", referencedColumnName = "id", nullable = false)
    private List<Occupant> occupants;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "applicant_id", referencedColumnName = "id", nullable = false)
    private List<Pet> pets;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "emergency_contact_id")
    private EmergencyContact emergencyContact;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "applicant_id", referencedColumnName = "id", nullable = false)
    private List<Vehicle> vehicles;
}
