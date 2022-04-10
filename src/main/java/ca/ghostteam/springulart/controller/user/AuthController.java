package ca.ghostteam.springulart.controller.user;

import ca.ghostteam.springulart.dto.*;
import ca.ghostteam.springulart.service.file.FileService;
import ca.ghostteam.springulart.service.mail.MailService;
import ca.ghostteam.springulart.service.user.UserService;
import ca.ghostteam.springulart.tools.UtilsUser;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-25
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UtilsUser utils;
    private final UserService userService;
    private final MailService mailService;
    private final FileService awss3ServiceImpl;

    public AuthController(
            UtilsUser utils,
            UserService userService,
            MailService mailService,
            FileService awss3ServiceImpl) {
        this.utils = utils;
        this.userService = userService;
        this.mailService = mailService;
        this.awss3ServiceImpl = awss3ServiceImpl;
    }

    @ApiResponses(value = {
            @ApiResponse(code=200, message = "OK", response = LoginDTO.class),
            @ApiResponse(code=400, message = "Bad Request"),
    })
    @ApiOperation(value = "Login", notes = "Login to the application")
    @PostMapping(value = "/login",  produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<LoginDTO> authenticateUser(@RequestBody AuthDTO authDTO) throws BadCredentialsException {

        // attempt authentication when user provides username and password
        UserDetailsDTO userDetailsDTO = (UserDetailsDTO) utils.authenticate(authDTO).getPrincipal();

        // get username of user
        String username = authDTO.getUsername();

        // get Role of user
        String userRole = utils.getUserRole(username);

        // get All authorization of user
        Map<String, Object> authorities = UtilsUser.getAllUserPermissions(userRole);

        // create Token
        String token = utils.createJwtToken(username, authorities);

        // set token to userDetailsDTO
        userDetailsDTO.setToken(token);

        // convert userDetailsDTO to LoginDTO
        LoginDTO loginDTO = UtilsUser.convertUserDetailsDTOtoLoginDTO(userDetailsDTO);
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

        // check role of user, if null, put ROLE_CLIENT as default
        if (registerDTO.getRole() == null || registerDTO.getRole().isEmpty())
            registerDTO.setRole("ROLE_CLIENT");

        // retrieve imageURL property from signupDTO
        MultipartFile imageURL = registerDTO.getImageURL();
        // upload image to AWS S3
        String imageURLString = awss3ServiceImpl.uploadImage(imageURL, "users");

        // create SignupDTO
        SignupDTO signupDTO = UtilsUser.convertRegisterDTOtoSignupDTO(registerDTO);

        // set imageURL property to signupDTO
        signupDTO.setImageURL(imageURLString);

        return userService
                .saveUser(signupDTO)
                .orElseThrow(() -> new IllegalStateException("User not registered"));
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

        // get user by email
        UserDTO userDTO = userService.findUserByEmail(email).get();

        // generate a new password
        String temporaryPassword = UUID.randomUUID().toString();

        // send mail to user with temporary password
        mailService.resetPassword(userDTO, temporaryPassword);

        // update password of UserModel
        userService.updatePassword(email, temporaryPassword);
        response.put("message", "your temporary password has been sent to " + email);
        response.put("status", "success");
        return ResponseEntity.ok( response );
    }
}
