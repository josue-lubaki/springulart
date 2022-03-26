package ca.ghostteam.springulart.controller.user;

import ca.ghostteam.springulart.dto.SignupDTO;
import ca.ghostteam.springulart.dto.UserDTO;
import ca.ghostteam.springulart.service.UserService;
import io.swagger.annotations.ApiResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-19
 */
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
    public void updateUser(@PathVariable("userId") Integer userId, @RequestBody UserDTO userDTO){
        System.out.println("updateUser a user");
        System.out.printf("%s %s\n", userId, userDTO);
    }
}
