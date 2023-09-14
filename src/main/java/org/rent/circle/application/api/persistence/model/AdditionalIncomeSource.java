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
@Table(name = "additional_income_source")
@Setter
@Getter
@ToString
public class AdditionalIncomeSource extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "monthly_income")
    private BigDecimal monthlyIncome;

    @Column(name = "applicant_id", insertable = false, updatable = false, nullable = false)
    private Long applicantId;
}
