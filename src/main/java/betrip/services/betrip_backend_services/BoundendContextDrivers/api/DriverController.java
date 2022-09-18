package betrip.services.betrip_backend_services.BoundendContextDrivers.api;

import betrip.services.betrip_backend_services.BoundendContextDrivers.domain.service.DriverService;
import betrip.services.betrip_backend_services.BoundendContextDrivers.domain.service.communication.RegisterDriverRequest;
import betrip.services.betrip_backend_services.BoundendContextDrivers.mapping.DriverMapper;
import betrip.services.betrip_backend_services.BoundendContextDrivers.resource.DriverResource;
import betrip.services.betrip_backend_services.BoundendContextDrivers.resource.UpdateDriverResource;
import betrip.services.betrip_backend_services.security.domain.service.communication.AuthenticateRequest;
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


@Tag(name="Drivers")
@RestController
@RequestMapping("/api/v1/drivers")
@CrossOrigin(origins = "*", maxAge = 3600)

public class DriverController {
    private final DriverService driverService;
    private final DriverMapper mapper;

    public DriverController(DriverService driverService, DriverMapper mapper) {
        this.driverService = driverService;
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
                                            implementation = DriverResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping()
    public Page<DriverResource> getAllDriver(Pageable pageable){

        return mapper.modelListToPage(driverService.getAll(),pageable);
    }
    @GetMapping("{driverId}")
    public DriverResource getDriverById(@PathVariable Long driverId){
        return mapper.toResource(driverService.getById(driverId));
    }
    @PostMapping("/auth/sign-in")
    public ResponseEntity<?> logDriver(@RequestBody AuthenticateRequest request){
        return driverService.authenticate(request);
    }
    @PostMapping("/auth/sign-up")
    public ResponseEntity<?> regDriver(@RequestBody RegisterDriverRequest request){
        return driverService.register(request);
    }

}
