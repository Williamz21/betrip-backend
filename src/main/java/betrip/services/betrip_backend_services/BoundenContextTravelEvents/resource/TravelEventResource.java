package betrip.services.betrip_backend_services.BoundenContextTravelEvents.resource;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class TravelEventResource {
    private Long id;
    private Long driverId;
    private String destiny;
    private String destinyUrl;
    private Long seating;
    private String starting_point;
    private String departure_time;
    private String departure_date;
    private Long cost;
    private String type;
    private String plate;
    private String travelerProfilePhotofUrl;
}
