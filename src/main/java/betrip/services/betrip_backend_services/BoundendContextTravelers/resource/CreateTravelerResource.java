package betrip.services.betrip_backend_services.BoundendContextTravelers.resource;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateTravelerResource {

    @NotNull
    private String name;
    @NotNull
    private Long age;
    @NotNull
    private String dni;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String phoneNumber;
    @NotNull
    @Size(max = 1000)
    private String pfp;
}
