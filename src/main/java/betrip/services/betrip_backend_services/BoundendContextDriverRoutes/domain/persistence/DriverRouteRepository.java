package betrip.services.betrip_backend_services.BoundendContextDriverRoutes.domain.persistence;

import betrip.services.betrip_backend_services.BoundenContextTravelEvents.domain.model.entity.TravelEvent;
import betrip.services.betrip_backend_services.BoundendContextDriverRoutes.domain.model.entity.DriverRoute;
import betrip.services.betrip_backend_services.BoundendContextDrivers.domain.model.entity.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRouteRepository extends JpaRepository<DriverRoute, Long> {
    List<DriverRoute> findByDriverId(Long driverId);
    Page<DriverRoute> findByDriverId(Long driverId, Pageable pageable);
    Optional<DriverRoute> findByIdAndDriverId(Long id, Long driverId);
    Optional<DriverRoute>findById(Long id);
}
