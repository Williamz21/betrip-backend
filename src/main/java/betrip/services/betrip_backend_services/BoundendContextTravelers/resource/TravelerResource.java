package betrip.services.betrip_backend_services.BoundendContextTravelers.resource;

import lombok.*;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class TravelerResource {
    private Long id;
    private String name;
    private Long age;
    private String dni;
    private String email;
    private String password;
    private String phoneNumber;
    private String pfp;
}


