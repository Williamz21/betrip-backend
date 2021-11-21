package betrip.services.betrip_backend_services.BoundendContextDrivers.resource;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateDriverResource {

    @NotNull
    private String name;

    @NotNull
    private String last_name;

    @NotNull
    private Long age;

    @NotNull
    private String dni;

    @NotNull
    @Size(max = 1000)
    private String Pfp;

    @NotNull
    private String district;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String type;

    @NotNull
    private String model;

    @NotNull
    private String brand;

    @NotNull
    private String licence_plate;

    @NotNull
    private String licence_code;

    @NotNull
    private Long number_seats;

    @NotNull
    private String phoneNumber;

}
