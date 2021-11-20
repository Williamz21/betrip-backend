package betrip.services.betrip_backend_services.BoundendContextTravelers.domain.service.communication;

import betrip.services.betrip_backend_services.BoundendContextTravelers.resource.TravelerResource;
import betrip.services.betrip_backend_services.shared.domain.service.communication.BaseResponse;

public class RegisterResponse extends BaseResponse<TravelerResource> {
    public RegisterResponse(String message){
        super(message);
    }

    public RegisterResponse(TravelerResource resource){
        super(resource);
    }
}
