package betrip.services.betrip_backend_services.cucumber;

import betrip.services.betrip_backend_services.BetripBackendServicesApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = BetripBackendServicesApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = BetripBackendServicesApplication.class,loader = SpringBootContextLoader.class)
public class CucumberSpringConfiguration {

}
