package ca.ghostteam.springulart.repository;

import ca.ghostteam.springulart.model.AddressModel;
import ca.ghostteam.springulart.model.CredentialModel;
import ca.ghostteam.springulart.model.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-21
 */
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CredentialRepository credentialRepository;

    @Test
    void findAll() {
        // given
        UserModel userExcepted = new UserModel();
        userExcepted.setEmail("josuelubaki@gmail.com");
        userExcepted.setPassword("password");

        AddressModel address = new AddressModel();
        address.setApartement("123");
        address.setCity("Vancouver");
        address.setStreet("123 Main Street");
        address.setZip("V6G 1G8");
        address.setState("BC");
        address.setUsers(new HashSet<>());
        userExcepted.setAddress(address);

        // save address
        this.addressRepository.save(address);
        this.userRepository.save(userExcepted);

        // when
        ArrayList<UserModel> users = new ArrayList<>(this.userRepository.findAll());

        // then
        assertEquals(1, users.size());
    }

    /**
     * Method under test: {@link UserRepository#findUserModelByEmail(String)}
     */
    @Test
    void findUserModelByEmail() {
        // given
        UserModel userExcepted = new UserModel();
        userExcepted.setEmail("josuelubaki@gmail.com");
        userExcepted.setPassword("password");

        AddressModel address = new AddressModel();
        address.setApartement("123");
        address.setCity("Vancouver");
        address.setStreet("123 Main Street");
        address.setZip("V6G 1G8");
        address.setState("BC");
        address.setUsers(new HashSet<>());
        userExcepted.setAddress(address);

        this.addressRepository.save(address);
        this.userRepository.save(userExcepted);

        // when
        UserModel user = this.userRepository.findUserModelByEmail(userExcepted.getEmail()).get();

        // then
        assertEquals(userExcepted, user);
    }

    /**
     * Method under test: {@link UserRepository#existsByEmail(String)}
     */
    @Test
    void existsByEmail() {
        // given
        UserModel userExcepted = new UserModel();
        userExcepted.setEmail("josuelubaki@gmail.com");
        userExcepted.setPassword("password");

        AddressModel address = new AddressModel();
        address.setApartement("123");
        address.setCity("Vancouver");
        address.setStreet("123 Main Street");
        address.setZip("V6G 1G8");
        address.setState("BC");
        address.setUsers(new HashSet<>());
        userExcepted.setAddress(address);

        this.addressRepository.save(address);
        this.userRepository.save(userExcepted);

        // when
        boolean exists = this.userRepository.existsByEmail(userExcepted.getEmail());

        // then
        assertTrue(exists);
    }

    /**
     * Method under test: {@link UserRepository#findById(Long)}
     */
    @Test
    void findById() {
        // given
        UserModel userExcepted = new UserModel();
        userExcepted.setEmail("josuelubaki@gmail.com");
        userExcepted.setPassword("password");

        AddressModel address = new AddressModel();
        address.setApartement("123");
        address.setCity("Vancouver");
        address.setStreet("123 Main Street");
        address.setZip("V6G 1G8");
        address.setState("BC");
        address.setUsers(new HashSet<>());
        userExcepted.setAddress(address);

        this.addressRepository.save(address);
        this.userRepository.save(userExcepted);

        // when
        UserModel user = this.userRepository.findById(userExcepted.getId()).get();

        // then
        assertEquals(userExcepted, user);
    }

    /**
     * Method under test: {@link UserRepository#updatePassword(String, String)}
     */
    @Transactional
    @Test
    void updatePassword() {
        // given
        UserModel userExcepted = new UserModel();
        userExcepted.setEmail("josuelubaki@gmail.com");
        userExcepted.setPassword("password");

        AddressModel address = new AddressModel();
        address.setApartement("123");
        address.setCity("Vancouver");
        address.setStreet("123 Main Street");
        address.setZip("V6G 1G8");
        address.setState("BC");
        address.setUsers(new HashSet<>());
        userExcepted.setAddress(address);

        this.addressRepository.save(address);
        this.userRepository.save(userExcepted);


        // when
        this.userRepository.updatePassword(userExcepted.getEmail(), "newPassword");
        // find all users
        List<UserModel> users = this.userRepository.findAll();

        // then
        UserModel user = this.userRepository.findUserModelByEmail(userExcepted.getEmail()).get();
        assertEquals("newPassword", user.getPassword());
    }

    /**
     * Method under test: {@link UserRepository#findAll()}
     */
    @Test
    void testFindAll() {
        UserModel userModel = new UserModel();

        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setUsers(new HashSet<>());
        addressModel.setZip("21654");
        userModel.setAddress(addressModel);
        userModel.setCreated(LocalDate.ofEpochDay(1L));
        LocalDate created = LocalDate.ofEpochDay(1L);

        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setCreated(created);
        credentialModel.setGrantedAuthority("ROLE_CLIENT");
        credentialModel.setUsername("jane.doe@example.org");
        credentialModel.setPassword("iloveyou");
        credentialModel.setUpdated(LocalDate.ofEpochDay(1L));

        credentialModel.setUser(userModel);
        userModel.setCredential(credentialModel);

        userModel.setDeleted(true);
        userModel.setDob(LocalDate.ofEpochDay(1L));
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setReservationModelBarber(new ArrayList<>());
        userModel.setReservationModelClient(new ArrayList<>());
        userModel.setRole("ROLE_CLIENT");
        userModel.setUpdated(LocalDate.ofEpochDay(1L));
        this.addressRepository.save(addressModel);
        this.credentialRepository.save(credentialModel);
        this.userRepository.save(userModel);

        UserModel userModel3 = new UserModel();
        userModel3.setCreated(LocalDate.ofEpochDay(1L));

        AddressModel addressModel3 = new AddressModel();
        addressModel3.setApartement("Apartement");
        addressModel3.setCity("Oxford");
        addressModel3.setState("MD");
        addressModel3.setStreet("Street");
        addressModel3.setUsers(new HashSet<>());
        addressModel3.setZip("21654");
        userModel3.setAddress(addressModel3);
        LocalDate created3 = LocalDate.ofEpochDay(1L);

        CredentialModel credentialModel3 = new CredentialModel();
        credentialModel3.setCreated(created3);
        credentialModel3.setGrantedAuthority("ROLE_CLIENT");
        credentialModel3.setPassword("iloveyou");
        credentialModel3.setUpdated(LocalDate.ofEpochDay(1L));

        credentialModel3.setUsername("jane2.doe@example.org");
        userModel3.setCredential(credentialModel3);

        userModel3.setDeleted(true);
        userModel3.setDob(LocalDate.ofEpochDay(1L));
        userModel3.setEmail("jane2.doe@example.org");
        userModel3.setFname("Fname");
        userModel3.setImageURL("https://example.org/example");
        userModel3.setLname("Lname");
        userModel3.setPassword("iloveyou");
        userModel3.setPhone("4105551212");
        userModel3.setReservationModelBarber(new ArrayList<>());
        userModel3.setReservationModelClient(new ArrayList<>());
        userModel3.setRole("ROLE_CLIENT");
        userModel3.setUpdated(LocalDate.ofEpochDay(1L));
        this.addressRepository.save(addressModel3);
        this.credentialRepository.save(credentialModel3);
        this.userRepository.save(userModel3);

        assertEquals(2, this.userRepository.findAll().size());
    }

}