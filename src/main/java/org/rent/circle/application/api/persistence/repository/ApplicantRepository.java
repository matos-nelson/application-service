package org.rent.circle.application.api.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import org.rent.circle.application.api.persistence.model.Applicant;

@ApplicationScoped
public class ApplicantRepository implements PanacheRepository<Applicant> {

    public long getApplicantApplicationsStatusCount(long managerId, String email, String status) {
        Parameters queryParams = Parameters.with("email", email)
            .and("managerId", managerId)
            .and("status", status);

        return count("from Application app "
            + "left join app.primaryApplicant pa "
            + "where app.managerId = :managerId and app.status = :status and pa.email = :email", queryParams);
    }
}
