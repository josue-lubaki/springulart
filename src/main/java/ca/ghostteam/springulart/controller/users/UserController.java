package ca.ghostteam.springulart.controller.users;

import ca.ghostteam.springulart.model.AddressModel;
import ca.ghostteam.springulart.model.UserModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-19
 */
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    // samples data
    private static final List<UserModel> USER_MODELS = List.of(
            new UserModel(1,
                    "Ismael",
                    "Coulibaly",
                    "ismaelcoulibaly@gmail.com",
                    "https://static.wikia.nocookie.net/marvel-cinematic/images/3/32/Steve_Rogers_2.jpg",
                    "+1 873 873 8738",
                    LocalDateTime.now(),
                    new AddressModel(
                            "25 Rue de Caillière",
                            "3",
                            "G8W 1B5",
                            "Trois-Rivières",
                            "Québec"
                    ),
                    LocalDateTime.now(),
                    LocalDateTime.now()
            ),
            new UserModel(2,
                    "Josue",
                    "Lubaki",
                    "josuelubaki@gmail.com",
                    "https://assets-prd.ignimgs.com/2020/08/06/john-wick-button-1596757524663.jpg",
                    "+1 873 873 8738",
                    LocalDateTime.now(),
                    new AddressModel(
                            "3100 Boulevard des Forges",
                            "101",
                            "G8Z 1V5",
                            "Trois-Rivières",
                            "Québec"
                    ),
                    LocalDateTime.now(),
                    LocalDateTime.now()
            ),
            new UserModel(3,
                    "Jonathan",
                    "Kanyinda",
                    "jonathankanyinda@gmail.com",
                    "https://static.wikia.nocookie.net/marvelcentral/images/4/4a/Tony-Stark-iron-man-11234572-1485-2061.jpg",
                    "+1 873 873 8738",
                    LocalDateTime.now(),
                    new AddressModel(
                            "1280 Rue de Terrière",
                            "1B",
                            "G8Z 3K2",
                            "Trois-Rivières",
                            "Québec"
                    ),
                    LocalDateTime.now(),
                    LocalDateTime.now()
            )

    );

    @GetMapping()
    public List<UserModel> getUsers() {
        return USER_MODELS;
    }

    @GetMapping("/{userId}")
    public UserModel getUser(@PathVariable("userId") Integer userId) {
        return USER_MODELS.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("UserModel " + userId + " does not exist"));
    }

    @PostMapping()
    public void registerNewUser(@RequestBody UserModel userModel){
        System.out.println("registerNewUser a userModel");
        System.out.println(userModel);
    }

    @DeleteMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('client:write')")
    public void deleteUser(@PathVariable("userId") Integer userId){
        System.out.println("deleteUser a user");
        System.out.println(userId);
    }

    @PutMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('client:write')")
    public void updateUser(@PathVariable("userId") Integer userId, @RequestBody UserModel userModel){
        System.out.println("updateUser a userModel");
        System.out.printf("%s %s\n", userId, userModel);
    }
}
