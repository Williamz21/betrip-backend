package betrip.services.betrip_backend_services.BoundenContextTravelEvents.domain.persistence;

import betrip.services.betrip_backend_services.BoundenContextTravelEvents.domain.model.entity.TravelEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TravelEventRepository extends JpaRepository<TravelEvent,Long> {

    List<TravelEvent> findByTravelerId(Long travelerId);
    Page<TravelEvent> findByTravelerId(Long travelerId, Pageable pageable);
    Optional<TravelEvent> findByIdAndTravelerId(Long id, Long travelerId);
}
