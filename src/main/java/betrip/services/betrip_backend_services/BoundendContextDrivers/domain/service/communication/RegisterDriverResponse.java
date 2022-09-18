package betrip.services.betrip_backend_services.BoundendContextDrivers.domain.service.communication;

import betrip.services.betrip_backend_services.BoundendContextDrivers.resource.DriverResource;
import betrip.services.betrip_backend_services.shared.domain.service.communication.BaseResponse;

public class RegisterDriverResponse extends BaseResponse<DriverResource> {
    public RegisterDriverResponse(String message) {
        super(message);
    }

    public RegisterDriverResponse(DriverResource resource) {
        super(resource);
    }
}
