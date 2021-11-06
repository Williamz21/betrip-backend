package betrip.services.betrip_backend_services.betripapp.resource;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateTravelerResource {

    private Long Id;
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
