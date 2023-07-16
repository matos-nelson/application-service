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
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rent.circle.application.api.persistence.BaseModel;

@Entity
@Table(name = "employer")
@Setter
@Getter
@ToString
public class Employer extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "monthly_salary")
    private BigDecimal monthlySalary;

    @Column(name = "position_held")
    private String positionHeld;

    @Column(name = "years_worked")
    private Byte yearsWorked;

    @Column(name = "supervisor_name")
    private String supervisorName;

    @Column(name = "supervisor_email")
    private String supervisorEmail;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "applicant_id", referencedColumnName = "id")
    private Applicant applicant;
}
