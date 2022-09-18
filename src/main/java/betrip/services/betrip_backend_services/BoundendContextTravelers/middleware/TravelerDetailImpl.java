package betrip.services.betrip_backend_services.BoundendContextTravelers.middleware;

import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.model.entity.Traveler;
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
public class TravelerDetailImpl implements UserDetails {
    private Long id;

    private  String name;

    private  Long age;

    private  String dni;

    private  String email;
    @JsonIgnore
    private  String password;

    private  String phoneNumber;

    private  String pfp;

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
        return true;
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
        TravelerDetailImpl user = (TravelerDetailImpl) other;
        return Objects.equals(id, user.id);
    }

    public static TravelerDetailImpl build(Traveler user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(
                        role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return new TravelerDetailImpl(user.getId(), user.getName(), user.getAge(), user.getDni(),
                user.getEmail(), user.getPassword(), user.getPhoneNumber(), user.getPfp(), authorities);
    }
}
