package org.rent.circle.application.api.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rent.circle.application.api.persistence.BaseModel;

@Entity
@Table(name = "emergency_contact")
@Setter
@Getter
@ToString
public class EmergencyContact extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "applicant_id")
    private Long applicantId;

    @Column(name = "name")
    private String name;

    @Column(name = "relationship")
    private String relationship;


    @Column(name = "phone_number")
    private String phone;

    @Column(name = "email")
    private String email;
}
