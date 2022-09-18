package betrip.services.betrip_backend_services.BoundendContextTravelers.domain.service.communication;

import betrip.services.betrip_backend_services.BoundendContextTravelers.resource.TravelerResource;
import betrip.services.betrip_backend_services.shared.domain.service.communication.BaseResponse;

public class RegisterTravelerResponse extends BaseResponse<TravelerResource> {
    public RegisterTravelerResponse(String message) {
        super(message);
    }

    public RegisterTravelerResponse(TravelerResource resource) {
        super(resource);
    }
}
