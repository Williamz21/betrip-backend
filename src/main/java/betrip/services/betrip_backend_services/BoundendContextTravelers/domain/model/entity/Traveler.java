package betrip.services.betrip_backend_services.BoundendContextTravelers.domain.model.entity;

import betrip.services.betrip_backend_services.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

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

    @Size(max = 50)
    @NotBlank
    @Column(unique = true)
    private String username;


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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns= @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public Traveler( String Username,String email, String password) {
        this.username=Username;
        this.email = email;
        this.password = password;
    }

}
