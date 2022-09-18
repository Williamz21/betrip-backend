package betrip.services.betrip_backend_services.BoundendContextTravelers.service;

import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.model.entity.Traveler;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.persistence.TravelerRepository;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.service.TravelerService;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.service.communication.AuthenticateTravelerResponse;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.service.communication.RegisterTravelerRequest;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.service.communication.RegisterTravelerResponse;
import betrip.services.betrip_backend_services.BoundendContextTravelers.middleware.JwtHandlerTraveler;
import betrip.services.betrip_backend_services.BoundendContextTravelers.middleware.TravelerDetailImpl;
import betrip.services.betrip_backend_services.BoundendContextTravelers.resource.AuthenticateTravelerResource;
import betrip.services.betrip_backend_services.BoundendContextTravelers.resource.TravelerResource;
import betrip.services.betrip_backend_services.security.domain.model.entity.Role;
import betrip.services.betrip_backend_services.security.domain.model.enumeration.Roles;
import betrip.services.betrip_backend_services.security.domain.persistence.RoleRepository;
import betrip.services.betrip_backend_services.security.domain.service.communication.AuthenticateRequest;
import betrip.services.betrip_backend_services.shared.exception.ResourceNotFoundException;
import betrip.services.betrip_backend_services.shared.mapping.EnhancedModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class TravelerServiceImpl implements TravelerService {
    private static final Logger logger = LoggerFactory.getLogger(TravelerServiceImpl.class);
    private static final String ENTITY = "Traveler";
    @Autowired
    TravelerRepository travelerRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtHandlerTraveler handler;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    EnhancedModelMapper mapper;
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
    public ResponseEntity<?> authenticate(AuthenticateRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword()
                    ));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = handler.generateToken(authentication);

            TravelerDetailImpl userDetails = (TravelerDetailImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            AuthenticateTravelerResource resource = mapper.map(userDetails, AuthenticateTravelerResource.class);
            resource.setRoles(roles);
            resource.setToken(token);

            AuthenticateTravelerResponse response = new AuthenticateTravelerResponse(resource);

            return ResponseEntity.ok(response.getResource());


        } catch (Exception e) {
            AuthenticateTravelerResponse response = new AuthenticateTravelerResponse(String.format("An error occurred while authenticating: %s", e.getMessage()));
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> register(RegisterTravelerRequest request) {
        if (travelerRepository.existsByEmail(request.getEmail())) {
            AuthenticateTravelerResponse response = new AuthenticateTravelerResponse("Email is already used.");
            return ResponseEntity.badRequest()
                    .body(response.getMessage());
        }

        try {

            Set<String> rolesStringSet = request.getRoles();
            Set<Role> roles = new HashSet<>();

            if (rolesStringSet == null) {
                roleRepository.findByName(Roles.ROLE_TRAVELER)
                        .map(roles::add)
                        .orElseThrow(() -> new RuntimeException("Role not found."));
            } else {
                rolesStringSet.forEach(roleString ->
                        roleRepository.findByName(Roles.valueOf(roleString))
                                .map(roles::add)
                                .orElseThrow(() -> new RuntimeException("Role not found.")));
            }

            logger.info("Roles: {}", roles);

            Traveler user = new Traveler()
                    .withName(request.getName())
                    .withAge(request.getAge())
                    .withDni(request.getDni())
                    .withEmail(request.getEmail())
                    .withPassword(encoder.encode(request.getPassword()))
                    .withPhoneNumber(request.getPhoneNumber())
                    .withPfp(request.getPfp())
                    .withRoles(roles);


            travelerRepository.save(user);
            TravelerResource resource = mapper.map(user, TravelerResource.class);
            RegisterTravelerResponse response = new RegisterTravelerResponse(resource);
            return ResponseEntity.ok(response.getResource());

        } catch (Exception e) {

            RegisterTravelerResponse response = new RegisterTravelerResponse(e.getMessage());
            return ResponseEntity.badRequest().body(response.getMessage());

        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Traveler user = travelerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with username: %s", username)));
        return TravelerDetailImpl.build(user);
    }

}
