package betrip.services.betrip_backend_services.BoundendContextTravelers.mapping;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("betripMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public TravelerMapper travelerMapper(){
        return new TravelerMapper();
    }
}
