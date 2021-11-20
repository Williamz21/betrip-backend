package betrip.services.betrip_backend_services.BoundendContextTravelers.domain.service.communication;

import betrip.services.betrip_backend_services.BoundendContextTravelers.resource.AuthenticateResource;
import betrip.services.betrip_backend_services.shared.domain.service.communication.BaseResponse;

public class AuthenticateResponse extends BaseResponse<AuthenticateResource> {
    public AuthenticateResponse(String message){
        super(message);
    }

    public AuthenticateResponse(AuthenticateResource resource){
        super(resource);
    }
}
