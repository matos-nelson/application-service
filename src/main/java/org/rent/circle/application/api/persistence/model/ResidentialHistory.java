package org.rent.circle.application.api.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rent.circle.application.api.persistence.BaseModel;

@Entity
@Table(name = "residential_history")
@Setter
@Getter
@ToString
public class ResidentialHistory extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "current_residence")
    private Boolean isCurrentResidence;

    @Column(name = "resided_from_month")
    private String residedFromMonth;

    @Column(name = "resided_from_year")
    private Integer residedFromYear;

    @Column(name = "resided_to_month")
    private String residedToMonth;

    @Column(name = "resided_to_year")
    private Integer residedToYear;

    @Column(name = "monthly_rent")
    private BigDecimal monthlyRent;

    @Column(name = "landlord_name")
    private String landlordName;

    @Column(name = "landlord_phone_number")
    private String landlordPhone;

    @Column(name = "landlord_email")
    private String landlordEmail;

    @Column(name = "leave_reason")
    private String leaveReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;
}
