package ca.ghostteam.springulart.controller.users;

import ca.ghostteam.springulart.bean.JwtConfig;
import ca.ghostteam.springulart.dto.*;
import ca.ghostteam.springulart.security.ApplicationUserRole;
import ca.ghostteam.springulart.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-25
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final UserService userService;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtConfig jwtConfig,
            UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.userService = userService;
    }

    @ApiResponses(value = {
            @ApiResponse(code=200, message = "OK", response = LoginDTO.class),
            @ApiResponse(code=400, message = "Bad Request"),
    })
    @PostMapping(value = "/login",  produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<LoginDTO> authenticateUser(@RequestBody AuthDTO authDTO) throws BadCredentialsException {

        // attempt authentication when user provides username and password
        UserDetailsDTO userDetailsDTO = (UserDetailsDTO) authenticate(authDTO).getPrincipal();

        // get username of user
        String username = authDTO.getUsername();

        // get Role of user
        String userRole = getUserRole(username);

        // get All authorization of user
        Map<String, Object> authorities = getAllUserPermissions(userRole);

        // create Token
        String token = createJwtToken(username, authorities);

        // set token to userDetailsDTO
        userDetailsDTO.setToken(token);

        // convert userDetailsDTO to LoginDTO
        LoginDTO loginDTO = convertUserDetailsDTOtoLoginDTO(userDetailsDTO);
        return ResponseEntity.ok(loginDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(code=200, message = "OK", response = UserDTO.class),
            @ApiResponse(code=400, message = "Bad Request"),
    })
    @PostMapping(value ="/register", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserDTO registerUser(@RequestBody SignupDTO signupDTO) throws Exception {
        // if user already exists
        if (userService.findUserByEmail(signupDTO.getEmail()))
            throw new Exception("User already exists");

        return userService
                .saveUser(signupDTO)
                .orElseThrow(() -> new IllegalStateException("User not registered"));
    }

    /**
     * Method to convert UserDetailsDTO to LoginDTO
     * @param userDetailsDTO UserDetailsDTO to convert to LoginDTO
     * @return LoginDTO
     */
    private LoginDTO convertUserDetailsDTOtoLoginDTO(UserDetailsDTO userDetailsDTO) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setId(userDetailsDTO.getUserModel().getId());
        loginDTO.setEmail(userDetailsDTO.getUsername());
        loginDTO.setToken(userDetailsDTO.getToken());
        loginDTO.setRole(userDetailsDTO.getUserModel().getRole());

        return loginDTO;
    }

    /**
     * Method to authenticate user and return an Authentication object
     * @param authDTO : AuthDTO object with username and password
     * @return Authentication
     * @throws BadCredentialsException : if username or password is incorrect
     * */
    private Authentication authenticate(AuthDTO authDTO) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authDTO.getUsername().toLowerCase().trim(),
                        authDTO.getPassword()
                )
        );
    }

    /**
     * Method to create JWT Token
     * @param username username of user
     * @param authorities Map<String, Object> authorities
     * @return String
     * */
    private String createJwtToken(String username, Map<String, Object> authorities) {
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withIssuer(jwtConfig.getApplicationName())
                .withPayload(authorities)
                .withExpiresAt(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                .sign(Algorithm.HMAC256(jwtConfig.getSecretKey()));
    }

    /**
     * method which allows to retrieve all the permissions of a user from his role
     * @param roleName the role that the currently logged-in user
     * @exception RuntimeException when role does not exist
     * @return Map<String, Object>
     * @throws RuntimeException when role does not exist
     * */
    private Map<String, Object> getAllUserPermissions(String roleName){
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
     * method that allows to retrieve the role of a user from Authentication
     * @param username : username of user
     * @return String, default value is ROLE_CLIENT
     * */
    private String getUserRole(String username){
        // retrieve role of user
        return userService.loadUserByUsername(username).getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .map(SimpleGrantedAuthority::new)
                .map(SimpleGrantedAuthority::getAuthority)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User %s has no role", username)));
    }
}
