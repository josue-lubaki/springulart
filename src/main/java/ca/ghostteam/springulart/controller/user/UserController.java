package ca.ghostteam.springulart.controller.user;

import ca.ghostteam.springulart.config.bean.JwtConfig;
import ca.ghostteam.springulart.dto.UserDTO;
import ca.ghostteam.springulart.dto.UserDetailsDTO;
import ca.ghostteam.springulart.dto.UserUpdateDTO;
import ca.ghostteam.springulart.security.jwt.filter.JwtTokenVerifier;
import ca.ghostteam.springulart.service.user.UserService;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    private final HttpServletRequest request;
    private final JwtConfig jwtConfig;
    private final JwtTokenVerifier jwtTokenVerifier;

    public UserController(
            UserService userService,
            HttpServletRequest request,
            JwtConfig jwtConfig,
            JwtTokenVerifier jwtTokenVerifier) {
        this.userService = userService;
        this.request = request;
        this.jwtConfig = jwtConfig;
        this.jwtTokenVerifier = jwtTokenVerifier;
    }

    @ApiResponse(code = 200, message = "Successfully retrieved a user")
    @ApiOperation(value = "Get a user by ID")
    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable("id") Long userId) {
        return userService
                .findUserById(userId)
                .orElseThrow(() -> new IllegalStateException("User with ID " + userId + " does not exist"));
    }

    @ApiResponse(code = 204, message = "Successfully Deleted a user")
    @ApiOperation(value = "Delete a user by ID")
    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasAuthority('client:write')")
    public void deleteMyAccount(@PathVariable("id") Long userId){
        // check if user has permission to do that
        if(isGrantedToPeformThisOperation(userId))
            throw new IllegalStateException("You are not authorized to delete user with ID " + userId);

        this.userService.deleteUserById(userId);
    }

    @ApiResponse(code = 200, message = "Successfully updated a user")
    @ApiOperation(value = "Update a user by ID")
    @PutMapping(path = "{id}")
    @PreAuthorize("hasAuthority('client:write')")
    public UserDTO updateUser(@PathVariable("id") Long userId, @ModelAttribute UserUpdateDTO userUpdateDTO) throws Exception {
        // check if user has permission to do that
        if(isGrantedToPeformThisOperation(userId))
            throw new IllegalStateException("You are not authorized to update user with ID " + userId);

        return this.userService
                .updateUser(userId, userUpdateDTO)
                .orElseThrow(() -> new IllegalStateException(String.format("user with ID %s cannot found", userId)));

    }

    /**
     * Method that checks if the present user can perform this operation by checking these permissions from their token
     * @param userId userId to modify or delete
     * @return boolean
     * */
    private boolean isGrantedToPeformThisOperation(Long userId) {
        // get headers informations
        String token = jwtTokenVerifier.extractJwtToken(request);
        DecodedJWT decodeJWTToken = jwtTokenVerifier.decodeJWT(token, jwtConfig.getSecretKey());

        // Decode a token
        String username = decodeJWTToken.getSubject();
        UserDetailsDTO userDetails = (UserDetailsDTO) userService.loadUserByUsername(username);
        long idFromToken = userDetails.getCredentials().getId();

        // retrieve claims "authorities" from payload of token
        List<Map> authorities = decodeJWTToken.getClaims().get("authorities").asList(Map.class);
        Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.get("authority").toString()))
                .collect(Collectors.toSet());

        // Check if the user has the required authorization for this request and if the ID matches userID
        return !grantedAuthorities.contains(new SimpleGrantedAuthority("client:write")) || idFromToken != userId;
    }
}
