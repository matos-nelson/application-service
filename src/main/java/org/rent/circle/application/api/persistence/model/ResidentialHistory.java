package org.rent.circle.application.api.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(name = "applicant_id")
    private Long applicantId;

    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "resided_from_month")
    private String residedFromMonth;

    @Column(name = "resided_from_year")
    private Integer residedFromYear;

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
}
