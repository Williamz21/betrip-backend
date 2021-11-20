package betrip.services.betrip_backend_services.BoundendContextDrivers.domain.persistence;

import betrip.services.betrip_backend_services.BoundendContextDrivers.domain.model.entity.Driver;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.model.entity.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Driver findByDni(String dni);
    Driver findByPassword(String dni);
    Driver findByEmail(String dni);
    Driver findByPhoneNumber(String dni);
}
