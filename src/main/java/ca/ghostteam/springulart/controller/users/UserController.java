package ca.ghostteam.springulart.controller.users;

import ca.ghostteam.springulart.dto.UserDTO;
import ca.ghostteam.springulart.model.AddressModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-19
 */
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private static final List<UserDTO> USERS = new ArrayList<>();

    static {
        UserDTO user1 = new UserDTO();
        AddressModel addressModel1 = new AddressModel();
        user1.setId(1);
        user1.setFname("Ismael");
        user1.setLname("Coulibaly");
        user1.setEmail("ismaelcoulibaly@gmail.com");
        user1.setRole("ROLE_BARBER");
        user1.setDob(LocalDate.of(1981, 6, 13));
        user1.setImageURL("https://static.wikia.nocookie.net/marvel-cinematic/images/3/32/Steve_Rogers_2.jpg");
        user1.setPhone("+1 873 873 8738");
        addressModel1.setApartement("3");
        addressModel1.setStreet("25 Rue de Caillière");
        addressModel1.setCity("Trois-Rivières");
        addressModel1.setZip("G8W 1B5");
        addressModel1.setState("Québec");
        user1.setAddress(addressModel1);

        UserDTO user2 = new UserDTO();
        AddressModel addressModel2 = new AddressModel();
        user2.setId(2);
        user2.setFname("Josue");
        user2.setLname("Lubaki");
        user2.setEmail("josuelubaki@gmail.com");
        user2.setRole("ROLE_CLIENT");
        user2.setDob(LocalDate.of(1964, 9, 2));
        user2.setImageURL("https://assets-prd.ignimgs.com/2020/08/06/john-wick-button-1596757524663.jpg");
        user2.setPhone("+1 873 873 8738");
        addressModel2.setApartement("101");
        addressModel2.setStreet("3100 Boulevard des Forges");
        addressModel2.setCity("Trois-Rivières");
        addressModel2.setZip("G8Z 1V5");
        addressModel2.setState("Québec");
        user2.setAddress(addressModel2);

        UserDTO user3 = new UserDTO();
        AddressModel addressModel3 = new AddressModel();
        user3.setId(3);
        user3.setFname("Jonathan");
        user3.setLname("Kanyinda");
        user3.setEmail("jonathankanyinda@gmail.com");
        user3.setRole("ROLE_ADMIN");
        user3.setDob(LocalDate.of(1965, 4, 4));
        user3.setImageURL("https://static.wikia.nocookie.net/marvelcentral/images/4/4a/Tony-Stark-iron-man-11234572-1485-2061.jpg");
        user3.setPhone("+1 873 873 8738");
        addressModel3.setApartement("1B");
        addressModel3.setStreet("1280 Rue de Terrière");
        addressModel3.setCity("Trois-Rivières");
        addressModel3.setZip("G8Z 3K2");
        addressModel3.setState("Québec");
        user3.setAddress(addressModel3);

        USERS.add(user1);
        USERS.add(user2);
        USERS.add(user3);
    }

    @GetMapping()
    public List<UserDTO> getUsers() {
        return USERS;
    }

    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable("userId") Integer userId) {
        return USERS.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User with ID " + userId + " does not exist"));
    }

    @PostMapping()
    public void registerNewUser(@RequestBody UserDTO userDTO){
        System.out.println("registerNewUser a user");
        System.out.println(userDTO);
    }

    @DeleteMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('client:write')")
    public void deleteUser(@PathVariable("userId") Integer userId){
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
