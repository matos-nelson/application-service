package org.rent.circle.application.api.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import org.rent.circle.application.api.persistence.model.Application;

@ApplicationScoped

public class ApplicationRepository implements PanacheRepository<Application> {

    public Application findApplication(Long id, Long managerId) {
        Parameters queryParams = Parameters.with("id", id).and("managerId", managerId);
        return find("id = :id and managerId = :managerId", queryParams).firstResult();
    }

    public List<Application> findApplications(Long managerId, int page, int pageSize) {
        return find("managerId", managerId)
            .page(Page.of(page, pageSize))
            .list();
    }
}
