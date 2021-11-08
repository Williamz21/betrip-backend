package betrip.services.betrip_backend_services.BoundendContextTravelers.domain.model.entity;

import betrip.services.betrip_backend_services.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@With
@Table(name="travelers")
public class Traveler extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

}
