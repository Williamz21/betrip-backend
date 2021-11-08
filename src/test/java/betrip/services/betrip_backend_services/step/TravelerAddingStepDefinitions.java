package betrip.services.betrip_backend_services.step;

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

public class TravelerAddingStepDefinitions {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int randomServerPort;
    private String endPointPath;
    private ResponseEntity<String> responseEntity;

    @Given("The EndPoint {string} is available")
    public void theEndPointIsAvailable(String endPointPath){
        this.endPointPath=String.format(endPointPath,randomServerPort);

    }
    @When("A Traveler Request is sent with values {string},{long},{string},{string},{string},{string},{string}")
    public void aTravelerRequestIsSentWithValues(String name,Long age,String dni,String email,String password,String phoneNumber,String pfp){
        CreateTravelerResource resource=new CreateTravelerResource()
                .withName(name)
                .withAge(age)
                .withDni(dni)
                .withEmail(email)
                .withPassword(password)
                .withPhoneNumber(phoneNumber)
                .withPfp(pfp);
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateTravelerResource> request=new HttpEntity<>(resource,headers);
        responseEntity=testRestTemplate.postForEntity(endPointPath,request,String.class);
    }

    @Then("A response with status {int} is received")
    public void aResponseWithStatusIsReceived(int expectedStatusCode) {
        int actualStatusCode=responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
    }

    @And("A Traveler Resource with values {string},{long},{string},{string},{string},{string},{string} is included in Response Body")
    public void aTravelerResourceWithValuesIsIncludedInResponseBody(String name,Long age,String dni,String email,String password,String phoneNumber,String pfp ) {
        TravelerResource expectedResource=new TravelerResource()
                .withName(name)
                .withAge(age)
                .withDni(dni)
                .withEmail(email)
                .withPassword(password)
                .withPhoneNumber(phoneNumber)
                .withPfp(pfp);
        String value=responseEntity.getBody();

        ObjectMapper mapper=new ObjectMapper();
        TravelerResource actualResource;
        try{
            actualResource=mapper.readValue(value,TravelerResource.class);
        }catch(JsonProcessingException | NullPointerException e){
            actualResource=new TravelerResource();

        }
        expectedResource.setId(actualResource.getId());
        assertThat(expectedResource).usingRecursiveComparison().isEqualTo(actualResource);
    }

    @Given("A Traveler Resource with values {string},{long},{string},{string},{string},{string},{string} is already stored")
    public void aTravelerResourceWithValuesIsAlreadyStored(String name,Long age,String dni,String email,String password,String phoneNumber,String pfp ) {
        CreateTravelerResource resource=new CreateTravelerResource()
                .withName(name)
                .withAge(age)
                .withDni(dni)
                .withEmail(email)
                .withPassword(password)
                .withPhoneNumber(phoneNumber)
                .withPfp(pfp);
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateTravelerResource> request =new HttpEntity<>(resource,headers);

        responseEntity=testRestTemplate.postForEntity(endPointPath,request,String.class);
    }
}
