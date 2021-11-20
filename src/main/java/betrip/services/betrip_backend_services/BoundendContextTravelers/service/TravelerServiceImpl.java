package betrip.services.betrip_backend_services.BoundendContextTravelers.service;

import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.model.entity.Role;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.model.entity.Traveler;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.model.enumeration.Roles;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.persistence.RoleRepository;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.persistence.TravelerRepository;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.service.TravelerService;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.service.communication.AuthenticateRequest;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.service.communication.AuthenticateResponse;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.service.communication.RegisterRequest;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.service.communication.RegisterResponse;
import betrip.services.betrip_backend_services.BoundendContextTravelers.middleware.JwtHandler;
import betrip.services.betrip_backend_services.BoundendContextTravelers.middleware.UserDetailsImpl;
import betrip.services.betrip_backend_services.BoundendContextTravelers.resource.AuthenticateResource;
import betrip.services.betrip_backend_services.BoundendContextTravelers.resource.TravelerResource;
import betrip.services.betrip_backend_services.shared.exception.ResourceNotFoundException;
import betrip.services.betrip_backend_services.shared.exception.ResourceValidationException;
import betrip.services.betrip_backend_services.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service

public class TravelerServiceImpl implements TravelerService {
   /* private static final  String ENTITY="Traveler";
    private final TravelerRepository travelerRepository;
    private final Validator validator;

    public TravelerServiceImpl(TravelerRepository travelerRepository, Validator validator) {
        this.travelerRepository = travelerRepository;
        this.validator = validator;
    }*/
   /* @Override
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
    }*/
   @Autowired
   TravelerRepository travelerRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtHandler handler;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    EnhancedModelMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Traveler user = travelerRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(
                        String.format("User not found with username: %s", username)));
        return UserDetailsImpl.build(user);
    }

    @Override
    public List<Traveler> getAll() {
        return travelerRepository.findAll();
    }

    @Override
    public ResponseEntity<?> authenticate(AuthenticateRequest request) {
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = handler.generateToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            AuthenticateResource resource = mapper.map(userDetails, AuthenticateResource.class);
            resource.setRoles(roles);
            resource.setToken(token);
            AuthenticateResponse response = new AuthenticateResponse(resource);

            return ResponseEntity.ok(response.getResource());
        }catch (Exception e){
            AuthenticateResponse response = new AuthenticateResponse(
                    String.format("An error occurred while authenticating : %s", e.getMessage()));
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> register(RegisterRequest request) {
        if(travelerRepository.existsByUsername(request.getUsername())){
            AuthenticateResponse response = new AuthenticateResponse("Username is already used.");
            return ResponseEntity.badRequest().body(response.getMessage());
        }

        if(travelerRepository.existsByEmail(request.getEmail())){
            AuthenticateResponse response = new AuthenticateResponse("Email is already used.");
            return ResponseEntity.badRequest().body(response.getMessage());
        }

        try{
            Set<String> rolesStringSet = request.getRoles();
            Set<Role> roles = new HashSet<>();

            if(rolesStringSet == null){
                roleRepository.findByName(Roles.ROLE_USER)
                        .map(roles::add)
                        .orElseThrow(()->new RuntimeException("Role not found"));
            } else{
                rolesStringSet.forEach(roleString ->
                        roleRepository.findByName(Roles.valueOf(roleString))
                                .map(roles::add)
                                .orElseThrow(()->new RuntimeException("Role not found.")));
            }

            Traveler user = new Traveler()
                    .withName(request.getName())
                    .withAge(request.getAge())
                    .withUsername(request.getUsername())
                    .withDni(request.getDni())
                    .withEmail(request.getEmail())
                    .withPassword(encoder.encode(request.getPassword()))
                    .withPhoneNumber(request.getPhoneNumber())
                    .withPfp(request.getPfp());

            travelerRepository.save(user);

            TravelerResource resource = mapper.map(user, TravelerResource.class);
            RegisterResponse response = new RegisterResponse(resource);
            return ResponseEntity.ok(response.getResource());
        }catch (Exception e){
            RegisterResponse response = new RegisterResponse(e.getMessage());
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }
}
