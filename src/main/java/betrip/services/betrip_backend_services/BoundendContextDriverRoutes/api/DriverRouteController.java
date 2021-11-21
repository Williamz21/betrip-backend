package betrip.services.betrip_backend_services.BoundendContextDriverRoutes.api;

import betrip.services.betrip_backend_services.BoundendContextDriverRoutes.domain.service.DriverRouteService;
import betrip.services.betrip_backend_services.BoundendContextDriverRoutes.mapping.DriverRouteMapper;
import betrip.services.betrip_backend_services.BoundendContextDriverRoutes.resource.CreateDriverRouteResource;
import betrip.services.betrip_backend_services.BoundendContextDriverRoutes.resource.DriverRouteResource;
import betrip.services.betrip_backend_services.BoundendContextDriverRoutes.resource.UpdateDriverRouteResource;
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

@Tag(name="Driver-Routes")
@RestController
@RequestMapping("/api/v1")
public class DriverRouteController {
    private final DriverRouteService driverRouteService;
    private final DriverRouteMapper mapper;

    public DriverRouteController(DriverRouteService driverRouteService, DriverRouteMapper mapper) {
        this.driverRouteService = driverRouteService;
        this.mapper = mapper;
    }

    @Operation(summary="Get all driver-routes",description = "Get all Data From Database PostgresSql")
    @ApiResponses(value={
            @ApiResponse(
                    responseCode = "200",
                    description = "Post Found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = DriverRouteResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("/driver-routes")
    public Page<DriverRouteResource> getAllPost(Pageable pageable){

        return mapper.modelToListToPage(driverRouteService.getAll(),pageable);
    }
    @GetMapping("drivers/{driverId}/driver-routes")
    public Page<DriverRouteResource> getAllDriverRoutesByPostId(@PathVariable Long driverId, Pageable pageable){
        return mapper.modelToListToPage(driverRouteService.getAllByDriverId(driverId),pageable);
    }
    @PostMapping("drivers/{driverId}/driver-routes")
    public DriverRouteResource createDriverRoute(@PathVariable Long driverId, @RequestBody CreateDriverRouteResource request){
        return mapper.toResource(driverRouteService.create(driverId,mapper.toModel(request)));
    }
    @PutMapping("drivers/{driverId}/driver-routes/{driverRouteId}")
    public DriverRouteResource updateComment(@PathVariable Long driverId,@PathVariable Long driverRouteId, @RequestBody UpdateDriverRouteResource request){
        return mapper.toResource(driverRouteService.update(driverId,driverRouteId,mapper.toModel(request)));
    }
    @DeleteMapping("drivers/{driverId}/driver-routes/{driverRouteId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long driverId, @PathVariable Long driverRouteId){
        return driverRouteService.delete(driverId,driverRouteId);
    }
}
