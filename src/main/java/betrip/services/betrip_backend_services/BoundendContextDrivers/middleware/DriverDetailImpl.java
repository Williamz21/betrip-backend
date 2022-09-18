package betrip.services.betrip_backend_services.BoundendContextDrivers.middleware;

import betrip.services.betrip_backend_services.BoundendContextDrivers.domain.model.entity.Driver;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class DriverDetailImpl implements UserDetails {

    private Long id;

    private String name;

    private String last_name;

    private Long age;

    private String dni;

    private String Pfp;

    private String district;

    private String email;
    @JsonIgnore
    private String password;

    private String type;

    private String model;

    private String brand;

    private String licence_plate;

    private String licence_code;

    private Long number_seats;

    private String phoneNumber;

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true ;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if(this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        DriverDetailImpl user = (DriverDetailImpl) other;
        return Objects.equals(id, user.id);
    }

    public static DriverDetailImpl build(Driver user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(
                        role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return new DriverDetailImpl(user.getId(), user.getName(), user.getLast_name(), user.getAge(), user.getDni(),
                user.getPfp(),user.getDistrict(), user.getEmail(), user.getPassword(), user.getType(), user.getModel(),
                user.getBrand(), user.getLicence_plate(), user.getLicence_code(), user.getNumber_seats(),
                user.getPhoneNumber(), authorities);
    }
}
