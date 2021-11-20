package betrip.services.betrip_backend_services.BoundendContextTravelers.domain.service.communication;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class RegisterRequest {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private Long age;
    @NotNull
    @NotBlank
    private String dni;
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    private String password;
    @NotNull
    @NotBlank
    private String phoneNumber;
    @NotNull
    @NotBlank
    private String pfp;


    private Set<String> roles;
}
