package betrip.services.betrip_backend_services.BoundendContextDrivers.domain.service.communication;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class RegisterDriverRequest {
    @NotNull
    @NotBlank
    @Size(max = 100)
    private  String name;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String last_name;

    @NotNull
    private  Long age;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private  String dni;

    @NotNull
    @NotBlank
    private String Pfp;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String district;


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
    @NotBlank
    @Size(max = 100)
    private String type;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String model;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String brand;

    @NotNull
    @NotBlank
    @Size(max = 100)
    @Column(unique = true)
    private String licence_plate;

    @NotNull
    @NotBlank
    @Size(max = 100)
    @Column(unique = true)
    private String licence_code;

    @NotNull
    private Long number_seats;

    @NotNull
    @Size(max=100)
    @Column(unique = true)
    private  String phoneNumber;

    private Set<String> roles;
}
