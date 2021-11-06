package betrip.services.betrip_backend_services.BoundendContextTravelers.domain.persistence;

import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.model.entity.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelerRepository extends JpaRepository<Traveler,Long> {
    Traveler findByDni(String dni);
    Traveler findByPassword(String dni);
    Traveler findByEmail(String dni);
    Traveler findByPhoneNumber(String dni);
}
