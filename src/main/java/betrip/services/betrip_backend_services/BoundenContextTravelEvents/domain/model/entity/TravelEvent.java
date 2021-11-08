package betrip.services.betrip_backend_services.BoundenContextTravelEvents.domain.model.entity;

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
@Table(name="travelEvents")
public class TravelEvent extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private  Long driverId;



    @NotNull
    @NotBlank
    private String destiny;

    @NotNull
    @NotBlank
    private String destinyUrl;

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

    @NotNull
    @NotBlank
    private String type;

    @NotNull
    @NotBlank
    private String plate;

    @NotNull
    @NotBlank
    private String travelerProfilePhotofUrl;

    @ManyToOne(fetch =FetchType.LAZY,optional = false)
    @JoinColumn(name = "traveler_id",nullable = false)
    @JsonIgnore
    private Traveler traveler;

}
