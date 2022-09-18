package betrip.services.betrip_backend_services.BoundendContextDrivers.domain.persistence;

import betrip.services.betrip_backend_services.BoundendContextDrivers.domain.model.entity.Driver;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.model.entity.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Driver findByDni(String dni);
    Driver findByPassword(String dni);
    Optional<Driver> findByEmail(String email);
    Driver findByPhoneNumber(String dni);
    Boolean existsByEmail(String email);
}
