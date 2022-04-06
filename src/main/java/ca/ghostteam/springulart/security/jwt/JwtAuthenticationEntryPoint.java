package ca.ghostteam.springulart.security.jwt;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.http.HttpStatus;

@Component
@AllArgsConstructor
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        switch (response.getStatus()) {
            case 400: {
                log.error("Bad Request: {}", authException.getMessage());
                response.sendError(HttpStatus.BAD_REQUEST.value(), authException.getMessage());
                break;
            }
            case 401: {
                log.error("Unauthorized error: {}", authException.getMessage());
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
                break;
            }
            case 403:
                log.error("Forbidden error: {}", authException.getMessage());
                response.sendError(HttpStatus.FORBIDDEN.value(), "Forbidden");
                break;
            case 404:
                log.error("Not found error: {}", authException.getMessage());
                response.sendError(HttpStatus.NOT_FOUND.value(), "Not found");
                break;
            case 503:
                log.error("Service unavailable error: {}", authException.getMessage());
                response.sendError(HttpStatus.SERVICE_UNAVAILABLE.value(), "Service unavailable");
                break;
            default: {
                // if Bad credentials
                if (authException.getMessage().matches("Bad credentials")) {
                    log.error("Bad credentials error -: {}", authException.getMessage());
                    response.sendError(HttpStatus.UNAUTHORIZED.value(), "Bad credentials");
                }
                else {
                    log.error("Unknown error: {}", authException.getMessage());
                    response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error");
                }
                break;
            }
        }
    }
}
