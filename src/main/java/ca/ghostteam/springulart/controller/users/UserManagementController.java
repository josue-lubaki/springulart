package ca.ghostteam.springulart.controller.users;

import ca.ghostteam.springulart.model.Address;
import ca.ghostteam.springulart.model.User;
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
@RequestMapping("management/api/v1/users")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserManagementController {

    // samples data
    private static final List<User> USERS = List.of(
            new User(1,
                    "Ismael",
                    "Coulibaly",
                    "ismaelcoulibaly@gmail.com",
                    "https://static.wikia.nocookie.net/marvel-cinematic/images/3/32/Steve_Rogers_2.jpg",
                    "+1 873 873 8738",
                    LocalDateTime.now(),
                    new Address(
                            "25 Rue de Caillière",
                            "3",
                            "G8W 1B5",
                            "Trois-Rivières",
                            "Québec"
                    ),
                    LocalDateTime.now(),
                    LocalDateTime.now()
            ),
            new User(2,
                    "Josue",
                    "Lubaki",
                    "josuelubaki@gmail.com",
                    "https://assets-prd.ignimgs.com/2020/08/06/john-wick-button-1596757524663.jpg",
                    "+1 873 873 8738",
                    LocalDateTime.now(),
                    new Address(
                            "3100 Boulevard des Forges",
                            "101",
                            "G8Z 1V5",
                            "Trois-Rivières",
                            "Québec"
                    ),
                    LocalDateTime.now(),
                    LocalDateTime.now()
            ),
            new User(3,
                    "Jonathan",
                    "Kanyinda",
                    "jonathankanyinda@gmail.com",
                    "https://static.wikia.nocookie.net/marvelcentral/images/4/4a/Tony-Stark-iron-man-11234572-1485-2061.jpg",
                    "+1 873 873 8738",
                    LocalDateTime.now(),
                    new Address(
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
    public List<User> getUsers() {
        return USERS;
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") Integer userId) {
        return USERS.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User " + userId + " does not exist"));
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user){
        System.out.println("registerNewUser a user");
        System.out.println(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId){
        System.out.println("deleteUser a user");
        System.out.println(userId);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable("userId") Integer userId, @RequestBody User user){
        System.out.println("updateUser a user");
        System.out.printf("%s %s\n", userId, user);
    }

}
