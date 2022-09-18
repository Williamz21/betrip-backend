package betrip.services.betrip_backend_services.BoundendContextDrivers.mapping;

import betrip.services.betrip_backend_services.BoundendContextDrivers.domain.model.entity.Driver;
import betrip.services.betrip_backend_services.BoundendContextDrivers.resource.CreateDriverResource;
import betrip.services.betrip_backend_services.BoundendContextDrivers.resource.DriverResource;
import betrip.services.betrip_backend_services.BoundendContextDrivers.resource.UpdateDriverResource;
import betrip.services.betrip_backend_services.security.domain.model.entity.Role;
import betrip.services.betrip_backend_services.shared.mapping.EnhancedModelMapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class DriverMapper implements Serializable
{
    @Autowired
    EnhancedModelMapper mapper;

    //Object Mapping
    public DriverResource toResource(Driver model){
        return  mapper.map(model,DriverResource.class);
    }
    public Page<DriverResource> modelListToPage(List<Driver> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,DriverResource.class),pageable, modelList.size());
    }

    Converter<Role, String> roleToString = new AbstractConverter<>() {
        @Override
        protected String convert(Role role) {
            return role == null ? null : role.getName().name();
        }
    };
    public Driver toModel(CreateDriverResource resource){
        return mapper.map(resource,Driver.class);
    }

    public Driver toModel(UpdateDriverResource resource){
        return mapper.map(resource,Driver.class);
    }

}
