package betrip.services.betrip_backend_services.BoundendContextDriverRoutes.domain.service;

import betrip.services.betrip_backend_services.BoundendContextDriverRoutes.domain.model.entity.DriverRoute;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DriverRouteService {
    List<DriverRoute> getAll();
    List<DriverRoute> getAllByDriverId(Long driverId);
    Page<DriverRoute> getAllByDriverId (Long driverId, Pageable pageable);
    DriverRoute create(Long driverId, DriverRoute request);
    DriverRoute update(Long driverId, Long driverRouteId, DriverRoute request);
    ResponseEntity<?> delete(Long driverId, Long driverEventId);
}
