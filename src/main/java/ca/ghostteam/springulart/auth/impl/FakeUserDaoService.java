package ca.ghostteam.springulart.auth.impl;

import ca.ghostteam.springulart.auth.UserDao;
import ca.ghostteam.springulart.dto.UserDTO;
import ca.ghostteam.springulart.model.Address;
import ca.ghostteam.springulart.model.Credential;
import ca.ghostteam.springulart.model.User;
import ca.ghostteam.springulart.security.ApplicationUserRole;
import com.google.common.collect.Lists;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-19
 */
@Repository("fake")
public class FakeUserDaoService implements UserDao {

    private final PasswordEncoder passwordEncoder;

    public FakeUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<UserDTO> selectUserByUsername(String username) {
        return getAllUsers().stream()
                .filter(user -> username.equals(user.getCredentials().getUsername()))
                .findFirst();
    }

    private List<UserDTO> getAllUsers(){
        return Lists.newArrayList(
                new UserDTO(
                        new Credential(
                            1,
                            "ismaelcoulibaly@gmail.com",
                            passwordEncoder.encode("password"),
                            ApplicationUserRole.BARBER.getGrantedAuthorities(),
                            LocalDateTime.now(),
                            LocalDateTime.now()
                        ),
                        new User(
                            1,
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
                        true,
                        true,
                        true,
                        true
                ),
                new UserDTO(
                        new Credential(
                                2,
                                "josuelubaki@gmail.com",
                                passwordEncoder.encode("password"),
                                ApplicationUserRole.CLIENT.getGrantedAuthorities(),
                                LocalDateTime.now(),
                                LocalDateTime.now()
                        ),
                        new User(
                                2,
                                "Josue",
                                "Lubaki",
                                "josuelubaki@gmail.com",
                                "https://assets-prd.ignimgs.com/2020/08/06/john-wick-button-1596757524663.jpg",                                "+1 873 873 8738",
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
                        true,
                        true,
                        true,
                        true
                ),
                new UserDTO(
                        new Credential(
                                3,
                                "jonathankanyanda@gmail.com",
                                passwordEncoder.encode("password"),
                                ApplicationUserRole.ADMIN.getGrantedAuthorities(),
                                LocalDateTime.now(),
                                LocalDateTime.now()
                        ),
                        new User(
                                3,
                                "Jonathan",
                                "Kanyinda",
                                "jonathankanyanda@gmail.com",
                                "https://static.wikia.nocookie.net/marvelcentral/images/4/4a/Tony-Stark-iron-man-11234572-1485-2061.jpg",                                "+1 873 873 8738",
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
                        ),
                        true,
                        true,
                        true,
                        true
                )
        );
    }
}
