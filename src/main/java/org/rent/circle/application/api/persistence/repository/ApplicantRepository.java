package org.rent.circle.application.api.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import org.rent.circle.application.api.persistence.model.Applicant;

@ApplicationScoped
public class ApplicantRepository implements PanacheRepository<Applicant> {

    public long getApplicantApplicationsStatusCount(String email, String status) {
        Parameters queryParams = Parameters.with("email", email)
            .and("status", status);

        return count("from Applicant a "
            + "left join a.application app "
            + "where app.status = :status and email = :email", queryParams);
    }
}
