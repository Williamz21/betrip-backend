package betrip.services.betrip_backend_services.BoundendContextTravelers.domain.service;

import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.model.entity.Traveler;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.service.communication.RegisterTravelerRequest;
import betrip.services.betrip_backend_services.security.domain.service.communication.AuthenticateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface TravelerService  extends UserDetailsService {
    List<Traveler>getAll();
    Page<Traveler> getAll(Pageable pageable);
    Traveler getById(Long travelerId);
    ResponseEntity<?> authenticate(AuthenticateRequest request);
    ResponseEntity<?> register(RegisterTravelerRequest request);
}
