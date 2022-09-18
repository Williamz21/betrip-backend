package betrip.services.betrip_backend_services.security.mapping;

import betrip.services.betrip_backend_services.BoundendContextDrivers.mapping.DriverMapper;
import betrip.services.betrip_backend_services.BoundendContextTravelers.mapping.TravelerMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("securityMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public RoleMapper roleMapper() {
        return new RoleMapper();
    }
}
