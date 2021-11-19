package betrip.services.betrip_backend_services.BoundendContextDrivers.mapping;

import betrip.services.betrip_backend_services.BoundendContextTravelers.mapping.TravelerMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class MappingConfiguration {
    @Bean
    public DriverMapper driverMapper(){
        return new DriverMapper();
    }
}
