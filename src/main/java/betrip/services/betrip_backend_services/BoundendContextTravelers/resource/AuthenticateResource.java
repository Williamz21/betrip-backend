package betrip.services.betrip_backend_services.BoundendContextTravelers.resource;

import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class AuthenticateResource {
    private Long id;
    private String username;
    private String name;
    private Long age;
    private String dni;
    private String email;
    private String phoneNumber;
    private List<String> roles;
    private String pfp;
    private String token;
}
