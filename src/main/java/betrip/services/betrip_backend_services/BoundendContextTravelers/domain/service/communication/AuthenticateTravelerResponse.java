package betrip.services.betrip_backend_services.BoundendContextTravelers.domain.service.communication;

import betrip.services.betrip_backend_services.BoundendContextTravelers.resource.AuthenticateTravelerResource;
import betrip.services.betrip_backend_services.shared.domain.service.communication.BaseResponse;

public class AuthenticateTravelerResponse extends BaseResponse<AuthenticateTravelerResource> {
    public AuthenticateTravelerResponse(String message) {
        super(message);
    }

    public AuthenticateTravelerResponse(AuthenticateTravelerResource resource) {
        super(resource);
    }
}
