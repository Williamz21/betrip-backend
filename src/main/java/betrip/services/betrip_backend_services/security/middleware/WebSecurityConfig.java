package betrip.services.betrip_backend_services.security.middleware;

import betrip.services.betrip_backend_services.BoundendContextDrivers.domain.service.DriverService;
import betrip.services.betrip_backend_services.BoundendContextDrivers.middleware.JwtAuthorizationFilterDriver;
import betrip.services.betrip_backend_services.BoundendContextTravelers.domain.service.TravelerService;
import betrip.services.betrip_backend_services.BoundendContextTravelers.middleware.JwtAuthorizationFilterTraveler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DriverService driverService;

    @Autowired
    TravelerService travelerService;

    @Autowired
    JwtAuthenticateEntryPointDriver unauthorizedHandler;

    @Bean
    public JwtAuthorizationFilterDriver authorizationFilterDriver() {
        return new JwtAuthorizationFilterDriver();
    }

    @Bean
    public JwtAuthorizationFilterTraveler authorizationFilterTraveler() {
        return new JwtAuthorizationFilterTraveler();
    }


    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(driverService);
        builder.userDetailsService(travelerService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/v1/drivers/auth/*", "/api/v1/travelers/auth/**", "/swagger-ui/**", "/api-docs/**").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(authorizationFilterDriver(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(authorizationFilterTraveler(), UsernamePasswordAuthenticationFilter.class);
    }
}
