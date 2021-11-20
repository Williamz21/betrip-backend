package betrip.services.betrip_backend_services.BoundendContextTravelers.domain.persistence;

import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.model.entity.Role;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.model.enumeration.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles name);
    boolean existsByName (Roles name);
}
