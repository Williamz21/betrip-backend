package betrip.services.betrip_backend_services.BoundendContextDrivers.service;

import betrip.services.betrip_backend_services.BoundendContextDrivers.domain.model.entity.Driver;
import betrip.services.betrip_backend_services.BoundendContextDrivers.domain.persistence.DriverRepository;
import betrip.services.betrip_backend_services.BoundendContextDrivers.domain.service.DriverService;
import betrip.services.betrip_backend_services.BoundendContextDrivers.domain.service.communication.AuthenticateDriverResponse;
import betrip.services.betrip_backend_services.BoundendContextDrivers.domain.service.communication.RegisterDriverRequest;
import betrip.services.betrip_backend_services.BoundendContextDrivers.domain.service.communication.RegisterDriverResponse;
import betrip.services.betrip_backend_services.BoundendContextDrivers.middleware.DriverDetailImpl;
import betrip.services.betrip_backend_services.BoundendContextDrivers.resource.AuthenticateDriverResource;
import betrip.services.betrip_backend_services.BoundendContextDrivers.resource.DriverResource;
import betrip.services.betrip_backend_services.security.domain.model.entity.Role;
import betrip.services.betrip_backend_services.security.domain.model.enumeration.Roles;
import betrip.services.betrip_backend_services.security.domain.persistence.RoleRepository;
import betrip.services.betrip_backend_services.security.domain.service.communication.AuthenticateRequest;
import betrip.services.betrip_backend_services.BoundendContextDrivers.middleware.JwtHandlerDriver;
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

public class DriverServiceImpl implements DriverService {
    private static final Logger logger = LoggerFactory.getLogger(DriverServiceImpl.class);
    private static final String ENTITY = "Driver";
    @Autowired
    DriverRepository driverRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtHandlerDriver handler;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    EnhancedModelMapper mapper;

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
        return driverRepository.findById(driverId)
                .orElseThrow( () -> new ResourceNotFoundException(ENTITY, driverId));
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

            DriverDetailImpl userDetails = (DriverDetailImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            AuthenticateDriverResource resource = mapper.map(userDetails, AuthenticateDriverResource.class);
            resource.setRoles(roles);
            resource.setToken(token);

            AuthenticateDriverResponse response = new AuthenticateDriverResponse(resource);

            return ResponseEntity.ok(response.getResource());


        } catch (Exception e) {
            AuthenticateDriverResponse response = new AuthenticateDriverResponse(String.format("An error occurred while authenticating: %s", e.getMessage()));
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> register(RegisterDriverRequest request) {
        if (driverRepository.existsByEmail(request.getEmail())) {
            AuthenticateDriverResponse response = new AuthenticateDriverResponse("Email is already used.");
            return ResponseEntity.badRequest()
                    .body(response.getMessage());
        }

        try {

            Set<String> rolesStringSet = request.getRoles();
            Set<Role> roles = new HashSet<>();

            if (rolesStringSet == null) {
                roleRepository.findByName(Roles.ROLE_DRIVER)
                        .map(roles::add)
                        .orElseThrow(() -> new RuntimeException("Role not found."));
            } else {
                rolesStringSet.forEach(roleString ->
                        roleRepository.findByName(Roles.valueOf(roleString))
                                .map(roles::add)
                                .orElseThrow(() -> new RuntimeException("Role not found.")));
            }

            logger.info("Roles: {}", roles);

            Driver user = new Driver()
                    .withName(request.getName())
                    .withLast_name(request.getLast_name())
                    .withAge(request.getAge())
                    .withDni(request.getDni())
                    .withPfp(request.getPfp())
                    .withDistrict(request.getDistrict())
                    .withEmail(request.getEmail())
                    .withPassword(encoder.encode(request.getPassword()))
                    .withType(request.getType())
                    .withModel(request.getModel())
                    .withBrand(request.getBrand())
                    .withLicence_plate(request.getLicence_plate())
                    .withLicence_code(request.getLicence_code())
                    .withNumber_seats(request.getNumber_seats())
                    .withPhoneNumber(request.getPhoneNumber())
                    .withRoles(roles);


            driverRepository.save(user);
            DriverResource resource = mapper.map(user, DriverResource.class);
            RegisterDriverResponse response = new RegisterDriverResponse(resource);
            return ResponseEntity.ok(response.getResource());

        } catch (Exception e) {

            RegisterDriverResponse response = new RegisterDriverResponse(e.getMessage());
            return ResponseEntity.badRequest().body(response.getMessage());

        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Driver user = driverRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with username: %s", username)));
        return DriverDetailImpl.build(user);
    }

}
