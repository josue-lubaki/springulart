//package ca.ghostteam.springulart.dao.impl;
//
//import ca.ghostteam.springulart.model.AddressModel;
//import ca.ghostteam.springulart.model.UserModel;
//import ca.ghostteam.springulart.dao.UserDao;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
///**
// * @author Josue Lubaki
// * @version 1.0
// * @since 2022-03-19
// */
//@Slf4j
//@Repository
//public class InMemoryUserDaoImpl implements UserDao {
//
//    private final List<UserModel> LIST_USERS = new ArrayList<>();
//    private final PasswordEncoder passwordEncoder;
//
//    public InMemoryUserDaoImpl(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//        this.initUsers();
//    }
//
//    @Override
//    public Optional<UserModel> findUserById(Integer id) {
//        return LIST_USERS
//                .stream()
//                .filter(user -> user.getId().equals(id))
//                .findFirst();
//    }
//
//    @Override
//    public Optional<UserModel> selectUserByUsername(String username) {
//        return getAllUsers().stream()
//                .filter(user -> username.equals(user.getEmail()))
//                .findFirst();
//    }
//
//    @Override
//    public boolean existsByEmail(String email) {
//        return getAllUsers()
//                .stream()
//                .anyMatch(user -> email.equals(user.getEmail()));
//    }
//
//    @Override
//    public Optional<UserModel> update(Integer id, UserModel userModel) {
//       //get old userModel by id
//        UserModel oldUserModel = LIST_USERS
//            .stream()
//            .filter(user -> user.getId().equals(id))
//            .findFirst()
//            .get();
//
//        //get index
//        int index = LIST_USERS.indexOf(oldUserModel);
//
//        // set old password
//        userModel.setPassword(oldUserModel.getPassword());
//
//        // set old ID
//        userModel.setId(oldUserModel.getId());
//
//        //set the old userModel with the new one
//        LIST_USERS.set(index,userModel);
//        return Optional.of(LIST_USERS.get(index));
//    }
//
//    @Override
//    public void deleteById(Integer id) {
//        UserModel userModel=
//                LIST_USERS
//                .stream()
//                .filter(user -> user.getId().equals(id))
//                .findFirst()
//                .orElse(null);
//
//        LIST_USERS.remove(userModel);
//
//    }
//
//    @Override
//    public List<UserModel> findAllUsers() {
//        return getAllUsers();
//    }
//
//    @Override
//    public Optional<UserModel> save(UserModel userModel) {
//        LIST_USERS.add(userModel);
//        return Optional.of(userModel);
//    }
//
//    /**
//     * get all users
//     * @return List<UserModel>
//     * */
//    private List<UserModel> getAllUsers(){
//        return LIST_USERS;
//    }
//
//    // sample data
//    private void initUsers() {
//        UserModel user1 = new UserModel();
//        AddressModel addressModel1 = new AddressModel();
//        // set user information
//        user1.setId(LIST_USERS.size() + 1);
//        user1.setFname("Ismael");
//        user1.setLname("Coulibaly");
//        user1.setEmail("ismaelcoulibaly@gmail.com");
//        user1.setRole("ROLE_BARBER");
//        user1.setPassword(passwordEncoder.encode("Ismael2022"));
//        user1.setDob(LocalDate.of(1981, 6, 13));
//        user1.setImageURL("https://static.wikia.nocookie.net/marvel-cinematic/images/3/32/Steve_Rogers_2.jpg");
//        user1.setPhone("+1 873 873 8738");
//        // set address information
//        addressModel1.setApartement("3");
//        addressModel1.setStreet("25 Rue de Caillière");
//        addressModel1.setCity("Trois-Rivières");
//        addressModel1.setZip("G8W 1B5");
//        addressModel1.setState("Québec");
//        user1.setAddress(addressModel1);
//        // add user
//        LIST_USERS.add(user1);
//
//        UserModel user2 = new UserModel();
//        AddressModel addressModel2 = new AddressModel();
//        // set user information
//        user2.setId(LIST_USERS.size() + 1);
//        user2.setFname("Josue");
//        user2.setLname("Lubaki");
//        user2.setEmail("josuelubaki@gmail.com");
//        user2.setPassword(passwordEncoder.encode("Josue2022"));
//        user2.setRole("ROLE_CLIENT");
//        user2.setDob(LocalDate.of(1964, 9, 2));
//        user2.setImageURL("https://assets-prd.ignimgs.com/2020/08/06/john-wick-button-1596757524663.jpg");
//        user2.setPhone("+1 873 873 8738");
//        // set address information
//        addressModel2.setApartement("101");
//        addressModel2.setStreet("3100 Boulevard des Forges");
//        addressModel2.setCity("Trois-Rivières");
//        addressModel2.setZip("G8Z 1V5");
//        addressModel2.setState("Québec");
//        user2.setAddress(addressModel2);
//        // add user
//        LIST_USERS.add(user2);
//
//        UserModel user3 = new UserModel();
//        AddressModel addressModel3 = new AddressModel();
//        // set user information
//        user3.setId(LIST_USERS.size() + 1);
//        user3.setFname("Jonathan");
//        user3.setLname("Kanyinda");
//        user3.setEmail("jonathankanyinda@gmail.com");
//        user3.setPassword(passwordEncoder.encode("Jonathan2022"));
//        user3.setRole("ROLE_ADMIN");
//        user3.setDob(LocalDate.of(1965, 4, 4));
//        user3.setImageURL("https://static.wikia.nocookie.net/marvelcentral/images/4/4a/Tony-Stark-iron-man-11234572-1485-2061.jpg");
//        user3.setPhone("+1 873 873 8738");
//        // set address information
//        addressModel3.setApartement("1B");
//        addressModel3.setStreet("1280 Rue de Terrière");
//        addressModel3.setCity("Trois-Rivières");
//        addressModel3.setZip("G8Z 3K2");
//        addressModel3.setState("Québec");
//        user3.setAddress(addressModel3);
//        // add user
//        LIST_USERS.add(user3);
//    }
//}
