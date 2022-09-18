package betrip.services.betrip_backend_services.BoundendContextTravelers.resource;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class AuthenticateTravelerResource {
    private Long id;

    private  String name;

    private  Long age;

    private  String dni;

    private  String email;

    private  String phoneNumber;

    private  String pfp;

    private List<String> roles;

    private String token;
}
