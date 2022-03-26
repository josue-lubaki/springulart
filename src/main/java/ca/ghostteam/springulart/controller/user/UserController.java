package ca.ghostteam.springulart.controller.user;

import ca.ghostteam.springulart.dto.SignupDTO;
import ca.ghostteam.springulart.dto.UserDTO;
import ca.ghostteam.springulart.service.UserService;
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

    @GetMapping()
    public List<UserDTO> getUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable("userId") Integer userId) {
        return userService.findAllUsers()
                .stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User with ID " + userId + " does not exist"));
    }

    @PostMapping()
    public void createUser(@RequestBody SignupDTO signupDTO){
        System.out.println("registerNewUser a user");
        System.out.println(signupDTO);
    }

    @PatchMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('client:write')")
    public void deleteUser(@PathVariable("userId") Integer userId){
        // change isDeleted property to true with PATCH mode
        System.out.println("deleteUser a user");
        System.out.println(userId);
    }

    @PutMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('client:write')")
    public void updateUser(@PathVariable("userId") Integer userId, @RequestBody UserDTO userDTO){
        System.out.println("updateUser a user");
        System.out.printf("%s %s\n", userId, userDTO);
    }
}
