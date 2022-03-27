package ca.ghostteam.springulart.controller.user;

import ca.ghostteam.springulart.dto.SignupDTO;
import ca.ghostteam.springulart.dto.UserDTO;
import ca.ghostteam.springulart.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-19
 */
@RestController
@RequestMapping("management/api/v1/users")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserManagementController {

    private final UserService userService;

    public UserManagementController(UserService userService) {
        this.userService = userService;
    }

    @ApiResponse(code = 200, message = "Successfully retrieved all users")
    @GetMapping()
    public List<UserDTO> getUsers() {
        return userService.findAllUsers();
    }

    @ApiResponse(code = 200, message = "Successfully retrieved a user")
    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable("userId") Integer userId) {
        return userService
                .findAllUsers()
                .stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User with ID " + userId + " does not exist"));
    }


    @ApiResponses(value = {
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400, message = "Bad Request"),
    })
    @PostMapping()
    public UserDTO registerNewUser(@RequestBody SignupDTO signupDTO) throws Exception {
        // check if user already exists
        if(userService.existsUserByEmail(signupDTO.getEmail()))
            throw new IllegalStateException("User already exists");

        return userService
                .saveUser(signupDTO)
                .orElseThrow(() -> new IllegalStateException("User not registered"));
    }

    @ApiResponse(code = 204, message = "Successfully Deleted a user")
    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId){
        userService.deleteUserById(userId);
    }


    @ApiResponse(code = 200, message = "Successfully updated a user")
    @PutMapping(path = "{userId}")
    public UserDTO updateUser(@PathVariable("userId") Integer userId, @RequestBody UserDTO userDTO) throws Exception {
        return this.userService
                .updateUser(userId, userDTO)
                .orElseThrow(() -> new IllegalStateException(String.format("User with ID %s cannot found", userId)));
    }

}
