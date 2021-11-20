package betrip.services.betrip_backend_services.BoundendContextTravelers.domain.model.entity;

import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.model.enumeration.Roles;
import betrip.services.betrip_backend_services.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@With
@Table(name="TravelerRoles")
public class Role extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Roles name;
}
