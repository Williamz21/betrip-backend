package betrip.services.betrip_backend_services.BoundendContextDriverRoutes.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("DriverRoutesMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public DriverRouteMapper DriverRouteMapper(){   return new DriverRouteMapper(); }
}
