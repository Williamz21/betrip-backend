package betrip.services.betrip_backend_services.BoundendContextDriverRoutes.resource;

import lombok.*;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class Allrouts {
    private Long id;
    private  Long driverId;
    private String destiny;
    private  Long seating;
    private String starting_point;
    private String departure_time;
    private String departure_date;
    private  Long cost;
}
