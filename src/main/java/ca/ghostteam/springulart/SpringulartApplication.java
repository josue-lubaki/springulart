package ca.ghostteam.springulart;

import ca.ghostteam.springulart.dto.SignupDTO;
import ca.ghostteam.springulart.dto.UserDTO;
import ca.ghostteam.springulart.model.AddressModel;
import ca.ghostteam.springulart.model.UserModel;
import ca.ghostteam.springulart.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
@ConfigurationPropertiesScan
@Slf4j
public class SpringulartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringulartApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
            SignupDTO user1 = new SignupDTO();
            AddressModel addressModel1 = new AddressModel();
            // set user information
            user1.setFname("Ismael");
            user1.setLname("Coulibaly");
            user1.setEmail("ismaelcoulibaly@gmail.com");
            user1.setRole("ROLE_BARBER");
            user1.setPassword("Ismael2022");
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
            // add user
            userService.saveUser(user1);

            SignupDTO user2 = new SignupDTO();
            AddressModel addressModel2 = new AddressModel();
            // set user information
            user2.setFname("Josue");
            user2.setLname("Lubaki");
            user2.setEmail("josuelubaki@gmail.com");
            user2.setPassword("Josue2022");
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
            // add user
            userService.saveUser(user2);

            SignupDTO user3 = new SignupDTO();
            AddressModel addressModel3 = new AddressModel();
            // set user information
            user3.setFname("Jonathan");
            user3.setLname("Kanyinda");
            user3.setEmail("jonathankanyinda@gmail.com");
            user3.setPassword("Jonathan2022");
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
            // add user
            userService.saveUser(user3);
            
//            UserDTO userRetrieved = userService.findUserById(1L).get();
//            log.info("USER RETRIEVED" + userRetrieved.getEmail());
        };
    }

}
