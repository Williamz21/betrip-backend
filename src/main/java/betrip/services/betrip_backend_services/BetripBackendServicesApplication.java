package betrip.services.betrip_backend_services;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition
@SpringBootApplication
@EnableJpaAuditing
public class BetripBackendServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BetripBackendServicesApplication.class, args);
    }

}
