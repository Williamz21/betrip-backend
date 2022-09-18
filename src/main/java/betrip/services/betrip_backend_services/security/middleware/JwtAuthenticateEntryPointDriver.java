package betrip.services.betrip_backend_services.security.middleware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticateEntryPointDriver implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticateEntryPointDriver.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        logger.error("Unauthorized error: {}", exception.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized error");
    }
}
