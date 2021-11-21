package betrip.services.betrip_backend_services.BoundenContextTravelEvents.resource;

import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.model.entity.Traveler;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class UpdateTravelEventResource {
    private Long Id;
    @NotNull
    private  Long driverId;
    @NotNull
    @NotBlank
    private String destiny;
    @NotNull
    @NotBlank
    private String destinyUrl;
    @NotNull
    private  Long seating;
    @NotNull
    @NotBlank
    private String starting_point;
    @NotNull
    @NotBlank
    private String departure_time;
    @NotNull
    @NotBlank
    private String departure_date;
    @NotNull
    private  Long cost;
    @NotNull
    @NotBlank
    private String type;
    @NotNull
    @NotBlank
    private String plate;
    @NotNull
    @NotBlank
    private String travelerProfilePhotofUrl;

    private Set<Traveler>passengers;

}
