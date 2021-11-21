package betrip.services.betrip_backend_services.BoundenContextTravelEvents.mapping;

import betrip.services.betrip_backend_services.BoundenContextTravelEvents.domain.model.entity.TravelEvent;
import betrip.services.betrip_backend_services.BoundenContextTravelEvents.resource.CreateTravelEventResource;
import betrip.services.betrip_backend_services.BoundenContextTravelEvents.resource.TravelEventResource;
import betrip.services.betrip_backend_services.BoundenContextTravelEvents.resource.TravelEvents;
import betrip.services.betrip_backend_services.BoundenContextTravelEvents.resource.UpdateTravelEventResource;
import betrip.services.betrip_backend_services.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class TravelEventMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public TravelEventResource toResource(TravelEvent model){
        return mapper.map(model,TravelEventResource.class);

    }
    public TravelEvents toResourceList(TravelEvent model){
        return mapper.map(model,TravelEvents.class);
    }

    public Page<TravelEventResource> modelToListToPage(List<TravelEvent> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,TravelEventResource.class),pageable,modelList.size());
    }
    public Page<TravelEvents> modelToListToPage2(List<TravelEvent> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,TravelEvents.class),pageable,modelList.size());
    }
    public TravelEvent toModel(CreateTravelEventResource resource){
        return  mapper.map(resource,TravelEvent.class);
    }
    public TravelEvent toModel(UpdateTravelEventResource resource){
        return  mapper.map(resource,TravelEvent.class);
    }
}

