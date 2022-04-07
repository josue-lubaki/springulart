package ca.ghostteam.springulart.tools;

import ca.ghostteam.springulart.config.bean.JwtConfig;
import ca.ghostteam.springulart.dto.*;
import ca.ghostteam.springulart.security.ApplicationUserRole;
import ca.ghostteam.springulart.service.user.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-04
 */
@Component
public class UtilsUser {

    private final UserService userService;
    private final JwtConfig jwtConfig;
    private final AuthenticationManager authenticationManager;

    public UtilsUser(UserService userService,
                     JwtConfig jwtConfig,
                     AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtConfig = jwtConfig;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Method to convert RegisterDTO to SignupDTO
     * @param registerDTO registerDTO object with user information to be registered
     * @return SignupDTO object with user information to be registered
     */
    public static SignupDTO convertRegisterDTOtoSignupDTO(RegisterDTO registerDTO) {
        SignupDTO signupDTO = new SignupDTO();
        signupDTO.setFname(registerDTO.getFname());
        signupDTO.setLname(registerDTO.getLname());
        signupDTO.setEmail(registerDTO.getEmail());
        signupDTO.setPassword(registerDTO.getPassword());
        signupDTO.setDob(LocalDate.parse(registerDTO.getDob()));
        signupDTO.setRole(registerDTO.getRole());

        // set address information
        signupDTO.setApartment(registerDTO.getApartment());
        signupDTO.setStreet(registerDTO.getStreet());
        signupDTO.setCity(registerDTO.getCity());
        signupDTO.setState(registerDTO.getState());
        signupDTO.setZip(registerDTO.getZip());
        signupDTO.setPhone(registerDTO.getPhone());

        return signupDTO;
    }

    /**
     * Method to convert UserDetailsDTO to LoginDTO
     * @param userDetailsDTO UserDetailsDTO to convert to LoginDTO
     * @return LoginDTO
     */
    public static LoginDTO convertUserDetailsDTOtoLoginDTO(UserDetailsDTO userDetailsDTO) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setId(userDetailsDTO.getUserModel().getId());
        loginDTO.setEmail(userDetailsDTO.getUsername());
        loginDTO.setToken(userDetailsDTO.getToken());
        loginDTO.setRole(userDetailsDTO.getUserModel().getRole());

        return loginDTO;
    }

    /**
     * method which allows to retrieve all the permissions of a user from his role
     * @param roleName the role that the currently logged-in user
     * @exception RuntimeException when role does not exist
     * @return Map<String, Object>
     * @throws RuntimeException when role does not exist
     * */
    public static Map<String, Object> getAllUserPermissions(String roleName){
        Map<String, Object> authorities = new HashMap<>();

        switch(roleName){
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
     * Method to create JWT Token
     * @param username username of user
     * @param authorities Map<String, Object> authorities
     * @return String
     * */
    public String createJwtToken(String username, Map<String, Object> authorities) {
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withIssuer(jwtConfig.getApplicationName())
                .withPayload(authorities)
                .withExpiresAt(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                .sign(Algorithm.HMAC256(jwtConfig.getSecretKey()));
    }

    /**
     * method that allows to retrieve the role of a user from Authentication
     * @param username : username of user
     * @return String, default value is ROLE_CLIENT
     * */
    public String getUserRole(String username){
        // retrieve role of user
        return userService.loadUserByUsername(username).getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .map(SimpleGrantedAuthority::new)
                .map(SimpleGrantedAuthority::getAuthority)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User %s has no role", username)));
    }

    /**
     * Method to authenticate user and return an Authentication object
     * @param authDTO : AuthDTO object with username and password
     * @return Authentication
     * @throws BadCredentialsException : if username or password is incorrect
     * */
    public Authentication authenticate(AuthDTO authDTO) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authDTO.getUsername().toLowerCase().trim(),
                        authDTO.getPassword()
                )
        );
    }
}
