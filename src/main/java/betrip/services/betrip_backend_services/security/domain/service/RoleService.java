package betrip.services.betrip_backend_services.security.domain.service;

import betrip.services.betrip_backend_services.security.domain.model.entity.Role;

import java.util.List;

public interface RoleService {
    void seed();

    List<Role> getAll();
}
