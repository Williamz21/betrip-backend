package betrip.services.betrip_backend_services.BoundendContextTravelers.api;

import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.service.TravelerService;
import betrip.services.betrip_backend_services.BoundendContextTravelers.mapping.TravelerMapper;
import betrip.services.betrip_backend_services.BoundendContextTravelers.resource.CreateTravelerResource;
import betrip.services.betrip_backend_services.BoundendContextTravelers.resource.TravelerResource;
import betrip.services.betrip_backend_services.BoundendContextTravelers.resource.UpdateTravelerResource;
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

import javax.validation.Valid;


@Tag(name="Travelers")
@RestController
@RequestMapping("/api/v1/travelers")
public class TravellerController {
   private final TravelerService travelerService;
    private final TravelerMapper mapper;

    public TravellerController(TravelerService travelerService, TravelerMapper mapper) {
        this.travelerService = travelerService;
        this.mapper = mapper;
    }
    @Operation(summary="Get posts",description = "Get all Data From Databse PostgressSql")
    @ApiResponses(value={
            @ApiResponse(
                    responseCode = "200",
                    description = "Post Found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = TravelerResource.class
                                    )
                            )
                    }
            )
    })
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
