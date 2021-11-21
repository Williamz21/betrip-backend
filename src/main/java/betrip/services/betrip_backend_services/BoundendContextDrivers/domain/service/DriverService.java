package betrip.services.betrip_backend_services.BoundendContextDrivers.domain.service;

import betrip.services.betrip_backend_services.BoundendContextDrivers.domain.model.entity.Driver;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.model.entity.Traveler;
import betrip.services.betrip_backend_services.BoundendContextTravelers.resource.AuthenticateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DriverService {
    List<Driver> getAll();
    Page<Driver> getAll(Pageable pageable);
    Driver getById(Long driverId);
    Driver create(Driver driver);
    Driver update(Long driverId, Driver request);
    ResponseEntity<?> delete(Long driverId);
    Driver authenticate (AuthenticateRequest request);
}
