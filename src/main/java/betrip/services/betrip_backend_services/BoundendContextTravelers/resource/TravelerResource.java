package betrip.services.betrip_backend_services.BoundendContextTravelers.resource;

import betrip.services.betrip_backend_services.security.resource.RoleResource;
import lombok.*;

import java.util.List;

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
    private String phoneNumber;
    private String pfp;
    private List<RoleResource> roles;
}


