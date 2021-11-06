package betrip.services.betrip_backend_services.betripapp.api;

import betrip.services.betrip_backend_services.betripapp.domain.travelers.model.entity.Traveler;
import betrip.services.betrip_backend_services.betripapp.domain.travelers.service.TravelerService;
import betrip.services.betrip_backend_services.betripapp.mapping.TravelerMapper;
import betrip.services.betrip_backend_services.betripapp.resource.CreateTravelerResource;
import betrip.services.betrip_backend_services.betripapp.resource.TravelerResource;
import betrip.services.betrip_backend_services.betripapp.resource.UpdateTravelerResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/travelers")
public class TravellerController {
    private final TravelerService travelerService;
    private final TravelerMapper mapper;

    public TravellerController(TravelerService travelerService, TravelerMapper mapper) {
        this.travelerService = travelerService;
        this.mapper = mapper;
    }
    @GetMapping()
    public Page<TravelerResource> getAllTraveler(Pageable pageable){

        return mapper.modelListToPage(travelerService.getAll(),pageable);
    }
    @GetMapping("{travelerId}")
    public TravelerResource getTravelerById(@PathVariable Long travelerId){
        return mapper.toResource(travelerService.getById(travelerId));
    }
    @PostMapping
    public TravelerResource createTraveler(@RequestBody CreateTravelerResource request){
        return mapper.toResource(travelerService.create(mapper.toModel(request)));
    }
    @PutMapping("{travelerId}")
    public TravelerResource updateTraveler(@PathVariable Long travelerId, @RequestBody UpdateTravelerResource request){
        return mapper.toResource(travelerService.update(travelerId,mapper.toModel(request)));
    }
    @DeleteMapping("{travelerId}")
    public ResponseEntity<?> deleteTraveler(@PathVariable Long travelerId){
        return  travelerService.delete(travelerId);
    }
}
