package ca.ghostteam.springulart.controller.user;

import ca.ghostteam.springulart.dto.RegisterDTO;
import ca.ghostteam.springulart.dto.SignupDTO;
import ca.ghostteam.springulart.dto.UserDTO;
import ca.ghostteam.springulart.dto.UserUpdateDTO;
import ca.ghostteam.springulart.service.file.FileService;
import ca.ghostteam.springulart.service.user.UserService;
import ca.ghostteam.springulart.tools.UtilsUser;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private final FileService awss3ServiceImpl;

    public UserManagementController(UserService userService,
                                    FileService awss3ServiceImpl) {
        this.userService = userService;
        this.awss3ServiceImpl = awss3ServiceImpl;
    }

    @ApiResponse(code = 200, message = "Successfully retrieved all users")
    @ApiOperation(value = "Get all users")
    @GetMapping()
    public List<UserDTO> getUsers() {
        return userService.findAllUsers();
    }

    @ApiResponse(code = 200, message = "Successfully retrieved a user")
    @ApiOperation(value = "Get a user by ID")
    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable("userId") Long userId) {
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
    @ApiOperation(value = "Create a new user")
    @PostMapping()
    public UserDTO registerUser(@ModelAttribute RegisterDTO registerDTO) throws Exception {

        // if user already exists
        if (userService.existsUserByEmail(registerDTO.getEmail()))
            throw new IllegalStateException("User already exists");

        // check role of user, if null, put ROLE_CLIENT as default
        if (registerDTO.getRole() == null)
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

    @ApiResponse(code = 204, message = "Successfully Deleted a user")
    @ApiOperation(value = "Delete a user by ID")
    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUserById(userId);
    }


    @ApiResponse(code = 200, message = "Successfully updated a user")
    @ApiOperation(value = "Update a user by ID")
    @PutMapping(path = "{userId}")
    public UserDTO updateUser(@PathVariable("userId") Long userId, @ModelAttribute UserUpdateDTO userUpdateDTO) throws Exception {

        return this.userService
                .updateUser(userId, userUpdateDTO)
                .orElseThrow(() -> new IllegalStateException(String.format("User with ID %s cannot found", userId)));

    }


}
