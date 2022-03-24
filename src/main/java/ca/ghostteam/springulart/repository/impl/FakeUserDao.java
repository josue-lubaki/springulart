package ca.ghostteam.springulart.repository.impl;

import ca.ghostteam.springulart.dto.UserDetailsDTO;
import ca.ghostteam.springulart.model.AddressModel;
import ca.ghostteam.springulart.model.CredentialModel;
import ca.ghostteam.springulart.model.UserModel;
import ca.ghostteam.springulart.repository.UserDao;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-19
 */
@Repository("fake-repository")
public class FakeUserDao implements UserDao {

    private final PasswordEncoder passwordEncoder;

    public FakeUserDao(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<UserDetailsDTO> selectUserByUsername(String username) {
        return getAllUsers().stream()
                .filter(user -> username.equals(user.getCredentials().getUsername()))
                .findFirst();
    }

    @Override
    public List<UserDetailsDTO> findAllUsers() {
        return getAllUsers();
    }

    private List<UserDetailsDTO> getAllUsers(){
        List<UserDetailsDTO> listUser = new ArrayList<>();

        CredentialModel credential1 = new CredentialModel();
        UserModel user1 = new UserModel();
        AddressModel addressModel1 = new AddressModel();
        // set user information
        user1.setId(1);
        user1.setFname("Ismael");
        user1.setLname("Coulibaly");
        user1.setEmail("ismaelcoulibaly@gmail.com");
        user1.setRole("ROLE_BARBER");
        user1.setDob(LocalDate.of(1981, 6, 13));
        user1.setImageURL("https://static.wikia.nocookie.net/marvel-cinematic/images/3/32/Steve_Rogers_2.jpg");
        user1.setPhone("+1 873 873 8738");
        // set address information
        addressModel1.setApartement("3");
        addressModel1.setStreet("25 Rue de Caillière");
        addressModel1.setCity("Trois-Rivières");
        addressModel1.setZip("G8W 1B5");
        addressModel1.setState("Québec");
        user1.setAddress(addressModel1);
        // set credential information
        credential1.setId(1);
        credential1.setUsername("ismaelcoulibaly@gmail.com");
        credential1.setPassword(passwordEncoder.encode("Ismael2022"));
        credential1.setGrantedAuthority("ROLE_BARBER");
        // create UserDetailsDTO
        UserDetailsDTO userDetailsDTO1 = new UserDetailsDTO();
        userDetailsDTO1.setUserModel(user1);
        userDetailsDTO1.setCredentials(credential1);
        // add user
        listUser.add(userDetailsDTO1);

        CredentialModel credential2 = new CredentialModel();
        UserModel user2 = new UserModel();
        AddressModel addressModel2 = new AddressModel();
        // set user information
        user2.setId(2);
        user2.setFname("Josue");
        user2.setLname("Lubaki");
        user2.setEmail("josuelubaki@gmail.com");
        user2.setRole("ROLE_CLIENT");
        user2.setDob(LocalDate.of(1964, 9, 2));
        user2.setImageURL("https://assets-prd.ignimgs.com/2020/08/06/john-wick-button-1596757524663.jpg");
        user2.setPhone("+1 873 873 8738");
        // set address information
        addressModel2.setApartement("101");
        addressModel2.setStreet("3100 Boulevard des Forges");
        addressModel2.setCity("Trois-Rivières");
        addressModel2.setZip("G8Z 1V5");
        addressModel2.setState("Québec");
        user2.setAddress(addressModel2);
        // set credential information
        credential2.setId(2);
        credential2.setUsername("josuelubaki@gmail.com");
        credential2.setPassword(passwordEncoder.encode("Josue2022"));
        credential2.setGrantedAuthority("ROLE_CLIENT");
        // create UserDetailsDTO
        UserDetailsDTO userDetailsDTO2 = new UserDetailsDTO();
        userDetailsDTO2.setUserModel(user2);
        userDetailsDTO2.setCredentials(credential2);
        // add user
        listUser.add(userDetailsDTO2);

        CredentialModel credential3 = new CredentialModel();
        UserModel user3 = new UserModel();
        AddressModel addressModel3 = new AddressModel();
        // set user information
        user3.setId(3);
        user3.setFname("Jonathan");
        user3.setLname("Kanyinda");
        user3.setEmail("jonathankanyinda@gmail.com");
        user3.setRole("ROLE_ADMIN");
        user3.setDob(LocalDate.of(1965, 4, 4));
        user3.setImageURL("https://static.wikia.nocookie.net/marvelcentral/images/4/4a/Tony-Stark-iron-man-11234572-1485-2061.jpg");
        user3.setPhone("+1 873 873 8738");
        // set address information
        addressModel3.setApartement("1B");
        addressModel3.setStreet("1280 Rue de Terrière");
        addressModel3.setCity("Trois-Rivières");
        addressModel3.setZip("G8Z 3K2");
        addressModel3.setState("Québec");
        user3.setAddress(addressModel3);
        // set credential information
        credential3.setId(3);
        credential3.setUsername("jonathankanyinda@gmail.com");
        credential3.setPassword(passwordEncoder.encode("Jonathan2022"));
        credential3.setGrantedAuthority("ROLE_ADMIN");
        // create UserDetailsDTO
        UserDetailsDTO userDetailsDTO3 = new UserDetailsDTO();
        userDetailsDTO3.setUserModel(user3);
        userDetailsDTO3.setCredentials(credential3);
        // add user
        listUser.add(userDetailsDTO3);

        return listUser;
    }
}
