package betrip.services.betrip_backend_services.BoundenContextTravelEvents.resource;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateTravelEventResource {

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
}
