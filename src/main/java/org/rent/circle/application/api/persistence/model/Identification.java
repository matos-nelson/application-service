package org.rent.circle.application.api.persistence.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rent.circle.application.api.persistence.BaseModel;

@Entity
@Table(name = "identification")
@Setter
@Getter
@ToString
public class Identification extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "ssn")
    private String ssn;

    @Column(name = "government_issued_id")
    private String governmentIssuedId;

    @Column(name = "issued_state_territory")
    private String issuedLocation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "applicant_id", referencedColumnName = "id")
    private Applicant applicant;
}
