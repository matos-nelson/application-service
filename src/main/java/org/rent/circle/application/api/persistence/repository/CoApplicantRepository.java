package org.rent.circle.application.api.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import org.rent.circle.application.api.persistence.model.CoApplicant;

@ApplicationScoped
public class CoApplicantRepository implements PanacheRepository<CoApplicant> {

    public CoApplicant findCoApplicant(String managerId, long applicationId, long id) {

        Parameters queryParams = Parameters.with("id", id)
            .and("applicationId", applicationId)
            .and("managerId", managerId);
        return find("from CoApplicant coApp "
            + "left join coApp.primaryApplicant pa "
            + "left join pa.application app "
            + "where app.id = :applicationId and app.managerId = :managerId "
            + "and coApp.id = :id", queryParams)
            .singleResultOptional()
            .orElse(null);
    }
}
