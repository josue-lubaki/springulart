package ca.ghostteam.springulart.service.user;

import ca.ghostteam.springulart.dto.*;
import ca.ghostteam.springulart.model.AddressModel;
import ca.ghostteam.springulart.model.CredentialModel;
import ca.ghostteam.springulart.model.UserModel;
import ca.ghostteam.springulart.repository.UserRepository;
import ca.ghostteam.springulart.service.address.AddressService;
import ca.ghostteam.springulart.service.credential.CredentialService;
import ca.ghostteam.springulart.service.file.FileService;
import ca.ghostteam.springulart.service.mail.MailService;
import ca.ghostteam.springulart.service.user.impl.UserServiceImpl;
import ca.ghostteam.springulart.tools.UtilsUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private AddressService addressService;

    @MockBean
    private CredentialService credentialService;

    @MockBean
    private FileService fileService;

    @MockBean
    private MailService mailService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @MockBean
    private UtilsUser utilsUser;

    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setId(123L);
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setUsers(new HashSet<>());
        addressModel.setZip("21654");

        UserModel userModel = new UserModel();
        userModel.setAddress(new AddressModel());
        userModel.setCreated(null);
        userModel.setCredential(new CredentialModel());
        userModel.setDeleted(true);
        userModel.setDob(null);
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(123L);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setReservationModelBarber(new ArrayList<>());
        userModel.setReservationModelClient(new ArrayList<>());
        userModel.setRole("Role");
        userModel.setUpdated(null);

        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel.setGrantedAuthority("JaneDoe");
        credentialModel.setId(123L);
        credentialModel.setPassword("iloveyou");
        credentialModel.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel.setUser(userModel);
        credentialModel.setUsername("janedoe");

        UserModel userModel1 = new UserModel();
        userModel1.setAddress(addressModel);
        userModel1.setCreated(LocalDate.ofEpochDay(1L));
        userModel1.setCredential(credentialModel);
        userModel1.setDeleted(true);
        userModel1.setDob(LocalDate.ofEpochDay(1L));
        userModel1.setEmail("jane.doe@example.org");
        userModel1.setFname("Fname");
        userModel1.setId(123L);
        userModel1.setImageURL("https://example.org/example");
        userModel1.setLname("Lname");
        userModel1.setPassword("iloveyou");
        userModel1.setPhone("4105551212");
        userModel1.setReservationModelBarber(new ArrayList<>());
        userModel1.setReservationModelClient(new ArrayList<>());
        userModel1.setRole("Role");
        userModel1.setUpdated(LocalDate.ofEpochDay(1L));

        CredentialModel credentialModel1 = new CredentialModel();
        credentialModel1.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel1.setGrantedAuthority("JaneDoe");
        credentialModel1.setId(123L);
        credentialModel1.setPassword("iloveyou");
        credentialModel1.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel1.setUser(userModel1);
        credentialModel1.setUsername("janedoe");

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setId(123L);
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setUsers(new HashSet<>());
        addressModel1.setZip("21654");

        AddressModel addressModel2 = new AddressModel();
        addressModel2.setApartement("Apartement");
        addressModel2.setCity("Oxford");
        addressModel2.setId(123L);
        addressModel2.setState("MD");
        addressModel2.setStreet("Street");
        addressModel2.setUsers(new HashSet<>());
        addressModel2.setZip("21654");

        CredentialModel credentialModel2 = new CredentialModel();
        credentialModel2.setCreated(null);
        credentialModel2.setGrantedAuthority("JaneDoe");
        credentialModel2.setId(123L);
        credentialModel2.setPassword("iloveyou");
        credentialModel2.setUpdated(null);
        credentialModel2.setUser(new UserModel());
        credentialModel2.setUsername("janedoe");

        UserModel userModel2 = new UserModel();
        userModel2.setAddress(addressModel2);
        userModel2.setCreated(LocalDate.ofEpochDay(1L));
        userModel2.setCredential(credentialModel2);
        userModel2.setDeleted(true);
        userModel2.setDob(LocalDate.ofEpochDay(1L));
        userModel2.setEmail("jane.doe@example.org");
        userModel2.setFname("Fname");
        userModel2.setId(123L);
        userModel2.setImageURL("https://example.org/example");
        userModel2.setLname("Lname");
        userModel2.setPassword("iloveyou");
        userModel2.setPhone("4105551212");
        userModel2.setReservationModelBarber(new ArrayList<>());
        userModel2.setReservationModelClient(new ArrayList<>());
        userModel2.setRole("Role");
        userModel2.setUpdated(LocalDate.ofEpochDay(1L));

        CredentialModel credentialModel3 = new CredentialModel();
        credentialModel3.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel3.setGrantedAuthority("JaneDoe");
        credentialModel3.setId(123L);
        credentialModel3.setPassword("iloveyou");
        credentialModel3.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel3.setUser(userModel2);
        credentialModel3.setUsername("janedoe");

        UserModel userModel3 = new UserModel();
        userModel3.setAddress(addressModel1);
        userModel3.setCreated(LocalDate.ofEpochDay(1L));
        userModel3.setCredential(credentialModel3);
        userModel3.setDeleted(true);
        userModel3.setDob(LocalDate.ofEpochDay(1L));
        userModel3.setEmail("jane.doe@example.org");
        userModel3.setFname("Fname");
        userModel3.setId(123L);
        userModel3.setImageURL("https://example.org/example");
        userModel3.setLname("Lname");
        userModel3.setPassword("iloveyou");
        userModel3.setPhone("4105551212");
        userModel3.setReservationModelBarber(new ArrayList<>());
        userModel3.setReservationModelClient(new ArrayList<>());
        userModel3.setRole("Role");
        userModel3.setUpdated(LocalDate.ofEpochDay(1L));

        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setCredentials(credentialModel1);
        userDetailsDTO.setToken("ABC123");
        userDetailsDTO.setUserModel(userModel3);
        when(this.utilsUser.converterUserModelToUserDetailsDTO(any())).thenReturn(userDetailsDTO);

        AddressModel addressModel3 = new AddressModel();
        addressModel3.setApartement("Apartement");
        addressModel3.setCity("Oxford");
        addressModel3.setId(123L);
        addressModel3.setState("MD");
        addressModel3.setStreet("Street");
        addressModel3.setUsers(new HashSet<>());
        addressModel3.setZip("21654");

        AddressModel addressModel4 = new AddressModel();
        addressModel4.setApartement("Apartement");
        addressModel4.setCity("Oxford");
        addressModel4.setId(123L);
        addressModel4.setState("MD");
        addressModel4.setStreet("Street");
        addressModel4.setUsers(new HashSet<>());
        addressModel4.setZip("21654");

        CredentialModel credentialModel4 = new CredentialModel();
        credentialModel4.setCreated(null);
        credentialModel4.setGrantedAuthority("JaneDoe");
        credentialModel4.setId(123L);
        credentialModel4.setPassword("iloveyou");
        credentialModel4.setUpdated(null);
        credentialModel4.setUser(new UserModel());
        credentialModel4.setUsername("janedoe");

        UserModel userModel4 = new UserModel();
        userModel4.setAddress(addressModel4);
        userModel4.setCreated(LocalDate.ofEpochDay(1L));
        userModel4.setCredential(credentialModel4);
        userModel4.setDeleted(true);
        userModel4.setDob(LocalDate.ofEpochDay(1L));
        userModel4.setEmail("jane.doe@example.org");
        userModel4.setFname("Fname");
        userModel4.setId(123L);
        userModel4.setImageURL("https://example.org/example");
        userModel4.setLname("Lname");
        userModel4.setPassword("iloveyou");
        userModel4.setPhone("4105551212");
        userModel4.setReservationModelBarber(new ArrayList<>());
        userModel4.setReservationModelClient(new ArrayList<>());
        userModel4.setRole("Role");
        userModel4.setUpdated(LocalDate.ofEpochDay(1L));

        CredentialModel credentialModel5 = new CredentialModel();
        credentialModel5.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel5.setGrantedAuthority("JaneDoe");
        credentialModel5.setId(123L);
        credentialModel5.setPassword("iloveyou");
        credentialModel5.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel5.setUser(userModel4);
        credentialModel5.setUsername("janedoe");

        UserModel userModel5 = new UserModel();
        userModel5.setAddress(addressModel3);
        userModel5.setCreated(LocalDate.ofEpochDay(1L));
        userModel5.setCredential(credentialModel5);
        userModel5.setDeleted(true);
        userModel5.setDob(LocalDate.ofEpochDay(1L));
        userModel5.setEmail("jane.doe@example.org");
        userModel5.setFname("Fname");
        userModel5.setId(123L);
        userModel5.setImageURL("https://example.org/example");
        userModel5.setLname("Lname");
        userModel5.setPassword("iloveyou");
        userModel5.setPhone("4105551212");
        userModel5.setReservationModelBarber(new ArrayList<>());
        userModel5.setReservationModelClient(new ArrayList<>());
        userModel5.setRole("Role");
        userModel5.setUpdated(LocalDate.ofEpochDay(1L));
        Optional<UserModel> ofResult = Optional.of(userModel5);
        when(this.userRepository.findUserModelByEmail(any())).thenReturn(ofResult);
        assertSame(userDetailsDTO, this.userServiceImpl.loadUserByUsername("janedoe"));
        verify(this.utilsUser).converterUserModelToUserDetailsDTO(any());
        verify(this.userRepository).findUserModelByEmail(any());
    }

    @Test
    void testUpdateUser() {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setApartement("Apartement");
        addressDTO.setCity("Oxford");
        addressDTO.setId(123L);
        addressDTO.setState("MD");
        addressDTO.setStreet("Street");
        addressDTO.setZip("21654");

        UserDTO userDTO = new UserDTO();
        userDTO.setAddress(addressDTO);
        userDTO.setCreated(LocalDate.ofEpochDay(1L));
        userDTO.setDeleted(true);
        userDTO.setDob(LocalDate.ofEpochDay(1L));
        userDTO.setEmail("jane.doe@example.org");
        userDTO.setFname("Fname");
        userDTO.setId(123L);
        userDTO.setImageURL("https://example.org/example");
        userDTO.setLname("Lname");
        userDTO.setPhone("4105551212");
        userDTO.setRole("Role");
        userDTO.setUpdated(LocalDate.ofEpochDay(1L));
        when(this.utilsUser.converterUserModelToUserDTO(any())).thenReturn(userDTO);

        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setId(123L);
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setUsers(new HashSet<>());
        addressModel.setZip("21654");

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setId(123L);
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setUsers(new HashSet<>());
        addressModel1.setZip("21654");

        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setCreated(null);
        credentialModel.setGrantedAuthority("JaneDoe");
        credentialModel.setId(123L);
        credentialModel.setPassword("iloveyou");
        credentialModel.setUpdated(null);
        credentialModel.setUser(new UserModel());
        credentialModel.setUsername("janedoe");

        UserModel userModel = new UserModel();
        userModel.setAddress(addressModel1);
        userModel.setCreated(LocalDate.ofEpochDay(1L));
        userModel.setCredential(credentialModel);
        userModel.setDeleted(true);
        userModel.setDob(LocalDate.ofEpochDay(1L));
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(123L);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setReservationModelBarber(new ArrayList<>());
        userModel.setReservationModelClient(new ArrayList<>());
        userModel.setRole("Role");
        userModel.setUpdated(LocalDate.ofEpochDay(1L));

        CredentialModel credentialModel1 = new CredentialModel();
        credentialModel1.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel1.setGrantedAuthority("JaneDoe");
        credentialModel1.setId(123L);
        credentialModel1.setPassword("iloveyou");
        credentialModel1.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel1.setUser(userModel);
        credentialModel1.setUsername("janedoe");

        UserModel userModel1 = new UserModel();
        userModel1.setAddress(addressModel);
        userModel1.setCreated(LocalDate.ofEpochDay(1L));
        userModel1.setCredential(credentialModel1);
        userModel1.setDeleted(true);
        userModel1.setDob(LocalDate.ofEpochDay(1L));
        userModel1.setEmail("jane.doe@example.org");
        userModel1.setFname("Fname");
        userModel1.setId(123L);
        userModel1.setImageURL("https://example.org/example");
        userModel1.setLname("Lname");
        userModel1.setPassword("iloveyou");
        userModel1.setPhone("4105551212");
        userModel1.setReservationModelBarber(new ArrayList<>());
        userModel1.setReservationModelClient(new ArrayList<>());
        userModel1.setRole("Role");
        userModel1.setUpdated(LocalDate.ofEpochDay(1L));
        Optional<UserModel> ofResult = Optional.of(userModel1);

        AddressModel addressModel2 = new AddressModel();
        addressModel2.setApartement("Apartement");
        addressModel2.setCity("Oxford");
        addressModel2.setId(123L);
        addressModel2.setState("MD");
        addressModel2.setStreet("Street");
        addressModel2.setUsers(new HashSet<>());
        addressModel2.setZip("21654");

        AddressModel addressModel3 = new AddressModel();
        addressModel3.setApartement("Apartement");
        addressModel3.setCity("Oxford");
        addressModel3.setId(123L);
        addressModel3.setState("MD");
        addressModel3.setStreet("Street");
        addressModel3.setUsers(new HashSet<>());
        addressModel3.setZip("21654");

        UserModel userModel2 = new UserModel();
        userModel2.setAddress(new AddressModel());
        userModel2.setCreated(null);
        userModel2.setCredential(new CredentialModel());
        userModel2.setDeleted(true);
        userModel2.setDob(null);
        userModel2.setEmail("jane.doe@example.org");
        userModel2.setFname("Fname");
        userModel2.setId(123L);
        userModel2.setImageURL("https://example.org/example");
        userModel2.setLname("Lname");
        userModel2.setPassword("iloveyou");
        userModel2.setPhone("4105551212");
        userModel2.setReservationModelBarber(new ArrayList<>());
        userModel2.setReservationModelClient(new ArrayList<>());
        userModel2.setRole("Role");
        userModel2.setUpdated(null);

        CredentialModel credentialModel2 = new CredentialModel();
        credentialModel2.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel2.setGrantedAuthority("JaneDoe");
        credentialModel2.setId(123L);
        credentialModel2.setPassword("iloveyou");
        credentialModel2.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel2.setUser(userModel2);
        credentialModel2.setUsername("janedoe");

        UserModel userModel3 = new UserModel();
        userModel3.setAddress(addressModel3);
        userModel3.setCreated(LocalDate.ofEpochDay(1L));
        userModel3.setCredential(credentialModel2);
        userModel3.setDeleted(true);
        userModel3.setDob(LocalDate.ofEpochDay(1L));
        userModel3.setEmail("jane.doe@example.org");
        userModel3.setFname("Fname");
        userModel3.setId(123L);
        userModel3.setImageURL("https://example.org/example");
        userModel3.setLname("Lname");
        userModel3.setPassword("iloveyou");
        userModel3.setPhone("4105551212");
        userModel3.setReservationModelBarber(new ArrayList<>());
        userModel3.setReservationModelClient(new ArrayList<>());
        userModel3.setRole("Role");
        userModel3.setUpdated(LocalDate.ofEpochDay(1L));

        CredentialModel credentialModel3 = new CredentialModel();
        credentialModel3.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel3.setGrantedAuthority("JaneDoe");
        credentialModel3.setId(123L);
        credentialModel3.setPassword("iloveyou");
        credentialModel3.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel3.setUser(userModel3);
        credentialModel3.setUsername("janedoe");

        UserModel userModel4 = new UserModel();
        userModel4.setAddress(addressModel2);
        userModel4.setCreated(LocalDate.ofEpochDay(1L));
        userModel4.setCredential(credentialModel3);
        userModel4.setDeleted(true);
        userModel4.setDob(LocalDate.ofEpochDay(1L));
        userModel4.setEmail("jane.doe@example.org");
        userModel4.setFname("Fname");
        userModel4.setId(123L);
        userModel4.setImageURL("https://example.org/example");
        userModel4.setLname("Lname");
        userModel4.setPassword("iloveyou");
        userModel4.setPhone("4105551212");
        userModel4.setReservationModelBarber(new ArrayList<>());
        userModel4.setReservationModelClient(new ArrayList<>());
        userModel4.setRole("Role");
        userModel4.setUpdated(LocalDate.ofEpochDay(1L));
        when(this.userRepository.save(any())).thenReturn(userModel4);
        when(this.userRepository.findById(any())).thenReturn(ofResult);

        AddressDTO addressDTO1 = new AddressDTO();
        addressDTO1.setApartement("Apartement");
        addressDTO1.setCity("Oxford");
        addressDTO1.setId(123L);
        addressDTO1.setState("MD");
        addressDTO1.setStreet("Street");
        addressDTO1.setZip("21654");

        UserUpdateDTO userDTO1 = new UserUpdateDTO();
        userDTO1.setFname("Fname");
        userDTO1.setId(123L);
        userDTO1.setLname("Lname");
        userDTO1.setPhone("4105551212");
        userDTO1.setUpdated(LocalDate.ofEpochDay(1L));
        assertTrue(this.userServiceImpl.updateUser(123L, userDTO1).isPresent());
        verify(this.utilsUser).converterUserModelToUserDTO(any());
        verify(this.userRepository).save(any());
        verify(this.userRepository).findById(any());
    }

    @Test
    void testUpdateUser2() {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setApartement("Apartement");
        addressDTO.setCity("Oxford");
        addressDTO.setId(123L);
        addressDTO.setState("MD");
        addressDTO.setStreet("Street");
        addressDTO.setZip("21654");

        UserDTO userDTO = new UserDTO();
        userDTO.setAddress(addressDTO);
        userDTO.setCreated(LocalDate.ofEpochDay(1L));
        userDTO.setDeleted(true);
        userDTO.setDob(LocalDate.ofEpochDay(1L));
        userDTO.setEmail("jane.doe@example.org");
        userDTO.setFname("Fname");
        userDTO.setId(123L);
        userDTO.setImageURL("https://example.org/example");
        userDTO.setLname("Lname");
        userDTO.setPhone("4105551212");
        userDTO.setRole("Role");
        userDTO.setUpdated(LocalDate.ofEpochDay(1L));
        when(this.utilsUser.converterUserModelToUserDTO(any())).thenReturn(userDTO);

        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setId(123L);
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setUsers(new HashSet<>());
        addressModel.setZip("21654");

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setId(123L);
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setUsers(new HashSet<>());
        addressModel1.setZip("21654");

        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setCreated(null);
        credentialModel.setGrantedAuthority("JaneDoe");
        credentialModel.setId(123L);
        credentialModel.setPassword("iloveyou");
        credentialModel.setUpdated(null);
        credentialModel.setUser(new UserModel());
        credentialModel.setUsername("janedoe");

        UserModel userModel = new UserModel();
        userModel.setAddress(addressModel1);
        userModel.setCreated(LocalDate.ofEpochDay(1L));
        userModel.setCredential(credentialModel);
        userModel.setDeleted(true);
        userModel.setDob(LocalDate.ofEpochDay(1L));
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(123L);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setReservationModelBarber(new ArrayList<>());
        userModel.setReservationModelClient(new ArrayList<>());
        userModel.setRole("Role");
        userModel.setUpdated(LocalDate.ofEpochDay(1L));

        CredentialModel credentialModel1 = new CredentialModel();
        credentialModel1.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel1.setGrantedAuthority("JaneDoe");
        credentialModel1.setId(123L);
        credentialModel1.setPassword("iloveyou");
        credentialModel1.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel1.setUser(userModel);
        credentialModel1.setUsername("janedoe");

        UserModel userModel1 = new UserModel();
        userModel1.setAddress(addressModel);
        userModel1.setCreated(LocalDate.ofEpochDay(1L));
        userModel1.setCredential(credentialModel1);
        userModel1.setDeleted(true);
        userModel1.setDob(LocalDate.ofEpochDay(1L));
        userModel1.setEmail("jane.doe@example.org");
        userModel1.setFname("Fname");
        userModel1.setId(123L);
        userModel1.setImageURL("https://example.org/example");
        userModel1.setLname("Lname");
        userModel1.setPassword("iloveyou");
        userModel1.setPhone("4105551212");
        userModel1.setReservationModelBarber(new ArrayList<>());
        userModel1.setReservationModelClient(new ArrayList<>());
        userModel1.setRole("Role");
        userModel1.setUpdated(LocalDate.ofEpochDay(1L));
        Optional<UserModel> ofResult = Optional.of(userModel1);
        when(this.userRepository.save(any())).thenThrow(new UsernameNotFoundException("Msg"));
        when(this.userRepository.findById(any())).thenReturn(ofResult);

        AddressDTO addressDTO1 = new AddressDTO();
        addressDTO1.setApartement("Apartement");
        addressDTO1.setCity("Oxford");
        addressDTO1.setId(123L);
        addressDTO1.setState("MD");
        addressDTO1.setStreet("Street");
        addressDTO1.setZip("21654");

        UserUpdateDTO userDTO1 = new UserUpdateDTO();
        userDTO1.setFname("Fname");
        userDTO1.setId(123L);
        userDTO1.setLname("Lname");
        userDTO1.setPhone("4105551212");
        userDTO1.setUpdated(LocalDate.ofEpochDay(1L));
        assertThrows(UsernameNotFoundException.class, () -> this.userServiceImpl.updateUser(123L, userDTO1));
        verify(this.userRepository).save(any());
        verify(this.userRepository).findById(any());
    }

    @Test
    void testFindUserByEmail() {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setApartement("Apartement");
        addressDTO.setCity("Oxford");
        addressDTO.setId(123L);
        addressDTO.setState("MD");
        addressDTO.setStreet("Street");
        addressDTO.setZip("21654");

        UserDTO userDTO = new UserDTO();
        userDTO.setAddress(addressDTO);
        userDTO.setCreated(LocalDate.ofEpochDay(1L));
        userDTO.setDeleted(true);
        userDTO.setDob(LocalDate.ofEpochDay(1L));
        userDTO.setEmail("jane.doe@example.org");
        userDTO.setFname("Fname");
        userDTO.setId(123L);
        userDTO.setImageURL("https://example.org/example");
        userDTO.setLname("Lname");
        userDTO.setPhone("4105551212");
        userDTO.setRole("Role");
        userDTO.setUpdated(LocalDate.ofEpochDay(1L));
        when(this.utilsUser.converterUserModelToUserDTO(any())).thenReturn(userDTO);

        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setId(123L);
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setUsers(new HashSet<>());
        addressModel.setZip("21654");

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setId(123L);
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setUsers(new HashSet<>());
        addressModel1.setZip("21654");

        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setCreated(null);
        credentialModel.setGrantedAuthority("JaneDoe");
        credentialModel.setId(123L);
        credentialModel.setPassword("iloveyou");
        credentialModel.setUpdated(null);
        credentialModel.setUser(new UserModel());
        credentialModel.setUsername("janedoe");

        UserModel userModel = new UserModel();
        userModel.setAddress(addressModel1);
        userModel.setCreated(LocalDate.ofEpochDay(1L));
        userModel.setCredential(credentialModel);
        userModel.setDeleted(true);
        userModel.setDob(LocalDate.ofEpochDay(1L));
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(123L);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setReservationModelBarber(new ArrayList<>());
        userModel.setReservationModelClient(new ArrayList<>());
        userModel.setRole("Role");
        userModel.setUpdated(LocalDate.ofEpochDay(1L));

        CredentialModel credentialModel1 = new CredentialModel();
        credentialModel1.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel1.setGrantedAuthority("JaneDoe");
        credentialModel1.setId(123L);
        credentialModel1.setPassword("iloveyou");
        credentialModel1.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel1.setUser(userModel);
        credentialModel1.setUsername("janedoe");

        UserModel userModel1 = new UserModel();
        userModel1.setAddress(addressModel);
        userModel1.setCreated(LocalDate.ofEpochDay(1L));
        userModel1.setCredential(credentialModel1);
        userModel1.setDeleted(true);
        userModel1.setDob(LocalDate.ofEpochDay(1L));
        userModel1.setEmail("jane.doe@example.org");
        userModel1.setFname("Fname");
        userModel1.setId(123L);
        userModel1.setImageURL("https://example.org/example");
        userModel1.setLname("Lname");
        userModel1.setPassword("iloveyou");
        userModel1.setPhone("4105551212");
        userModel1.setReservationModelBarber(new ArrayList<>());
        userModel1.setReservationModelClient(new ArrayList<>());
        userModel1.setRole("Role");
        userModel1.setUpdated(LocalDate.ofEpochDay(1L));
        Optional<UserModel> ofResult = Optional.of(userModel1);
        when(this.userRepository.findUserModelByEmail(any())).thenReturn(ofResult);
        assertTrue(this.userServiceImpl.findUserByEmail("jane.doe@example.org").isPresent());
        verify(this.utilsUser).converterUserModelToUserDTO(any());
        verify(this.userRepository).findUserModelByEmail(any());
    }

    @Test
    void testFindUserByEmail2() {
        when(this.utilsUser.converterUserModelToUserDTO(any())).thenThrow(new UsernameNotFoundException("Msg"));

        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setId(123L);
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setUsers(new HashSet<>());
        addressModel.setZip("21654");

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setId(123L);
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setUsers(new HashSet<>());
        addressModel1.setZip("21654");

        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setCreated(null);
        credentialModel.setGrantedAuthority("JaneDoe");
        credentialModel.setId(123L);
        credentialModel.setPassword("iloveyou");
        credentialModel.setUpdated(null);
        credentialModel.setUser(new UserModel());
        credentialModel.setUsername("janedoe");

        UserModel userModel = new UserModel();
        userModel.setAddress(addressModel1);
        userModel.setCreated(LocalDate.ofEpochDay(1L));
        userModel.setCredential(credentialModel);
        userModel.setDeleted(true);
        userModel.setDob(LocalDate.ofEpochDay(1L));
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(123L);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setReservationModelBarber(new ArrayList<>());
        userModel.setReservationModelClient(new ArrayList<>());
        userModel.setRole("Role");
        userModel.setUpdated(LocalDate.ofEpochDay(1L));

        CredentialModel credentialModel1 = new CredentialModel();
        credentialModel1.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel1.setGrantedAuthority("JaneDoe");
        credentialModel1.setId(123L);
        credentialModel1.setPassword("iloveyou");
        credentialModel1.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel1.setUser(userModel);
        credentialModel1.setUsername("janedoe");

        UserModel userModel1 = new UserModel();
        userModel1.setAddress(addressModel);
        userModel1.setCreated(LocalDate.ofEpochDay(1L));
        userModel1.setCredential(credentialModel1);
        userModel1.setDeleted(true);
        userModel1.setDob(LocalDate.ofEpochDay(1L));
        userModel1.setEmail("jane.doe@example.org");
        userModel1.setFname("Fname");
        userModel1.setId(123L);
        userModel1.setImageURL("https://example.org/example");
        userModel1.setLname("Lname");
        userModel1.setPassword("iloveyou");
        userModel1.setPhone("4105551212");
        userModel1.setReservationModelBarber(new ArrayList<>());
        userModel1.setReservationModelClient(new ArrayList<>());
        userModel1.setRole("Role");
        userModel1.setUpdated(LocalDate.ofEpochDay(1L));
        Optional<UserModel> ofResult = Optional.of(userModel1);
        when(this.userRepository.findUserModelByEmail(any())).thenReturn(ofResult);
        assertThrows(UsernameNotFoundException.class, () -> this.userServiceImpl.findUserByEmail("jane.doe@example.org"));
        verify(this.utilsUser).converterUserModelToUserDTO(any());
        verify(this.userRepository).findUserModelByEmail(any());
    }

    @Test
    void testUpdatePassword() {
        doNothing().when(this.userRepository).updatePassword(any(), any());
        when(this.passwordEncoder.encode(any())).thenReturn("secret");
        this.userServiceImpl.updatePassword("jane.doe@example.org", "iloveyou");
        verify(this.userRepository).updatePassword(any(), any());
        verify(this.passwordEncoder).encode(any());
    }

    @Test
    void testUpdatePassword2() {
        doNothing().when(this.userRepository).updatePassword(any(), any());
        when(this.passwordEncoder.encode(any())).thenThrow(new UsernameNotFoundException("Msg"));
        assertThrows(UsernameNotFoundException.class,
                () -> this.userServiceImpl.updatePassword("jane.doe@example.org", "iloveyou"));
        verify(this.passwordEncoder).encode(any());
    }

    @Test
    void testFindAllUsers() {
        when(this.userRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(this.userServiceImpl.findAllUsers().isEmpty());
        verify(this.userRepository).findAll();
    }


    @Test
    void testSaveUser() {
        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setId(123L);
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setUsers(new HashSet<>());
        addressModel.setZip("21654");

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setId(123L);
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setUsers(new HashSet<>());
        addressModel1.setZip("21654");

        AddressModel addressModel2 = new AddressModel();
        addressModel2.setApartement("Apartement");
        addressModel2.setCity("Oxford");
        addressModel2.setId(123L);
        addressModel2.setState("MD");
        addressModel2.setStreet("Street");
        addressModel2.setUsers(new HashSet<>());
        addressModel2.setZip("21654");

        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setCreated(null);
        credentialModel.setGrantedAuthority("JaneDoe");
        credentialModel.setId(123L);
        credentialModel.setPassword("iloveyou");
        credentialModel.setUpdated(null);
        credentialModel.setUser(new UserModel());
        credentialModel.setUsername("janedoe");

        UserModel userModel = new UserModel();
        userModel.setAddress(addressModel2);
        userModel.setCreated(LocalDate.ofEpochDay(1L));
        userModel.setCredential(credentialModel);
        userModel.setDeleted(true);
        userModel.setDob(LocalDate.ofEpochDay(1L));
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(123L);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setReservationModelBarber(new ArrayList<>());
        userModel.setReservationModelClient(new ArrayList<>());
        userModel.setRole("Role");
        userModel.setUpdated(LocalDate.ofEpochDay(1L));

        CredentialModel credentialModel1 = new CredentialModel();
        credentialModel1.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel1.setGrantedAuthority("JaneDoe");
        credentialModel1.setId(123L);
        credentialModel1.setPassword("iloveyou");
        credentialModel1.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel1.setUser(userModel);
        credentialModel1.setUsername("janedoe");

        UserModel userModel1 = new UserModel();
        userModel1.setAddress(addressModel1);
        userModel1.setCreated(LocalDate.ofEpochDay(1L));
        userModel1.setCredential(credentialModel1);
        userModel1.setDeleted(true);
        userModel1.setDob(LocalDate.ofEpochDay(1L));
        userModel1.setEmail("jane.doe@example.org");
        userModel1.setFname("Fname");
        userModel1.setId(123L);
        userModel1.setImageURL("https://example.org/example");
        userModel1.setLname("Lname");
        userModel1.setPassword("iloveyou");
        userModel1.setPhone("4105551212");
        userModel1.setReservationModelBarber(new ArrayList<>());
        userModel1.setReservationModelClient(new ArrayList<>());
        userModel1.setRole("Role");
        userModel1.setUpdated(LocalDate.ofEpochDay(1L));

        CredentialModel credentialModel2 = new CredentialModel();
        credentialModel2.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel2.setGrantedAuthority("JaneDoe");
        credentialModel2.setId(123L);
        credentialModel2.setPassword("iloveyou");
        credentialModel2.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel2.setUser(userModel1);
        credentialModel2.setUsername("janedoe");

        AddressModel addressModel3 = new AddressModel();
        addressModel3.setApartement("Apartement");
        addressModel3.setCity("Oxford");
        addressModel3.setId(123L);
        addressModel3.setState("MD");
        addressModel3.setStreet("Street");
        addressModel3.setUsers(new HashSet<>());
        addressModel3.setZip("21654");

        AddressModel addressModel4 = new AddressModel();
        addressModel4.setApartement("Apartement");
        addressModel4.setCity("Oxford");
        addressModel4.setId(123L);
        addressModel4.setState("MD");
        addressModel4.setStreet("Street");
        addressModel4.setUsers(new HashSet<>());
        addressModel4.setZip("21654");

        UserModel userModel2 = new UserModel();
        userModel2.setAddress(new AddressModel());
        userModel2.setCreated(null);
        userModel2.setCredential(new CredentialModel());
        userModel2.setDeleted(true);
        userModel2.setDob(null);
        userModel2.setEmail("jane.doe@example.org");
        userModel2.setFname("Fname");
        userModel2.setId(123L);
        userModel2.setImageURL("https://example.org/example");
        userModel2.setLname("Lname");
        userModel2.setPassword("iloveyou");
        userModel2.setPhone("4105551212");
        userModel2.setReservationModelBarber(new ArrayList<>());
        userModel2.setReservationModelClient(new ArrayList<>());
        userModel2.setRole("Role");
        userModel2.setUpdated(null);

        CredentialModel credentialModel3 = new CredentialModel();
        credentialModel3.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel3.setGrantedAuthority("JaneDoe");
        credentialModel3.setId(123L);
        credentialModel3.setPassword("iloveyou");
        credentialModel3.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel3.setUser(userModel2);
        credentialModel3.setUsername("janedoe");

        UserModel userModel3 = new UserModel();
        userModel3.setAddress(addressModel4);
        userModel3.setCreated(LocalDate.ofEpochDay(1L));
        userModel3.setCredential(credentialModel3);
        userModel3.setDeleted(true);
        userModel3.setDob(LocalDate.ofEpochDay(1L));
        userModel3.setEmail("jane.doe@example.org");
        userModel3.setFname("Fname");
        userModel3.setId(123L);
        userModel3.setImageURL("https://example.org/example");
        userModel3.setLname("Lname");
        userModel3.setPassword("iloveyou");
        userModel3.setPhone("4105551212");
        userModel3.setReservationModelBarber(new ArrayList<>());
        userModel3.setReservationModelClient(new ArrayList<>());
        userModel3.setRole("Role");
        userModel3.setUpdated(LocalDate.ofEpochDay(1L));

        CredentialModel credentialModel4 = new CredentialModel();
        credentialModel4.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel4.setGrantedAuthority("JaneDoe");
        credentialModel4.setId(123L);
        credentialModel4.setPassword("iloveyou");
        credentialModel4.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel4.setUser(userModel3);
        credentialModel4.setUsername("janedoe");

        UserModel userModel4 = new UserModel();
        userModel4.setAddress(addressModel3);
        userModel4.setCreated(LocalDate.ofEpochDay(1L));
        userModel4.setCredential(credentialModel4);
        userModel4.setDeleted(true);
        userModel4.setDob(LocalDate.ofEpochDay(1L));
        userModel4.setEmail("jane.doe@example.org");
        userModel4.setFname("Fname");
        userModel4.setId(123L);
        userModel4.setImageURL("https://example.org/example");
        userModel4.setLname("Lname");
        userModel4.setPassword("iloveyou");
        userModel4.setPhone("4105551212");
        userModel4.setReservationModelBarber(new ArrayList<>());
        userModel4.setReservationModelClient(new ArrayList<>());
        userModel4.setRole("Role");
        userModel4.setUpdated(LocalDate.ofEpochDay(1L));
        when(this.utilsUser.extractAddressModel(any())).thenReturn(addressModel);
        when(this.utilsUser.extractCredentialModel(any())).thenReturn(credentialModel2);
        when(this.utilsUser.extractUserModelToSignUp(any())).thenReturn(userModel4);
        when(this.credentialService.saveCredential(any()))
                .thenThrow(new UsernameNotFoundException("Msg"));

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setApartement("Apartement");
        addressDTO.setCity("Oxford");
        addressDTO.setId(123L);
        addressDTO.setState("MD");
        addressDTO.setStreet("Street");
        addressDTO.setZip("21654");
        Optional<AddressDTO> ofResult = Optional.of(addressDTO);
        when(this.addressService.saveAddressModel(any())).thenReturn(ofResult);

        SignupDTO signupDTO = new SignupDTO();
        signupDTO.setApartement("Apartment");
        signupDTO.setCity("Oxford");
        signupDTO.setDob(LocalDate.ofEpochDay(1L));
        signupDTO.setEmail("jane.doe@example.org");
        signupDTO.setFname("Fname");
        signupDTO.setImageURL("https://example.org/example");
        signupDTO.setLname("Lname");
        signupDTO.setPassword("iloveyou");
        signupDTO.setPhone("4105551212");
        signupDTO.setRole("Role");
        signupDTO.setState("MD");
        signupDTO.setStreet("Street");
        signupDTO.setZip("21654");
        assertThrows(UsernameNotFoundException.class, () -> this.userServiceImpl.saveUser(signupDTO));
        verify(this.utilsUser).extractAddressModel(any());
        verify(this.utilsUser).extractCredentialModel(any());
        verify(this.utilsUser).extractUserModelToSignUp(any());
        verify(this.credentialService).saveCredential(any());
        verify(this.addressService).saveAddressModel(any());
    }

    @Test
    void testSaveUser2() {
        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setId(123L);
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setUsers(new HashSet<>());
        addressModel.setZip("21654");

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setId(123L);
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setUsers(new HashSet<>());
        addressModel1.setZip("21654");

        AddressModel addressModel2 = new AddressModel();
        addressModel2.setApartement("Apartement");
        addressModel2.setCity("Oxford");
        addressModel2.setId(123L);
        addressModel2.setState("MD");
        addressModel2.setStreet("Street");
        addressModel2.setUsers(new HashSet<>());
        addressModel2.setZip("21654");

        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setCreated(null);
        credentialModel.setGrantedAuthority("JaneDoe");
        credentialModel.setId(123L);
        credentialModel.setPassword("iloveyou");
        credentialModel.setUpdated(null);
        credentialModel.setUser(new UserModel());
        credentialModel.setUsername("janedoe");

        UserModel userModel = new UserModel();
        userModel.setAddress(addressModel2);
        userModel.setCreated(LocalDate.ofEpochDay(1L));
        userModel.setCredential(credentialModel);
        userModel.setDeleted(true);
        userModel.setDob(LocalDate.ofEpochDay(1L));
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(123L);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setReservationModelBarber(new ArrayList<>());
        userModel.setReservationModelClient(new ArrayList<>());
        userModel.setRole("Role");
        userModel.setUpdated(LocalDate.ofEpochDay(1L));

        CredentialModel credentialModel1 = new CredentialModel();
        credentialModel1.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel1.setGrantedAuthority("JaneDoe");
        credentialModel1.setId(123L);
        credentialModel1.setPassword("iloveyou");
        credentialModel1.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel1.setUser(userModel);
        credentialModel1.setUsername("janedoe");

        UserModel userModel1 = new UserModel();
        userModel1.setAddress(addressModel1);
        userModel1.setCreated(LocalDate.ofEpochDay(1L));
        userModel1.setCredential(credentialModel1);
        userModel1.setDeleted(true);
        userModel1.setDob(LocalDate.ofEpochDay(1L));
        userModel1.setEmail("jane.doe@example.org");
        userModel1.setFname("Fname");
        userModel1.setId(123L);
        userModel1.setImageURL("https://example.org/example");
        userModel1.setLname("Lname");
        userModel1.setPassword("iloveyou");
        userModel1.setPhone("4105551212");
        userModel1.setReservationModelBarber(new ArrayList<>());
        userModel1.setReservationModelClient(new ArrayList<>());
        userModel1.setRole("Role");
        userModel1.setUpdated(LocalDate.ofEpochDay(1L));
        CredentialModel credentialModel2 = mock(CredentialModel.class);
        doNothing().when(credentialModel2).setCreated(any());
        doNothing().when(credentialModel2).setGrantedAuthority(any());
        doNothing().when(credentialModel2).setId(any());
        doNothing().when(credentialModel2).setPassword(any());
        doNothing().when(credentialModel2).setUpdated(any());
        doNothing().when(credentialModel2).setUser(any());
        doNothing().when(credentialModel2).setUsername(any());
        credentialModel2.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel2.setGrantedAuthority("JaneDoe");
        credentialModel2.setId(123L);
        credentialModel2.setPassword("iloveyou");
        credentialModel2.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel2.setUser(userModel1);
        credentialModel2.setUsername("janedoe");

        AddressModel addressModel3 = new AddressModel();
        addressModel3.setApartement("Apartement");
        addressModel3.setCity("Oxford");
        addressModel3.setId(123L);
        addressModel3.setState("MD");
        addressModel3.setStreet("Street");
        addressModel3.setUsers(new HashSet<>());
        addressModel3.setZip("21654");

        AddressModel addressModel4 = new AddressModel();
        addressModel4.setApartement("Apartement");
        addressModel4.setCity("Oxford");
        addressModel4.setId(123L);
        addressModel4.setState("MD");
        addressModel4.setStreet("Street");
        addressModel4.setUsers(new HashSet<>());
        addressModel4.setZip("21654");

        UserModel userModel2 = new UserModel();
        userModel2.setAddress(new AddressModel());
        userModel2.setCreated(null);
        userModel2.setCredential(new CredentialModel());
        userModel2.setDeleted(true);
        userModel2.setDob(null);
        userModel2.setEmail("jane.doe@example.org");
        userModel2.setFname("Fname");
        userModel2.setId(123L);
        userModel2.setImageURL("https://example.org/example");
        userModel2.setLname("Lname");
        userModel2.setPassword("iloveyou");
        userModel2.setPhone("4105551212");
        userModel2.setReservationModelBarber(new ArrayList<>());
        userModel2.setReservationModelClient(new ArrayList<>());
        userModel2.setRole("Role");
        userModel2.setUpdated(null);

        CredentialModel credentialModel3 = new CredentialModel();
        credentialModel3.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel3.setGrantedAuthority("JaneDoe");
        credentialModel3.setId(123L);
        credentialModel3.setPassword("iloveyou");
        credentialModel3.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel3.setUser(userModel2);
        credentialModel3.setUsername("janedoe");

        UserModel userModel3 = new UserModel();
        userModel3.setAddress(addressModel4);
        userModel3.setCreated(LocalDate.ofEpochDay(1L));
        userModel3.setCredential(credentialModel3);
        userModel3.setDeleted(true);
        userModel3.setDob(LocalDate.ofEpochDay(1L));
        userModel3.setEmail("jane.doe@example.org");
        userModel3.setFname("Fname");
        userModel3.setId(123L);
        userModel3.setImageURL("https://example.org/example");
        userModel3.setLname("Lname");
        userModel3.setPassword("iloveyou");
        userModel3.setPhone("4105551212");
        userModel3.setReservationModelBarber(new ArrayList<>());
        userModel3.setReservationModelClient(new ArrayList<>());
        userModel3.setRole("Role");
        userModel3.setUpdated(LocalDate.ofEpochDay(1L));

        CredentialModel credentialModel4 = new CredentialModel();
        credentialModel4.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel4.setGrantedAuthority("JaneDoe");
        credentialModel4.setId(123L);
        credentialModel4.setPassword("iloveyou");
        credentialModel4.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel4.setUser(userModel3);
        credentialModel4.setUsername("janedoe");

        UserModel userModel4 = new UserModel();
        userModel4.setAddress(addressModel3);
        userModel4.setCreated(LocalDate.ofEpochDay(1L));
        userModel4.setCredential(credentialModel4);
        userModel4.setDeleted(true);
        userModel4.setDob(LocalDate.ofEpochDay(1L));
        userModel4.setEmail("jane.doe@example.org");
        userModel4.setFname("Fname");
        userModel4.setId(123L);
        userModel4.setImageURL("https://example.org/example");
        userModel4.setLname("Lname");
        userModel4.setPassword("iloveyou");
        userModel4.setPhone("4105551212");
        userModel4.setReservationModelBarber(new ArrayList<>());
        userModel4.setReservationModelClient(new ArrayList<>());
        userModel4.setRole("Role");
        userModel4.setUpdated(LocalDate.ofEpochDay(1L));
        when(this.utilsUser.extractAddressModel(any())).thenReturn(addressModel);
        when(this.utilsUser.extractCredentialModel(any())).thenReturn(credentialModel2);
        when(this.utilsUser.extractUserModelToSignUp(any())).thenReturn(userModel4);
        when(this.credentialService.saveCredential(any()))
                .thenThrow(new UsernameNotFoundException("Msg"));

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setApartement("Apartement");
        addressDTO.setCity("Oxford");
        addressDTO.setId(123L);
        addressDTO.setState("MD");
        addressDTO.setStreet("Street");
        addressDTO.setZip("21654");
        Optional<AddressDTO> ofResult = Optional.of(addressDTO);
        when(this.addressService.saveAddressModel(any())).thenReturn(ofResult);

        SignupDTO signupDTO = new SignupDTO();
        signupDTO.setApartement("Apartment");
        signupDTO.setCity("Oxford");
        signupDTO.setDob(LocalDate.ofEpochDay(1L));
        signupDTO.setEmail("jane.doe@example.org");
        signupDTO.setFname("Fname");
        signupDTO.setImageURL("https://example.org/example");
        signupDTO.setLname("Lname");
        signupDTO.setPassword("iloveyou");
        signupDTO.setPhone("4105551212");
        signupDTO.setRole("Role");
        signupDTO.setState("MD");
        signupDTO.setStreet("Street");
        signupDTO.setZip("21654");
        assertThrows(UsernameNotFoundException.class, () -> this.userServiceImpl.saveUser(signupDTO));
        verify(this.utilsUser).extractAddressModel(any());
        verify(this.utilsUser).extractCredentialModel(any());
        verify(this.utilsUser).extractUserModelToSignUp(any());
        verify(credentialModel2).setCreated(any());
        verify(credentialModel2).setGrantedAuthority(any());
        verify(credentialModel2).setId(any());
        verify(credentialModel2).setPassword(any());
        verify(credentialModel2).setUpdated(any());
        verify(credentialModel2, atLeast(1)).setUser(any());
        verify(credentialModel2).setUsername(any());
        verify(this.credentialService).saveCredential(any());
        verify(this.addressService).saveAddressModel(any());
    }

    @Test
    void testFindUserById() {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setApartement("Apartement");
        addressDTO.setCity("Oxford");
        addressDTO.setId(123L);
        addressDTO.setState("MD");
        addressDTO.setStreet("Street");
        addressDTO.setZip("21654");

        UserDTO userDTO = new UserDTO();
        userDTO.setAddress(addressDTO);
        userDTO.setCreated(LocalDate.ofEpochDay(1L));
        userDTO.setDeleted(true);
        userDTO.setDob(LocalDate.ofEpochDay(1L));
        userDTO.setEmail("jane.doe@example.org");
        userDTO.setFname("Fname");
        userDTO.setId(123L);
        userDTO.setImageURL("https://example.org/example");
        userDTO.setLname("Lname");
        userDTO.setPhone("4105551212");
        userDTO.setRole("Role");
        userDTO.setUpdated(LocalDate.ofEpochDay(1L));
        when(this.utilsUser.converterUserModelToUserDTO(any())).thenReturn(userDTO);

        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setId(123L);
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setUsers(new HashSet<>());
        addressModel.setZip("21654");

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setId(123L);
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setUsers(new HashSet<>());
        addressModel1.setZip("21654");

        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setCreated(null);
        credentialModel.setGrantedAuthority("JaneDoe");
        credentialModel.setId(123L);
        credentialModel.setPassword("iloveyou");
        credentialModel.setUpdated(null);
        credentialModel.setUser(new UserModel());
        credentialModel.setUsername("janedoe");

        UserModel userModel = new UserModel();
        userModel.setAddress(addressModel1);
        userModel.setCreated(LocalDate.ofEpochDay(1L));
        userModel.setCredential(credentialModel);
        userModel.setDeleted(true);
        userModel.setDob(LocalDate.ofEpochDay(1L));
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(123L);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setReservationModelBarber(new ArrayList<>());
        userModel.setReservationModelClient(new ArrayList<>());
        userModel.setRole("Role");
        userModel.setUpdated(LocalDate.ofEpochDay(1L));

        CredentialModel credentialModel1 = new CredentialModel();
        credentialModel1.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel1.setGrantedAuthority("JaneDoe");
        credentialModel1.setId(123L);
        credentialModel1.setPassword("iloveyou");
        credentialModel1.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel1.setUser(userModel);
        credentialModel1.setUsername("janedoe");

        UserModel userModel1 = new UserModel();
        userModel1.setAddress(addressModel);
        userModel1.setCreated(LocalDate.ofEpochDay(1L));
        userModel1.setCredential(credentialModel1);
        userModel1.setDeleted(true);
        userModel1.setDob(LocalDate.ofEpochDay(1L));
        userModel1.setEmail("jane.doe@example.org");
        userModel1.setFname("Fname");
        userModel1.setId(123L);
        userModel1.setImageURL("https://example.org/example");
        userModel1.setLname("Lname");
        userModel1.setPassword("iloveyou");
        userModel1.setPhone("4105551212");
        userModel1.setReservationModelBarber(new ArrayList<>());
        userModel1.setReservationModelClient(new ArrayList<>());
        userModel1.setRole("Role");
        userModel1.setUpdated(LocalDate.ofEpochDay(1L));
        Optional<UserModel> ofResult = Optional.of(userModel1);
        when(this.userRepository.findById(any())).thenReturn(ofResult);
        assertTrue(this.userServiceImpl.findUserById(123L).isPresent());
        verify(this.utilsUser).converterUserModelToUserDTO(any());
        verify(this.userRepository).findById(any());
    }

    @Test
    void testExistsUserByEmail() {
        when(this.userRepository.existsByEmail(any())).thenReturn(true);
        assertTrue(this.userServiceImpl.existsUserByEmail("jane.doe@example.org"));
        verify(this.userRepository).existsByEmail(any());
    }

    @Test
    void testExistsUserByEmail2() {
        when(this.userRepository.existsByEmail(any())).thenReturn(false);
        assertFalse(this.userServiceImpl.existsUserByEmail("jane.doe@example.org"));
        verify(this.userRepository).existsByEmail(any());
    }

    @Test
    void testExistsUserByEmail3() {
        when(this.userRepository.existsByEmail(any())).thenThrow(new UsernameNotFoundException("Msg"));
        assertThrows(UsernameNotFoundException.class, () -> this.userServiceImpl.existsUserByEmail("jane.doe@example.org"));
        verify(this.userRepository).existsByEmail(any());
    }

    @Test
    void testDeleteUserById() {
        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setId(123L);
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setUsers(new HashSet<>());
        addressModel.setZip("21654");

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setId(123L);
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setUsers(new HashSet<>());
        addressModel1.setZip("21654");

        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setCreated(null);
        credentialModel.setGrantedAuthority("JaneDoe");
        credentialModel.setId(123L);
        credentialModel.setPassword("iloveyou");
        credentialModel.setUpdated(null);
        credentialModel.setUser(new UserModel());
        credentialModel.setUsername("janedoe");

        UserModel userModel = new UserModel();
        userModel.setAddress(addressModel1);
        userModel.setCreated(LocalDate.ofEpochDay(1L));
        userModel.setCredential(credentialModel);
        userModel.setDeleted(true);
        userModel.setDob(LocalDate.ofEpochDay(1L));
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(123L);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setReservationModelBarber(new ArrayList<>());
        userModel.setReservationModelClient(new ArrayList<>());
        userModel.setRole("Role");
        userModel.setUpdated(LocalDate.ofEpochDay(1L));

        CredentialModel credentialModel1 = new CredentialModel();
        credentialModel1.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel1.setGrantedAuthority("JaneDoe");
        credentialModel1.setId(123L);
        credentialModel1.setPassword("iloveyou");
        credentialModel1.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel1.setUser(userModel);
        credentialModel1.setUsername("janedoe");

        UserModel userModel1 = new UserModel();
        userModel1.setAddress(addressModel);
        userModel1.setCreated(LocalDate.ofEpochDay(1L));
        userModel1.setCredential(credentialModel1);
        userModel1.setDeleted(true);
        userModel1.setDob(LocalDate.ofEpochDay(1L));
        userModel1.setEmail("jane.doe@example.org");
        userModel1.setFname("Fname");
        userModel1.setId(123L);
        userModel1.setImageURL("https://example.org/example");
        userModel1.setLname("Lname");
        userModel1.setPassword("iloveyou");
        userModel1.setPhone("4105551212");
        userModel1.setReservationModelBarber(new ArrayList<>());
        userModel1.setReservationModelClient(new ArrayList<>());
        userModel1.setRole("Role");
        userModel1.setUpdated(LocalDate.ofEpochDay(1L));
        Optional<UserModel> ofResult = Optional.of(userModel1);
        doNothing().when(this.userRepository).deleteById(any());
        when(this.userRepository.findById(any())).thenReturn(ofResult);
        doNothing().when(this.fileService).deleteImage(any(), any());
        this.userServiceImpl.deleteUserById(123L);
        verify(this.userRepository).findById(any());
        verify(this.userRepository).deleteById(any());
        verify(this.fileService).deleteImage(any(), any());
    }

    @Test
    void testDeleteUserById2() {
        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setId(123L);
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setUsers(new HashSet<>());
        addressModel.setZip("21654");

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setId(123L);
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setUsers(new HashSet<>());
        addressModel1.setZip("21654");

        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setCreated(null);
        credentialModel.setGrantedAuthority("JaneDoe");
        credentialModel.setId(123L);
        credentialModel.setPassword("iloveyou");
        credentialModel.setUpdated(null);
        credentialModel.setUser(new UserModel());
        credentialModel.setUsername("janedoe");

        UserModel userModel = new UserModel();
        userModel.setAddress(addressModel1);
        userModel.setCreated(LocalDate.ofEpochDay(1L));
        userModel.setCredential(credentialModel);
        userModel.setDeleted(true);
        userModel.setDob(LocalDate.ofEpochDay(1L));
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(123L);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setReservationModelBarber(new ArrayList<>());
        userModel.setReservationModelClient(new ArrayList<>());
        userModel.setRole("Role");
        userModel.setUpdated(LocalDate.ofEpochDay(1L));

        CredentialModel credentialModel1 = new CredentialModel();
        credentialModel1.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel1.setGrantedAuthority("JaneDoe");
        credentialModel1.setId(123L);
        credentialModel1.setPassword("iloveyou");
        credentialModel1.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel1.setUser(userModel);
        credentialModel1.setUsername("janedoe");

        UserModel userModel1 = new UserModel();
        userModel1.setAddress(addressModel);
        userModel1.setCreated(LocalDate.ofEpochDay(1L));
        userModel1.setCredential(credentialModel1);
        userModel1.setDeleted(true);
        userModel1.setDob(LocalDate.ofEpochDay(1L));
        userModel1.setEmail("jane.doe@example.org");
        userModel1.setFname("Fname");
        userModel1.setId(123L);
        userModel1.setImageURL("https://example.org/example");
        userModel1.setLname("Lname");
        userModel1.setPassword("iloveyou");
        userModel1.setPhone("4105551212");
        userModel1.setReservationModelBarber(new ArrayList<>());
        userModel1.setReservationModelClient(new ArrayList<>());
        userModel1.setRole("Role");
        userModel1.setUpdated(LocalDate.ofEpochDay(1L));
        Optional<UserModel> ofResult = Optional.of(userModel1);
        doNothing().when(this.userRepository).deleteById(any());
        when(this.userRepository.findById(any())).thenReturn(ofResult);
        doThrow(new UsernameNotFoundException("Msg")).when(this.fileService).deleteImage(any(), any());
        assertThrows(UsernameNotFoundException.class, () -> this.userServiceImpl.deleteUserById(123L));
        verify(this.userRepository).findById(any());
        verify(this.userRepository).deleteById(any());
        verify(this.fileService).deleteImage(any(), any());
    }

    @Test
    void testDeleteUserById3() {
        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setId(123L);
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setUsers(new HashSet<>());
        addressModel.setZip("21654");

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setId(123L);
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setUsers(new HashSet<>());
        addressModel1.setZip("21654");

        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setCreated(null);
        credentialModel.setGrantedAuthority("JaneDoe");
        credentialModel.setId(123L);
        credentialModel.setPassword("iloveyou");
        credentialModel.setUpdated(null);
        credentialModel.setUser(new UserModel());
        credentialModel.setUsername("janedoe");

        UserModel userModel = new UserModel();
        userModel.setAddress(addressModel1);
        userModel.setCreated(LocalDate.ofEpochDay(1L));
        userModel.setCredential(credentialModel);
        userModel.setDeleted(true);
        userModel.setDob(LocalDate.ofEpochDay(1L));
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("Fname");
        userModel.setId(123L);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("Lname");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setReservationModelBarber(new ArrayList<>());
        userModel.setReservationModelClient(new ArrayList<>());
        userModel.setRole("Role");
        userModel.setUpdated(LocalDate.ofEpochDay(1L));

        CredentialModel credentialModel1 = new CredentialModel();
        credentialModel1.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel1.setGrantedAuthority("JaneDoe");
        credentialModel1.setId(123L);
        credentialModel1.setPassword("iloveyou");
        credentialModel1.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel1.setUser(userModel);
        credentialModel1.setUsername("janedoe");
        UserModel userModel1 = mock(UserModel.class);
        when(userModel1.getImageURL()).thenReturn("https://example.org/example");
        doNothing().when(userModel1).setAddress(any());
        doNothing().when(userModel1).setCreated(any());
        doNothing().when(userModel1).setCredential(any());
        doNothing().when(userModel1).setDeleted(anyBoolean());
        doNothing().when(userModel1).setDob(any());
        doNothing().when(userModel1).setEmail(any());
        doNothing().when(userModel1).setFname(any());
        doNothing().when(userModel1).setId(any());
        doNothing().when(userModel1).setImageURL(any());
        doNothing().when(userModel1).setLname(any());
        doNothing().when(userModel1).setPassword(any());
        doNothing().when(userModel1).setPhone(any());
        doNothing().when(userModel1).setReservationModelBarber(any());
        doNothing().when(userModel1).setReservationModelClient(any());
        doNothing().when(userModel1).setRole(any());
        doNothing().when(userModel1).setUpdated(any());
        userModel1.setAddress(addressModel);
        userModel1.setCreated(LocalDate.ofEpochDay(1L));
        userModel1.setCredential(credentialModel1);
        userModel1.setDeleted(true);
        userModel1.setDob(LocalDate.ofEpochDay(1L));
        userModel1.setEmail("jane.doe@example.org");
        userModel1.setFname("Fname");
        userModel1.setId(123L);
        userModel1.setImageURL("https://example.org/example");
        userModel1.setLname("Lname");
        userModel1.setPassword("iloveyou");
        userModel1.setPhone("4105551212");
        userModel1.setReservationModelBarber(new ArrayList<>());
        userModel1.setReservationModelClient(new ArrayList<>());
        userModel1.setRole("Role");
        userModel1.setUpdated(LocalDate.ofEpochDay(1L));
        Optional<UserModel> ofResult = Optional.of(userModel1);
        doNothing().when(this.userRepository).deleteById(any());
        when(this.userRepository.findById(any())).thenReturn(ofResult);
        doNothing().when(this.fileService).deleteImage(any(), any());
        this.userServiceImpl.deleteUserById(123L);
        verify(this.userRepository).findById(any());
        verify(this.userRepository).deleteById(any());
        verify(userModel1).getImageURL();
        verify(userModel1).setAddress(any());
        verify(userModel1).setCreated(any());
        verify(userModel1).setCredential(any());
        verify(userModel1).setDeleted(anyBoolean());
        verify(userModel1).setDob(any());
        verify(userModel1).setEmail(any());
        verify(userModel1).setFname(any());
        verify(userModel1).setId(any());
        verify(userModel1).setImageURL(any());
        verify(userModel1).setLname(any());
        verify(userModel1).setPassword(any());
        verify(userModel1).setPhone(any());
        verify(userModel1).setReservationModelBarber(any());
        verify(userModel1).setReservationModelClient(any());
        verify(userModel1).setRole(any());
        verify(userModel1).setUpdated(any());
        verify(this.fileService).deleteImage(any(), any());
    }
}

