package betrip.services.betrip_backend_services.step;

import betrip.services.betrip_backend_services.BoundendContextTravelers.resource.CreateTravelerResource;
import betrip.services.betrip_backend_services.BoundendContextTravelers.resource.UpdateTravelerResource;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class TravelerUpdatingStepDefinitions {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int randomServerPort;
    private String endPointPath;
    private ResponseEntity<String> responseEntity;


    @When("A Traveler Update Request is sent with values {string},{long},{string},{string},{string},{string},{string}")
    public void aTravelerUpdateRequestIsSentWithValues(String name,Long age,String dni,String email,String password,String phoneNumber,String pfp) {
        UpdateTravelerResource resource=new UpdateTravelerResource();
        resource.setName(name);
        resource.setAge(age);
        resource.setDni(dni);
        resource.setEmail(email);
        resource.setPassword(password);
        resource.setPhoneNumber(phoneNumber);
        resource.setPfp(pfp);

        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UpdateTravelerResource> request=new HttpEntity<>(resource,headers);
        responseEntity=testRestTemplate.postForEntity(endPointPath,request,String.class);
    }

    @Given("The EndPoint to update {string} is available")
    public void theEndPointToUpdateIsAvailable(String endPointPath) {
            this.endPointPath=String.format(endPointPath,randomServerPort);
    }
}
