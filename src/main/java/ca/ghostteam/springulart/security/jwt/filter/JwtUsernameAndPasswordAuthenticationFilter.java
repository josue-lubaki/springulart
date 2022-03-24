package ca.ghostteam.springulart.security.jwt.filter;

import ca.ghostteam.springulart.bean.JwtConfig;
import ca.ghostteam.springulart.dto.AuthDTO;
import ca.ghostteam.springulart.security.ApplicationUserRole;
import ca.ghostteam.springulart.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-19
 */

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final UserService userService;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager,
                                                      JwtConfig jwtConfig,
                                                      UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            // read username and password from json request
            AuthDTO authDTO = new ObjectMapper().readValue(request.getInputStream(), AuthDTO.class);

            // instancie Authentication interface with information of authenticationRequest
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authDTO.getUsername(),
                    authDTO.getPassword()
            );

            return authenticationManager.authenticate(authentication);

        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) {

        String userRole = getUserRole(authResult);
        Map<String, Object> authorities = getAllUserPermissions(userRole);

        // create Token
        String token = JWT.create()
                .withSubject(authResult.getName())
                .withIssuedAt(new Date())
                .withIssuer(jwtConfig.getApplicationName())
                .withPayload(authorities)
                .withExpiresAt(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                .sign(Algorithm.HMAC256(jwtConfig.getSecretKey()));
        response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + " " + token);
    }

    /**
     * method which allows to retrieve all the permissions of a user from his role
     * @param userRole the role that the currently logged-in user
     * @exception RuntimeException when role does not exist
     * @return Map<String, Object>
     * */
    private  Map<String, Object> getAllUserPermissions(String userRole){
        Map<String, Object> authorities = new HashMap<>();

        switch(userRole){
            case "ROLE_CLIENT" : {
                // retrieve all permissions of CLIENT
                authorities.put("authorities", ApplicationUserRole.CLIENT.getGrantedAuthorities()
                        .stream()
                        .map(SimpleGrantedAuthority::getAuthority)
                        .map( a-> {
                            Map<String, Object> map = new HashMap<>();
                            map.put("authority", a);
                            return map;
                        })
                        .collect(Collectors.toList()));
                break;
            }
            case "ROLE_BARBER" : {
                // retrieve all permission of ADMIN
                authorities.put("authorities", ApplicationUserRole.BARBER.getGrantedAuthorities()
                        .stream()
                        .map(SimpleGrantedAuthority::getAuthority)
                        .map( a-> {
                            Map<String, Object> map = new HashMap<>();
                            map.put("authority", a);
                            return map;
                        })
                        .collect(Collectors.toList()));
                break;
            }
            case "ROLE_ADMIN" : {
                // retrieve all permission of ADMIN
                authorities.put("authorities", ApplicationUserRole.ADMIN.getGrantedAuthorities()
                        .stream()
                        .map(SimpleGrantedAuthority::getAuthority)
                        .map( a-> {
                            Map<String, Object> map = new HashMap<>();
                            map.put("authority", a);
                            return map;
                        })
                        .collect(Collectors.toList()));
                break;
            }
            default: {
                throw new RuntimeException("Role does not exist");
            }

        }

        return authorities;
    }

    /**
     * method that allows to retrieve the role of a user from Authentication
     * @param authResult : Authentication request on successfulAuthentication
     * @return String
     * */
    private String getUserRole(Authentication authResult){
        // retrieve role of user
        return userService.loadUserByUsername(authResult.getName()).getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .map(SimpleGrantedAuthority::new)
                .map(SimpleGrantedAuthority::getAuthority)
                .findFirst()
                .orElse("ROLE_USER");
    }
}
