package betrip.services.betrip_backend_services.BoundendContextDrivers.service;

import betrip.services.betrip_backend_services.BoundendContextDrivers.domain.model.entity.Driver;
import betrip.services.betrip_backend_services.BoundendContextDrivers.domain.persistence.DriverRepository;
import betrip.services.betrip_backend_services.BoundendContextDrivers.domain.service.DriverService;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.model.entity.Traveler;
import betrip.services.betrip_backend_services.shared.exception.ResourceNotFoundException;
import betrip.services.betrip_backend_services.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;


@Service
@Transactional(readOnly = true)
public class DriverServiceImpl implements DriverService {

    private static final  String ENTITY="Driver";
    private final DriverRepository driverRepository;
    private final Validator validator;

    public DriverServiceImpl(DriverRepository driverRepository, Validator validator) {
        this.driverRepository = driverRepository;
        this.validator = validator;
    }

    @Override
    public List<Driver> getAll() {
        return driverRepository.findAll();
    }

    @Override
    public Page<Driver> getAll(Pageable pageable) {
        return driverRepository.findAll(pageable);
    }

    @Override
    public Driver getById(Long driverId) {
        return driverRepository.findById(driverId).orElseThrow(()->new ResourceNotFoundException(ENTITY,driverId));
    }

    @Override
    public Driver create(Driver driver) {
        Set<ConstraintViolation<Driver>> violations =validator.validate(driver);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY,violations);


        Driver driverWithDni=driverRepository.findByDni(driver.getDni());
        if(driverWithDni!=null){
            throw new ResourceValidationException(ENTITY,"A driver with the same dni already exist");
        }
        Driver driverWithPhoneNumber=driverRepository.findByPhoneNumber(driver.getPhoneNumber());
        if(driverWithPhoneNumber!=null){
            throw new ResourceValidationException(ENTITY,"A driver with the same PhoneNumber already exist");
        }
        Driver driverWithPassword=driverRepository.findByEmail(driver.getEmail());
        if(driverWithPassword!=null){
            throw new ResourceValidationException(ENTITY,"A driver with the same Email already exist");
        }

        return driverRepository.save(driver);
    }

    @Override
    @Transactional
    public Driver update(Long driverId, Driver request) {
        Set<ConstraintViolation<Driver>> violations =validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY,violations);
        // Uniquessnes Validation
        Driver driverRepositoryByEmail=driverRepository.findByEmail(request.getEmail());
        if(driverRepositoryByEmail!=null&& !driverRepositoryByEmail.getId().equals(driverId)){
            throw new ResourceValidationException(ENTITY,"A post with the same email  already exist");
        }
        Driver driverWithDni=driverRepository.findByDni(request.getDni());
        if(driverWithDni!=null&& !driverWithDni.getId().equals(driverId)){
            throw new ResourceValidationException(ENTITY,"A post with the  same dni  already exist");
        }
        Driver driverWithPhoneNumber=driverRepository.findByPhoneNumber(request.getPhoneNumber());
        if(driverWithPhoneNumber!=null&& !driverWithPhoneNumber.getId().equals(driverId)){
            throw new ResourceValidationException(ENTITY,"A post with the same Phone Number  already exist");
        }



        return driverRepository.findById(driverId).map(post->driverRepository.save(post.withName(request.getName())
                        .withAge(request.getAge())
                        .withDni(request.getDni())
                        .withEmail(request.getEmail())
                        .withPassword(request.getPassword())
                        .withPfp(request.getPfp())
                ))
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,driverId));
    }

    @Override
    @Transactional
    public ResponseEntity<?> delete(Long driverId) {
        return driverRepository.findById(driverId).map(driver->{
            driverRepository.delete(driver);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(ENTITY,driverId));
    }
}
