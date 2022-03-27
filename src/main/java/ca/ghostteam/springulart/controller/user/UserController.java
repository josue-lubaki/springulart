package ca.ghostteam.springulart.controller.user;

import ca.ghostteam.springulart.bean.JwtConfig;
import ca.ghostteam.springulart.dto.UserDTO;
import ca.ghostteam.springulart.dto.UserDetailsDTO;
import ca.ghostteam.springulart.security.jwt.filter.JwtTokenVerifier;
import ca.ghostteam.springulart.service.UserService;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.annotations.ApiResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-19
 */
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final HttpServletRequest request;
    private final JwtConfig jwtConfig;
    private final JwtTokenVerifier jwtTokenVerifier;

    public UserController(
            UserService userService,
            AuthenticationManager authenticationManager,
            HttpServletRequest request,
            JwtConfig jwtConfig,
            JwtTokenVerifier jwtTokenVerifier) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.request = request;
        this.jwtConfig = jwtConfig;
        this.jwtTokenVerifier = jwtTokenVerifier;
    }

    @ApiResponse(code = 200, message = "Successfully retrieved a user")
    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable("userId") Integer userId) {
        return userService.findAllUsers()
                .stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User with ID " + userId + " does not exist"));
    }

    @ApiResponse(code = 200, message = "Successfully updated a user")
    @PatchMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('client:write')")
    public void deleteMyAccount(@PathVariable("userId") Integer userId){
        // change isDeleted property to true with PATCH mode
        System.out.println("deleteMyAccount account of user with ID " + userId);
        System.out.println(userId);
    }

    @ApiResponse(code = 200, message = "Successfully updated a user")
    @PutMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('client:write')")
    public UserDTO updateUser(@PathVariable("userId") Integer userId, @RequestBody UserDTO userDTO) throws Exception {

        if(!canUpdateUser(userId))
            throw new IllegalStateException("You are not authorized to update user with ID " + userId);

        return this.userService
                .updateUser(userId, userDTO)
                .orElseThrow(() -> new IllegalStateException(String.format("user with ID %s cannot found", userId)));
    }

    /**
     * Method that checks if the present user can perform this operation by checking these permissions from their token
     * @param userId userId to modify or delete
     * @return boolean
     * */
    private boolean canUpdateUser(Integer userId) {
        // get headers informations
        String token = jwtTokenVerifier.extractJwtToken(request);
        DecodedJWT decodeJWTToken = jwtTokenVerifier.decodeJWT(token, jwtConfig.getSecretKey());

        // Decode a token
        String username = decodeJWTToken.getSubject();
        UserDetailsDTO userDetails = (UserDetailsDTO) userService.loadUserByUsername(username);
        int idFromToken = userDetails.getCredentials().getId();

        // retrieve claims "authorities" from payload of token
        List<Map> authorities = decodeJWTToken.getClaims().get("authorities").asList(Map.class);
        Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.get("authority").toString()))
                .collect(Collectors.toSet());

        // Check if the user has the required authorization for this request and if the ID matches userID
        return grantedAuthorities.contains(new SimpleGrantedAuthority("client:write")) && idFromToken == userId;

    }
}
