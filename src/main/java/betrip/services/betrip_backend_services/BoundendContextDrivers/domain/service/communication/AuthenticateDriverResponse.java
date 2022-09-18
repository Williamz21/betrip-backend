package betrip.services.betrip_backend_services.BoundendContextDrivers.domain.service.communication;

import betrip.services.betrip_backend_services.BoundendContextDrivers.resource.AuthenticateDriverResource;
import betrip.services.betrip_backend_services.shared.domain.service.communication.BaseResponse;

public class AuthenticateDriverResponse extends BaseResponse<AuthenticateDriverResource> {

    public AuthenticateDriverResponse(String message) {
        super(message);
    }

    public AuthenticateDriverResponse(AuthenticateDriverResource resource) {
        super(resource);
    }
}
