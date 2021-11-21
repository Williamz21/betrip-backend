package betrip.services.betrip_backend_services.BoundenContextTravelEvents.resource;

import lombok.*;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class TravelEvents {
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
