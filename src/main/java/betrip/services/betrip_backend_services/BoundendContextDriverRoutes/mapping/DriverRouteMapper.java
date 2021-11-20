package betrip.services.betrip_backend_services.BoundendContextDriverRoutes.mapping;

import betrip.services.betrip_backend_services.BoundenContextTravelEvents.domain.model.entity.TravelEvent;
import betrip.services.betrip_backend_services.BoundenContextTravelEvents.resource.CreateTravelEventResource;
import betrip.services.betrip_backend_services.BoundenContextTravelEvents.resource.TravelEventResource;
import betrip.services.betrip_backend_services.BoundenContextTravelEvents.resource.UpdateTravelEventResource;
import betrip.services.betrip_backend_services.BoundendContextDriverRoutes.domain.model.entity.DriverRoute;
import betrip.services.betrip_backend_services.BoundendContextDriverRoutes.resource.CreateDriverRouteResource;
import betrip.services.betrip_backend_services.BoundendContextDriverRoutes.resource.DriverRouteResource;
import betrip.services.betrip_backend_services.BoundendContextDriverRoutes.resource.UpdateDriverRouteResource;
import betrip.services.betrip_backend_services.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class DriverRouteMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public DriverRouteResource toResource(DriverRoute model){
        return mapper.map(model,DriverRouteResource.class);

    }

    public Page<DriverRouteResource> modelToListToPage(List<DriverRoute> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,DriverRouteResource.class),pageable,modelList.size());
    }

    public DriverRoute toModel(CreateDriverRouteResource resource){
        return  mapper.map(resource,DriverRoute.class);
    }
    public DriverRoute toModel(UpdateDriverRouteResource resource){
        return  mapper.map(resource,DriverRoute.class);
    }

}
