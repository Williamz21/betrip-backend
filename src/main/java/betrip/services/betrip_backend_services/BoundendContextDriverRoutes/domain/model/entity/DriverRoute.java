package betrip.services.betrip_backend_services.BoundendContextDriverRoutes.domain.model.entity;

import betrip.services.betrip_backend_services.BoundendContextDrivers.domain.model.entity.Driver;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.model.entity.Traveler;
import betrip.services.betrip_backend_services.shared.domain.model.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@With
@Table(name="driverRoute")
public class DriverRoute extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private  Long driverId;


    @NotNull
    @NotBlank
    private String destiny;


    @NotNull
    private  Long seating;

    @NotNull
    @NotBlank
    private String starting_point;

    @NotNull
    @NotBlank
    private String departure_time;

    @NotNull
    @NotBlank
    private String departure_date;

    @NotNull
    private  Long cost;


    @ManyToOne(fetch =FetchType.LAZY,optional = false)
    @JoinColumn(name = "driver_id",nullable = false)
    @JsonIgnore
    private Driver driver;


}
