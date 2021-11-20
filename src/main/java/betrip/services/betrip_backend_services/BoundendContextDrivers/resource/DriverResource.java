package betrip.services.betrip_backend_services.BoundendContextDrivers.resource;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class DriverResource {
    private Long id;
    private String name;
    private String last_name;
    private Long age;
    private String dni;
    private String Pfp;
    private String district;
    private String email;
    private String password;
    private String type;
    private String model;
    private String brand;
    private String licence_plate;
    private String license_code;
    private Long number_seats;
    private String phoneNumber;
}
