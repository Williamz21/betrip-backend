package betrip.services.betrip_backend_services.BoundendContextDrivers.domain.service;

import betrip.services.betrip_backend_services.BoundendContextDrivers.domain.model.entity.Driver;
import betrip.services.betrip_backend_services.BoundendContextDrivers.domain.service.communication.RegisterDriverRequest;
import betrip.services.betrip_backend_services.security.domain.service.communication.AuthenticateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface DriverService extends UserDetailsService {
    List<Driver> getAll();
    Page<Driver> getAll(Pageable pageable);
    Driver getById(Long driverId);
    ResponseEntity<?> authenticate(AuthenticateRequest request);

    ResponseEntity<?> register(RegisterDriverRequest request);

}
