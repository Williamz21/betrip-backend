package betrip.services.betrip_backend_services.BoundenContextTravelEvents.mapping;

import betrip.services.betrip_backend_services.BoundendContextTravelers.mapping.TravelerMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("TravelEventsMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public TravelEventMapper TravelEventMapper(){
        return new TravelEventMapper();
    }
}
