package betrip.services.betrip_backend_services.step;

import betrip.services.betrip_backend_services.BoundenContextTravelEvents.resource.CreateTravelEventResource;
import betrip.services.betrip_backend_services.BoundenContextTravelEvents.resource.TravelEventResource;
import betrip.services.betrip_backend_services.BoundenContextTravelEvents.resource.UpdateTravelEventResource;
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

public class TravelEventUpdatingStepDefinitions {
  @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int randomServerPort;
    private String endPointPath;
    private ResponseEntity<String> responseEntity;


  @Given("The EndPoint of Travel Event  {string} is available")
  public void theEndPointOfTravelEventIsAvailable(String arg0) {
    this.endPointPath = String.format(endPointPath, randomServerPort);
  }



  @Then("A response with status {int} is received..")
  public void aResponseWithStatusIsReceived(int expectedStatusCode) {
    int actualStatusCode=responseEntity.getStatusCodeValue();
    assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
  }


  @When("A Travel Event Update Request is sent with values {long},{long},{string},{string},{long},{string},{string},{string},{long},{string},{string},{string}")
  public void aTravelEventUpdateRequestIsSentWithValues(Long driverId, String destiny, String destinyUrl,
                                                        Long seating, String starting_point, String departure_time,
                                                        String departure_date, Long cost, String type, String plate,
                                                        String travelerProfilePhotofUrl) {
    UpdateTravelEventResource resource=new UpdateTravelEventResource();

    resource.setDriverId(driverId);
    resource.setDestiny(destiny);
    resource.setCost(cost);
    resource.setDeparture_date(departure_date);
    resource.setDeparture_time(departure_time);
    resource.setPlate(plate);
    resource.setDestinyUrl(destinyUrl);
    resource.setSeating(seating);
    resource.setTravelerProfilePhotofUrl(travelerProfilePhotofUrl);
    resource.setStarting_point(starting_point);
    resource.setType(type);

    HttpHeaders headers=new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<UpdateTravelEventResource> request=new HttpEntity<>(resource,headers);
  }

  @And("A Travel Event Resource with values {long},{string},{string},{long},{string},{string},{string},{long},{string},{string},{string} is included in Response Body")
  public void aTravelEventResourceWithValuesIsIncludedInResponseBody(Long id,Long driverId, String destiny, String destinyUrl,
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
            .withTravelerProfilePhotofUrl(travelerProfilePhotofUrl)
            .withId(id);
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
}
