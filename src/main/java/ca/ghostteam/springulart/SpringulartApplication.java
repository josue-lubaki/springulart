package ca.ghostteam.springulart;

import ca.ghostteam.springulart.dto.HaircutDTO;
import ca.ghostteam.springulart.dto.SignupDTO;
import ca.ghostteam.springulart.model.AddressModel;
import ca.ghostteam.springulart.service.HaircutService;
import ca.ghostteam.springulart.service.ReservationService;
import ca.ghostteam.springulart.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
@ConfigurationPropertiesScan
@Slf4j
public class SpringulartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringulartApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService,
                          HaircutService haircutService,
                          ReservationService reservationService){
        return args -> {
            initHaircuts(haircutService);
            initUsers(userService);
            log.info("Nombre Reservations en cours " + reservationService.count());
        };
    }

    // sample data
    private void initUsers(UserService userService) throws Exception {
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
    }
    private void initHaircuts(HaircutService haircutService){
        // create fake haircuts
        HaircutDTO haircutModel = new HaircutDTO();
        // generate UUID for each haircut
        haircutModel.setId(null);
        haircutModel.setTitle("Coupe Beckham");
        haircutModel.setDescription("Ce chic \"do conserve une finition soignée sur les bords, tandis que la longueur plus longue à travers le haut est ramenée vers l\"arrière pour un look et une sensation classiques");
        haircutModel.setPrice(120);
        haircutModel.setEstimatedTime("10 min");
        haircutModel.setImageURL("https://hairstyles.thehairstyler.com/hairstyle_views/front_view_images/11732/original/David-Beckham.jpg");
        haircutService.saveHaircut(haircutModel);

        HaircutDTO haircutModel1 = new HaircutDTO();
        haircutModel1.setId(null);
        haircutModel1.setTitle("360 Waves");
        haircutModel1.setDescription("La coupe de cheveux des vagues est une coupe à la mode. Pour des vagues complètes à 360 °, obtenez un fondu effilé qui ne coupe que les favoris et le décolleté");
        haircutModel1.setPrice(100);
        haircutModel1.setEstimatedTime("50 min");
        haircutModel1.setImageURL( "https://www.menshairstyletrends.com/wp-content/uploads/2020/12/Taper-Fade-with-Waves-braidsmasterdorian.jpg");
        haircutService.saveHaircut(haircutModel1);

        HaircutDTO haircutModel2 = new HaircutDTO();
        haircutModel2.setId(null);
        haircutModel2.setTitle("Jin's Messy");
        haircutModel2.setDescription("Même si le membre du BTS peut être connu pour ses teintures capillaires colorées, nous le voyons ici avec un look plus naturel et réservé qui favorise ses traits.");
        haircutModel2.setPrice(60);
        haircutModel2.setEstimatedTime("20 min");
        haircutModel2.setImageURL("https://haircutinspiration.com/wp-content/uploads/Jins-Messy-Curtain-Hair-e1535448501869.jpg");
        haircutService.saveHaircut(haircutModel2);

        HaircutDTO haircutModel3 = new HaircutDTO();
        haircutModel3.setId(null);
        haircutModel3.setTitle("dread locks");
        haircutModel3.setDescription("Assez souvent, les gars trouvent qu\"il est beaucoup plus facile de gérer les dreads courtes que les longues. Dread lock est un beau style qui nécessite peu d\"entretien");
        haircutModel3.setPrice(80);
        haircutModel3.setEstimatedTime("45 min");
        haircutModel3.setImageURL("https://haircutinspiration.com/wp-content/uploads/Jins-Messy-Curtain-Hair-e1535448501869.jpg");
        haircutService.saveHaircut(haircutModel3);

        HaircutDTO haircutModel4 = new HaircutDTO();
        haircutModel4.setId(null);
        haircutModel4.setTitle("Mohawk Burst Fade");
        haircutModel4.setDescription("Après tout, le fondu mohawk éclaté est une coupe de cheveux élégante et flatteuse lorsqu\"il est fait correctement. Le burst fade mohawk, également connu sous le nom de fondu du sud de la France");
        haircutModel4.setPrice(35);
        haircutModel4.setEstimatedTime("25 min");
        haircutModel4.setImageURL( "https://www.menshairstylesnow.com/wp-content/uploads/2020/06/Odell-Beckham-Jr-Haircut.jpg");
        haircutService.saveHaircut(haircutModel4);

        HaircutDTO haircutModel5 = new HaircutDTO();
        haircutModel5.setId(null);
        haircutModel5.setTitle("La coupe à la new-yorkaise");
        haircutModel5.setDescription("La coupe de cheveux homme mi long la plus répandue ces derniers temps est la coupe Undercut, similaire de la coupe pompadour. Cette coupe de cheveux est tendance.");
        haircutModel5.setPrice(45);
        haircutModel5.setEstimatedTime("35 min");
        haircutModel5.setImageURL("https://i.pinimg.com/564x/17/bb/8d/17bb8d423273c7ee8ea3849d94c6692e.jpg");
        haircutService.saveHaircut(haircutModel5);

        HaircutDTO haircutModel6 = new HaircutDTO();
        haircutModel6.setId(null);
        haircutModel6.setTitle("Coupe Undercut");
        haircutModel6.setDescription("Cette coupe représente une coupe carrée, mais un peu plus longue que le modèle classique. cette coiffure est travaillée sur deux longueurs, révélant des côtés courts et une nuque dégagée ainsi qu\"une masse capillaire");
        haircutModel6.setPrice(50);
        haircutModel6.setEstimatedTime("20 min");
        haircutModel6.setImageURL("https://archzine.fr/wp-content/uploads/2017/10/coiffure-ame%CC%81ricaine-de%CC%81grade%CC%81-coupe-de-cheveux-homme-court-sur-les-cote%CC%81s-long-dessus.jpg");
        haircutService.saveHaircut(haircutModel6);

        HaircutDTO haircutModel7 = new HaircutDTO();
        haircutModel7.setId(null);
        haircutModel7.setTitle("Coupe Dégradé");
        haircutModel7.setDescription("Cette coupe représente une coupe carrée, mais un peu plus longue que le modèle classique. cette coiffure est travaillée sur deux longueurs, révélant des côtés courts et une nuque dégagée ainsi qu\"une masse capillaire");
        haircutModel7.setPrice(75);
        haircutModel7.setEstimatedTime("45 min");
        haircutModel7.setImageURL("https://guidelook.fr/wp-content/uploads/2020/06/degrade-homme-curly.jpg");
        haircutService.saveHaircut(haircutModel7);
    }

}
