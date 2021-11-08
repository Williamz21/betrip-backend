package betrip.services.betrip_backend_services.BoundendContextTravelers.service;

import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.model.entity.Traveler;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.persistence.TravelerRepository;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.service.TravelerService;
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
public class TravelerServiceImpl implements TravelerService {
    private static final  String ENTITY="Traveler";
    private final TravelerRepository travelerRepository;
    private final Validator validator;

    public TravelerServiceImpl(TravelerRepository travelerRepository, Validator validator) {
        this.travelerRepository = travelerRepository;
        this.validator = validator;
    }
    @Override
    public List<Traveler> getAll() {
        return travelerRepository.findAll();
    }

    @Override
    public Page<Traveler> getAll(Pageable pageable) {
        return travelerRepository.findAll(pageable);
    }

    @Override
    public Traveler getById(Long travelerId) {

        return travelerRepository.findById(travelerId).orElseThrow(()->new ResourceNotFoundException(ENTITY,travelerId));

    }

    @Override
    @Transactional
    public Traveler create(Traveler traveler) {
        Set<ConstraintViolation<Traveler>> violations =validator.validate(traveler);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY,violations);

        // Uniquessnes Validation

        Traveler travelerWithDni=travelerRepository.findByDni(traveler.getDni());
        if(travelerWithDni!=null){
            throw new ResourceValidationException(ENTITY,"A traveler with the same dni already exist");
        }
        Traveler travelerWithPhoneNumber=travelerRepository.findByPhoneNumber(traveler.getPhoneNumber());
        if(travelerWithPhoneNumber!=null){
            throw new ResourceValidationException(ENTITY,"A traveler with the same PhoneNumber already exist");
        }
        Traveler travelerWithPassword=travelerRepository.findByEmail(traveler.getEmail());
        if(travelerWithPassword!=null){
            throw new ResourceValidationException(ENTITY,"A traveler with the same Email already exist");
        }

        return travelerRepository.save(traveler);
    }

    @Override
    @Transactional
    public Traveler update(Long travelerId, Traveler request) {
        Set<ConstraintViolation<Traveler>> violations =validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY,violations);
        // Uniquessnes Validation
        Traveler travelerRepositoryByEmail=travelerRepository.findByEmail(request.getEmail());
        if(travelerRepositoryByEmail!=null&& !travelerRepositoryByEmail.getId().equals(travelerId)){
            throw new ResourceValidationException(ENTITY,"A post with the same email  already exist");
        }
        Traveler travelerWithDni=travelerRepository.findByDni(request.getDni());
        if(travelerWithDni!=null&& !travelerWithDni.getId().equals(travelerId)){
            throw new ResourceValidationException(ENTITY,"A post with the  same dni  already exist");
        }
        Traveler travelerWithPhoneNumber=travelerRepository.findByPhoneNumber(request.getPhoneNumber());
        if(travelerWithDni!=null&& !travelerWithDni.getId().equals(travelerId)){
            throw new ResourceValidationException(ENTITY,"A post with the same Phone Number  already exist");
        }



        return travelerRepository.findById(travelerId).map(post->travelerRepository.save(post.withName(request.getName())
                                .withAge(request.getAge())
                                .withDni(request.getDni())
                                .withEmail(request.getEmail())
                                .withPassword(request.getPassword())
                                .withPfp(request.getPfp())
                ))
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,travelerId));
    }

    @Override
    @Transactional
    public ResponseEntity<?> delete(Long travelerId) {
        return travelerRepository.findById(travelerId).map(traveler->{
            travelerRepository.delete(traveler);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(ENTITY,travelerId));
    }

}
