package betrip.services.betrip_backend_services.BoundenContextTravelEvents.domain.service;

import betrip.services.betrip_backend_services.BoundenContextTravelEvents.domain.model.entity.TravelEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TravelEventService {
    List<TravelEvent>getAll();
    List<TravelEvent> getAllByTravelerId(Long travelerId);
    Page<TravelEvent> getAllByTravelerId(Long travelerId, Pageable pageable);
    TravelEvent create(Long travelerId, TravelEvent request);
    TravelEvent update(Long travelerId, Long travelEventId, TravelEvent request);
    ResponseEntity<?> delete(Long travelerId, Long travelEventId);
}
