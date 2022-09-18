package betrip.services.betrip_backend_services.BoundendContextTravelers.domain.service.communication;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class RegisterTravelerRequest {
    @NotNull
    @NotBlank
    @Size(max = 100)
    private  String name;

    @NotNull
    private  Long age;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private  String dni;

    @NotNull
    @NotBlank
    @Size(max=100)
    @Column(unique = true)
    private  String email;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private  String password;

    @NotNull
    @Size(max=100)
    @Column(unique = true)
    private  String phoneNumber;

    @NotNull
    @Size(max=1000)
    private  String pfp;

    private Set<String> roles;
}
