package org.rent.circle.application.api.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.rent.circle.application.api.persistence.model.Application;

@ApplicationScoped
public class ApplicationRepository implements PanacheRepository<Application> {

}
