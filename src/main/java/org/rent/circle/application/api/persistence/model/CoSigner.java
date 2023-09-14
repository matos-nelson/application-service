package org.rent.circle.application.api.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "co_signer")
@Setter
@Getter
public class CoSigner extends Applicant {

    @Column(name = "address_id")
    private Long addressId;
}
