package betrip.services.betrip_backend_services.BoundendContextTravelers.service;

import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.model.entity.Role;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.model.enumeration.Roles;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.persistence.RoleRepository;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    private static String[] DEFAULT_ROLES = {"ROLE_USER"};

    @Override
    public void seed() {
        Arrays.stream(DEFAULT_ROLES).forEach(
                name->{
                    Roles roleName = Roles.valueOf(name);
                    if(!roleRepository.existsByName(roleName)){
                        roleRepository.save((new Role()).withName(roleName));
                    }
                }
        );
    }
}
