package ca.ghostteam.springulart.controller.user;

import ca.ghostteam.springulart.dto.SignupDTO;
import ca.ghostteam.springulart.dto.UserDTO;
import ca.ghostteam.springulart.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
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

    @GetMapping()
    public List<UserDTO> getUsers() {
        return userService.findAllUsers();
    }

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

        if(userService.findUserByEmail(signupDTO.getEmail()))
            throw new IllegalStateException("User already exists");

        return userService
                .saveUser(signupDTO)
                .orElseThrow(() -> new IllegalStateException("User not registered"));

    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId){
        System.out.println("deleteUser a user with ID " + userId);
        System.out.println(userId);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable("userId") Integer userId, @RequestBody UserDTO userDTO){
        System.out.println("updateUser a user ID " + userId);
        System.out.printf("%s %s\n", userId, userDTO);
    }

}
