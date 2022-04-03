package ca.ghostteam.springulart.controller.user;

import ca.ghostteam.springulart.config.bean.JwtConfig;
import ca.ghostteam.springulart.dto.*;
import ca.ghostteam.springulart.security.ApplicationUserRole;
import ca.ghostteam.springulart.service.File.AWSS3Service;
import ca.ghostteam.springulart.service.mail.MailService;
import ca.ghostteam.springulart.service.user.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;
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
    private final MailService mailService;
    private final AWSS3Service awss3Service;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtConfig jwtConfig,
            UserService userService,
            MailService mailService,
            AWSS3Service awss3Service) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.userService = userService;
        this.mailService = mailService;
        this.awss3Service = awss3Service;
    }

    @ApiResponses(value = {
            @ApiResponse(code=200, message = "OK", response = LoginDTO.class),
            @ApiResponse(code=400, message = "Bad Request"),
    })
    @ApiOperation(value = "Login", notes = "Login to the application")
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
            @ApiResponse(code=201, message = "Successfully created an account", response = UserDTO.class),
            @ApiResponse(code=400, message = "Bad Request"),
    })
    @ApiOperation(value = "Register", notes = "Register to the application")
    @PostMapping(value ="/register", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserDTO registerUser(@ModelAttribute RegisterDTO registerDTO) throws Exception {

        // if user already exists
        if (userService.existsUserByEmail(registerDTO.getEmail()))
            throw new IllegalStateException("User already exists");

        // retrieve imageURL property from signupDTO
        MultipartFile imageURL = registerDTO.getImageURL();
        // upload image to AWS S3
        String imageURLString = awss3Service.uploadImage(imageURL);

        // create SignupDTO
        SignupDTO signupDTO = convertRegisterDTOtoSignupDTO(registerDTO);

        // set imageURL property to signupDTO
        signupDTO.setImageURL(imageURLString);

        return userService
                .saveUser(signupDTO)
                .orElseThrow(() -> new IllegalStateException("User not registered"));
    }

    /**
     * Method to convert RegisterDTO to SignupDTO
     * @param registerDTO registerDTO object with user information to be registered
     * @return SignupDTO object with user information to be registered
     */
    private SignupDTO convertRegisterDTOtoSignupDTO(RegisterDTO registerDTO) {
        SignupDTO signupDTO = new SignupDTO();
        signupDTO.setFname(registerDTO.getFname());
        signupDTO.setLname(registerDTO.getLname());
        signupDTO.setEmail(registerDTO.getEmail());
        signupDTO.setPassword(registerDTO.getPassword());
        signupDTO.setDob(registerDTO.getDob());
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
    private LoginDTO convertUserDetailsDTOtoLoginDTO(UserDetailsDTO userDetailsDTO) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setId(userDetailsDTO.getUserModel().getId());
        loginDTO.setEmail(userDetailsDTO.getUsername());
        loginDTO.setToken(userDetailsDTO.getToken());
        loginDTO.setRole(userDetailsDTO.getUserModel().getRole());

        return loginDTO;
    }

    @ApiResponse(code = 200, message = "Successfully reset password")
    @ApiOperation(value = "Reset password")
    @PostMapping(value = "/reset-password", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, String>> resetPassword(@RequestBody AuthDTO resetPasswordDTO) {
        String email = resetPasswordDTO.getUsername();
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "User not found");
        response.put("status", "failure");

        if(!userService.existsUserByEmail(email))
            return ResponseEntity.badRequest().body(response);

        // generate a new password
        String temporaryPassword = UUID.randomUUID().toString();

        // send mail to user with temporary password
        mailService.resetPassword(email, temporaryPassword);

        // update password of UserModel
        userService.updatePassword(email, temporaryPassword);
        response.put("message", "your temporary password has been sent to " + email);
        response.put("status", "success");
        return ResponseEntity.ok( response );
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
