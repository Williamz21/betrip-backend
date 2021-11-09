package betrip.services.betrip_backend_services.step;

import betrip.services.betrip_backend_services.BoundenContextTravelEvents.resource.CreateTravelEventResource;
import betrip.services.betrip_backend_services.BoundenContextTravelEvents.resource.TravelEventResource;
import betrip.services.betrip_backend_services.BoundendContextTravelers.resource.CreateTravelerResource;
import betrip.services.betrip_backend_services.BoundendContextTravelers.resource.TravelerResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TravelEventAddingStepDefinitions {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int randomServerPort;
    private String endPointPath;
    private ResponseEntity<String> responseEntity;


    @Given("The EndPoint for travel events{string} is available")
    public void theEndPointForTravelEventsIsAvailable(String endPointPath) {
        this.endPointPath = String.format(endPointPath, randomServerPort);
    }




    @Then("A response with status {int} is received.")
    public void aResponseWithStatusIsReceived(int expectedStatusCode) {
        int actualStatusCode=responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
    }


    @When("A Travel-event Request is sent with values {long},{string},{string},{long},{string},{string},{string},{long},{string},{string},{string}")
    public void aTravelEventRequestIsSentWithValues(Long driverId, String destiny, String destinyUrl, Long seating, String starting_point, String departure_time, String departure_date, Long cost, String type, String plate, String travelerProfilePhotofUrl) {
        TravelEventResource resource=new TravelEventResource()
                .withDriverId(driverId)
                .withDestiny(destiny)
                .withDestinyUrl(destinyUrl)
                .withSeating(seating)
                .withStarting_point(starting_point)
                .withDeparture_time(departure_time)
                .withDeparture_date(departure_date)
                .withCost(cost)
                .withType(type)
                .withPlate(plate)
                .withTravelerProfilePhotofUrl(travelerProfilePhotofUrl);
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TravelEventResource> request=new HttpEntity<>(resource,headers);
        responseEntity=testRestTemplate.postForEntity(endPointPath,request,String.class);
    }

    @And("A Travel-event Resource with values {long},{string},{string},{long},{string},{string},{string},{long},{string},{string},{string} is included in Response Body")
    public void aTravelEventResourceWithValuesIsIncludedInResponseBody(Long driverId, String destiny, String destinyUrl,
                                                                       Long seating, String starting_point, String departure_time,
                                                                       String departure_date, Long cost, String type, String plate,
                                                                       String travelerProfilePhotofUrl) {
        TravelEventResource expectedResource=new TravelEventResource()
                .withDriverId(driverId)
                .withDestiny(destiny)
                .withDestinyUrl(destinyUrl)
                .withSeating(seating)
                .withStarting_point(starting_point)
                .withDeparture_time(departure_time)
                .withDeparture_date(departure_date)
                .withCost(cost)
                .withType(type)
                .withPlate(plate)
                .withTravelerProfilePhotofUrl(travelerProfilePhotofUrl);

        String value=responseEntity.getBody();

        ObjectMapper mapper=new ObjectMapper();
        TravelEventResource actualResource;
        try{
            actualResource=mapper.readValue(value,TravelEventResource.class);
        }catch(JsonProcessingException | NullPointerException e){
            actualResource=new TravelEventResource();

        }
        expectedResource.setId(actualResource.getId());
        assertThat(expectedResource).usingRecursiveComparison().isEqualTo(actualResource);

    }

    @Given("A Travel-event Resource with values {long},{string},{string},{long},{string},{string},{string},{long},{string},{string},{string} is already stored")
    public void aTravelEventResourceWithValuesIsAlreadyStored(Long driverId, String destiny, String destinyUrl,
                                                              Long seating, String starting_point, String departure_time,
                                                              String departure_date, Long cost, String type, String plate,
                                                              String travelerProfilePhotofUrl) {
        CreateTravelEventResource resource=new CreateTravelEventResource()
                .withDriverId(driverId)
                .withDestiny(destiny)
                .withDestinyUrl(destinyUrl)
                .withSeating(seating)
                .withStarting_point(starting_point)
                .withDeparture_time(departure_time)
                .withDeparture_date(departure_date)
                .withCost(cost)
                .withType(type)
                .withPlate(plate)
                .withTravelerProfilePhotofUrl(travelerProfilePhotofUrl);
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateTravelEventResource> request =new HttpEntity<>(resource,headers);

        responseEntity=testRestTemplate.postForEntity(endPointPath,request,String.class);

    }
}
