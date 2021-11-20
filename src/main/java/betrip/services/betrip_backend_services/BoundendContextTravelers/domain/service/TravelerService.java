package betrip.services.betrip_backend_services.BoundendContextTravelers.domain.service;

import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.model.entity.Traveler;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.service.communication.AuthenticateRequest;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.service.communication.RegisterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface TravelerService extends UserDetailsService {
    List<Traveler>getAll();
    //Page<Traveler> getAll(Pageable pageable);
    //Traveler getById(Long travelerId);
    //Traveler create(Traveler traveler);
    //Traveler update(Long travelerId,Traveler request);
    //ResponseEntity<?> delete(Long travelerId);
    ResponseEntity<?> authenticate (AuthenticateRequest request);
    ResponseEntity<?> register (RegisterRequest request);

}
