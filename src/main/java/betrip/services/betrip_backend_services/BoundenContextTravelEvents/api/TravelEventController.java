package betrip.services.betrip_backend_services.BoundenContextTravelEvents.api;

import betrip.services.betrip_backend_services.BoundenContextTravelEvents.domain.service.TravelEventService;
import betrip.services.betrip_backend_services.BoundenContextTravelEvents.mapping.TravelEventMapper;
import betrip.services.betrip_backend_services.BoundenContextTravelEvents.resource.CreateTravelEventResource;
import betrip.services.betrip_backend_services.BoundenContextTravelEvents.resource.TravelEventResource;
import betrip.services.betrip_backend_services.BoundenContextTravelEvents.resource.TravelEvents;
import betrip.services.betrip_backend_services.BoundenContextTravelEvents.resource.UpdateTravelEventResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Travel-Events")
@RestController
@RequestMapping("/api/v1/")
public class TravelEventController {
    private final TravelEventService travelEventService;
    private final TravelEventMapper mapper;

    public TravelEventController(TravelEventService travelEventService, TravelEventMapper mapper) {
        this.travelEventService = travelEventService;
        this.mapper = mapper;
    }
    @Operation(summary="Get all travel-events",description = "Get all Data From Database PostgresSql")
    @ApiResponses(value={
            @ApiResponse(
                    responseCode = "200",
                    description = "Post Found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = TravelEventResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("travel-events")
    public Page<TravelEvents> getAllPost(Pageable pageable){

        return mapper.modelToListToPage2(travelEventService.getAll(),pageable);
    }
    @GetMapping("travel-events/{travelEventId}")
    public TravelEventResource getPostById(@PathVariable Long travelEventId){
        return mapper.toResource(travelEventService.getById(travelEventId));
    }

    @GetMapping("travelers/{travelerId}/travel-events")
    public Page<TravelEvents> getAllTravelEventsByPostId(@PathVariable Long travelerId, Pageable pageable){
        return mapper.modelToListToPage2(travelEventService.getAllByTravelerId(travelerId),pageable);
    }
    @PostMapping("travelers/{travelerId}/travel-events")
    public TravelEventResource createTravelEvent(@PathVariable Long travelerId, @RequestBody CreateTravelEventResource request){
        return mapper.toResource(travelEventService.create(travelerId,mapper.toModel(request)));
    }
    @PutMapping("travelers/{travelerId}/travel-events/{travelEventId}")
    public TravelEventResource updateComment(@PathVariable Long travelerId,@PathVariable Long travelEventId, @RequestBody UpdateTravelEventResource request){
        return mapper.toResource(travelEventService.update(travelerId,travelEventId,mapper.toModel(request)));
    }
    @DeleteMapping("travelers/{travelerId}/travel-events/{travelEventId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long travelerId, @PathVariable Long travelEventId){
        return travelEventService.delete(travelerId,travelEventId);
    }
}