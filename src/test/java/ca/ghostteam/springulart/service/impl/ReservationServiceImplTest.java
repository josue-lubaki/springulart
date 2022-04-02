package ca.ghostteam.springulart.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ghostteam.springulart.dto.AddressDTO;
import ca.ghostteam.springulart.dto.HaircutDTO;
import ca.ghostteam.springulart.dto.LocationDTO;
import ca.ghostteam.springulart.dto.ReservationDTO;
import ca.ghostteam.springulart.dto.ReservationTimeDTO;
import ca.ghostteam.springulart.dto.UserDTO;
import ca.ghostteam.springulart.model.AddressModel;
import ca.ghostteam.springulart.model.CredentialModel;
import ca.ghostteam.springulart.model.HaircutModel;
import ca.ghostteam.springulart.model.LocationModel;
import ca.ghostteam.springulart.model.ReservationModel;
import ca.ghostteam.springulart.model.ReservationTimeModel;
import ca.ghostteam.springulart.model.UserModel;
import ca.ghostteam.springulart.repository.AddressRepository;
import ca.ghostteam.springulart.repository.CredentialRepository;
import ca.ghostteam.springulart.repository.HaircutRepository;
import ca.ghostteam.springulart.repository.LocationRepository;
import ca.ghostteam.springulart.repository.ReservationRepository;
import ca.ghostteam.springulart.repository.ReservationTimeRepository;
import ca.ghostteam.springulart.repository.UserRepository;
import ca.ghostteam.springulart.service.address.AddressServiceImpl;
import ca.ghostteam.springulart.service.credential.CredentialServiceImpl;
import ca.ghostteam.springulart.service.haircut.HaircutService;
import ca.ghostteam.springulart.service.location.LocationService;
import ca.ghostteam.springulart.service.reservationtime.ReservationTimeService;
import ca.ghostteam.springulart.service.haircut.HaircutServiceImpl;
import ca.ghostteam.springulart.service.location.LocationServiceImpl;
import ca.ghostteam.springulart.service.reservation.ReservationServiceImpl;
import ca.ghostteam.springulart.service.reservationtime.ReservationTimeServiceImpl;
import ca.ghostteam.springulart.service.user.UserService;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import ca.ghostteam.springulart.service.user.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ReservationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ReservationServiceImplTest {
    @MockBean
    private HaircutService haircutService;

    @MockBean
    private LocationService locationService;

    @MockBean
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationServiceImpl reservationServiceImpl;

    @MockBean
    private ReservationTimeService reservationTimeService;

    @MockBean
    private UserService userService;

    @Test
    void testFindAll() {
        when(this.reservationRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(this.reservationServiceImpl.findAll().isEmpty());
        verify(this.reservationRepository).findAll();
    }

    @Test
    void testFindAll2() {
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
        userModel.setReservationModelBarber(new HashSet<>());
        userModel.setReservationModelClient(new HashSet<>());
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
        userModel1.setReservationModelBarber(new HashSet<>());
        userModel1.setReservationModelClient(new HashSet<>());
        userModel1.setRole("Role");
        userModel1.setUpdated(LocalDate.ofEpochDay(1L));

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setId(123L);
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setUsers(new HashSet<>());
        addressModel1.setZip("21654");

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
        userModel2.setReservationModelBarber(new HashSet<>());
        userModel2.setReservationModelClient(new HashSet<>());
        userModel2.setRole("Role");
        userModel2.setUpdated(null);

        CredentialModel credentialModel1 = new CredentialModel();
        credentialModel1.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel1.setGrantedAuthority("JaneDoe");
        credentialModel1.setId(123L);
        credentialModel1.setPassword("iloveyou");
        credentialModel1.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel1.setUser(userModel2);
        credentialModel1.setUsername("janedoe");

        UserModel userModel3 = new UserModel();
        userModel3.setAddress(addressModel1);
        userModel3.setCreated(LocalDate.ofEpochDay(1L));
        userModel3.setCredential(credentialModel1);
        userModel3.setDeleted(true);
        userModel3.setDob(LocalDate.ofEpochDay(1L));
        userModel3.setEmail("jane.doe@example.org");
        userModel3.setFname("Fname");
        userModel3.setId(123L);
        userModel3.setImageURL("https://example.org/example");
        userModel3.setLname("Lname");
        userModel3.setPassword("iloveyou");
        userModel3.setPhone("4105551212");
        userModel3.setReservationModelBarber(new HashSet<>());
        userModel3.setReservationModelClient(new HashSet<>());
        userModel3.setRole("Role");
        userModel3.setUpdated(LocalDate.ofEpochDay(1L));

        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setReservationModel(new HashSet<>());
        haircutModel.setTitle("Dr");

        UserModel userModel4 = new UserModel();
        userModel4.setAddress(new AddressModel());
        userModel4.setCreated(null);
        userModel4.setCredential(new CredentialModel());
        userModel4.setDeleted(true);
        userModel4.setDob(null);
        userModel4.setEmail("jane.doe@example.org");
        userModel4.setFname("Fname");
        userModel4.setId(123L);
        userModel4.setImageURL("https://example.org/example");
        userModel4.setLname("Lname");
        userModel4.setPassword("iloveyou");
        userModel4.setPhone("4105551212");
        userModel4.setReservationModelBarber(new HashSet<>());
        userModel4.setReservationModelClient(new HashSet<>());
        userModel4.setRole("Role");
        userModel4.setUpdated(null);

        UserModel userModel5 = new UserModel();
        userModel5.setAddress(new AddressModel());
        userModel5.setCreated(null);
        userModel5.setCredential(new CredentialModel());
        userModel5.setDeleted(true);
        userModel5.setDob(null);
        userModel5.setEmail("jane.doe@example.org");
        userModel5.setFname("Fname");
        userModel5.setId(123L);
        userModel5.setImageURL("https://example.org/example");
        userModel5.setLname("Lname");
        userModel5.setPassword("iloveyou");
        userModel5.setPhone("4105551212");
        userModel5.setReservationModelBarber(new HashSet<>());
        userModel5.setReservationModelClient(new HashSet<>());
        userModel5.setRole("Role");
        userModel5.setUpdated(null);

        HaircutModel haircutModel1 = new HaircutModel();
        haircutModel1.setDescription("The characteristics of someone or something");
        haircutModel1.setEstimatedTime("Estimated Time");
        haircutModel1.setId("42");
        haircutModel1.setImageURL("https://example.org/example");
        haircutModel1.setPrice(1);
        haircutModel1.setReservationModel(new HashSet<>());
        haircutModel1.setTitle("Dr");

        LocationModel locationModel = new LocationModel();
        locationModel.setId(123L);
        locationModel.setLatitude(10.0d);
        locationModel.setLongitude(10.0d);
        locationModel.setReservationModel(new ReservationModel());

        ReservationTimeModel reservationTimeModel = new ReservationTimeModel();
        reservationTimeModel.setHours(1);
        reservationTimeModel.setId(123L);
        reservationTimeModel.setMinutes(1);
        reservationTimeModel.setReservationModel(new HashSet<>());

        ReservationModel reservationModel = new ReservationModel();
        reservationModel.setBarber(userModel4);
        reservationModel.setClient(userModel5);
        reservationModel.setHaircut(haircutModel1);
        reservationModel.setId(123L);
        reservationModel.setLocation(locationModel);
        reservationModel.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationModel.setReservationTime(reservationTimeModel);
        reservationModel.setStatus("Status");

        LocationModel locationModel1 = new LocationModel();
        locationModel1.setId(123L);
        locationModel1.setLatitude(10.0d);
        locationModel1.setLongitude(10.0d);
        locationModel1.setReservationModel(reservationModel);

        ReservationTimeModel reservationTimeModel1 = new ReservationTimeModel();
        reservationTimeModel1.setHours(1);
        reservationTimeModel1.setId(123L);
        reservationTimeModel1.setMinutes(1);
        reservationTimeModel1.setReservationModel(new HashSet<>());

        ReservationModel reservationModel1 = new ReservationModel();
        reservationModel1.setBarber(userModel1);
        reservationModel1.setClient(userModel3);
        reservationModel1.setHaircut(haircutModel);
        reservationModel1.setId(123L);
        reservationModel1.setLocation(locationModel1);
        reservationModel1.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationModel1.setReservationTime(reservationTimeModel1);
        reservationModel1.setStatus("Status");

        ArrayList<ReservationModel> reservationModelList = new ArrayList<>();
        reservationModelList.add(reservationModel1);
        when(this.reservationRepository.findAll()).thenReturn(reservationModelList);
        assertEquals(1, this.reservationServiceImpl.findAll().size());
        verify(this.reservationRepository).findAll();
    }

    @Test
    void testFindAll3() {
        when(this.reservationRepository.findAll()).thenThrow(new IllegalStateException("foo"));
        assertThrows(IllegalStateException.class, () -> this.reservationServiceImpl.findAll());
        verify(this.reservationRepository).findAll();
    }

    @Test
    void testFindAll4() {
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
        userModel.setReservationModelBarber(new HashSet<>());
        userModel.setReservationModelClient(new HashSet<>());
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
        userModel1.setReservationModelBarber(new HashSet<>());
        userModel1.setReservationModelClient(new HashSet<>());
        userModel1.setRole("Role");
        userModel1.setUpdated(LocalDate.ofEpochDay(1L));

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setId(123L);
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setUsers(new HashSet<>());
        addressModel1.setZip("21654");

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
        userModel2.setReservationModelBarber(new HashSet<>());
        userModel2.setReservationModelClient(new HashSet<>());
        userModel2.setRole("Role");
        userModel2.setUpdated(null);

        CredentialModel credentialModel1 = new CredentialModel();
        credentialModel1.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel1.setGrantedAuthority("JaneDoe");
        credentialModel1.setId(123L);
        credentialModel1.setPassword("iloveyou");
        credentialModel1.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel1.setUser(userModel2);
        credentialModel1.setUsername("janedoe");

        UserModel userModel3 = new UserModel();
        userModel3.setAddress(addressModel1);
        userModel3.setCreated(LocalDate.ofEpochDay(1L));
        userModel3.setCredential(credentialModel1);
        userModel3.setDeleted(true);
        userModel3.setDob(LocalDate.ofEpochDay(1L));
        userModel3.setEmail("jane.doe@example.org");
        userModel3.setFname("Fname");
        userModel3.setId(123L);
        userModel3.setImageURL("https://example.org/example");
        userModel3.setLname("Lname");
        userModel3.setPassword("iloveyou");
        userModel3.setPhone("4105551212");
        userModel3.setReservationModelBarber(new HashSet<>());
        userModel3.setReservationModelClient(new HashSet<>());
        userModel3.setRole("Role");
        userModel3.setUpdated(LocalDate.ofEpochDay(1L));

        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setReservationModel(new HashSet<>());
        haircutModel.setTitle("Dr");

        UserModel userModel4 = new UserModel();
        userModel4.setAddress(new AddressModel());
        userModel4.setCreated(null);
        userModel4.setCredential(new CredentialModel());
        userModel4.setDeleted(true);
        userModel4.setDob(null);
        userModel4.setEmail("jane.doe@example.org");
        userModel4.setFname("Fname");
        userModel4.setId(123L);
        userModel4.setImageURL("https://example.org/example");
        userModel4.setLname("Lname");
        userModel4.setPassword("iloveyou");
        userModel4.setPhone("4105551212");
        userModel4.setReservationModelBarber(new HashSet<>());
        userModel4.setReservationModelClient(new HashSet<>());
        userModel4.setRole("Role");
        userModel4.setUpdated(null);

        UserModel userModel5 = new UserModel();
        userModel5.setAddress(new AddressModel());
        userModel5.setCreated(null);
        userModel5.setCredential(new CredentialModel());
        userModel5.setDeleted(true);
        userModel5.setDob(null);
        userModel5.setEmail("jane.doe@example.org");
        userModel5.setFname("Fname");
        userModel5.setId(123L);
        userModel5.setImageURL("https://example.org/example");
        userModel5.setLname("Lname");
        userModel5.setPassword("iloveyou");
        userModel5.setPhone("4105551212");
        userModel5.setReservationModelBarber(new HashSet<>());
        userModel5.setReservationModelClient(new HashSet<>());
        userModel5.setRole("Role");
        userModel5.setUpdated(null);

        HaircutModel haircutModel1 = new HaircutModel();
        haircutModel1.setDescription("The characteristics of someone or something");
        haircutModel1.setEstimatedTime("Estimated Time");
        haircutModel1.setId("42");
        haircutModel1.setImageURL("https://example.org/example");
        haircutModel1.setPrice(1);
        haircutModel1.setReservationModel(new HashSet<>());
        haircutModel1.setTitle("Dr");

        LocationModel locationModel = new LocationModel();
        locationModel.setId(123L);
        locationModel.setLatitude(10.0d);
        locationModel.setLongitude(10.0d);
        locationModel.setReservationModel(new ReservationModel());

        ReservationTimeModel reservationTimeModel = new ReservationTimeModel();
        reservationTimeModel.setHours(1);
        reservationTimeModel.setId(123L);
        reservationTimeModel.setMinutes(1);
        reservationTimeModel.setReservationModel(new HashSet<>());

        ReservationModel reservationModel = new ReservationModel();
        reservationModel.setBarber(userModel4);
        reservationModel.setClient(userModel5);
        reservationModel.setHaircut(haircutModel1);
        reservationModel.setId(123L);
        reservationModel.setLocation(locationModel);
        reservationModel.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationModel.setReservationTime(reservationTimeModel);
        reservationModel.setStatus("Status");

        LocationModel locationModel1 = new LocationModel();
        locationModel1.setId(123L);
        locationModel1.setLatitude(10.0d);
        locationModel1.setLongitude(10.0d);
        locationModel1.setReservationModel(reservationModel);

        ReservationTimeModel reservationTimeModel1 = new ReservationTimeModel();
        reservationTimeModel1.setHours(1);
        reservationTimeModel1.setId(123L);
        reservationTimeModel1.setMinutes(1);
        reservationTimeModel1.setReservationModel(new HashSet<>());

        ReservationModel reservationModel1 = new ReservationModel();
        reservationModel1.setBarber(userModel1);
        reservationModel1.setClient(userModel3);
        reservationModel1.setHaircut(haircutModel);
        reservationModel1.setId(123L);
        reservationModel1.setLocation(locationModel1);
        reservationModel1.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationModel1.setReservationTime(reservationTimeModel1);
        reservationModel1.setStatus("Status");

        AddressModel addressModel2 = new AddressModel();
        addressModel2.setApartement("Apartement");
        addressModel2.setCity("Oxford");
        addressModel2.setId(123L);
        addressModel2.setState("MD");
        addressModel2.setStreet("Street");
        addressModel2.setUsers(new HashSet<>());
        addressModel2.setZip("21654");

        UserModel userModel6 = new UserModel();
        userModel6.setAddress(new AddressModel());
        userModel6.setCreated(null);
        userModel6.setCredential(new CredentialModel());
        userModel6.setDeleted(true);
        userModel6.setDob(null);
        userModel6.setEmail("jane.doe@example.org");
        userModel6.setFname("Fname");
        userModel6.setId(123L);
        userModel6.setImageURL("https://example.org/example");
        userModel6.setLname("Lname");
        userModel6.setPassword("iloveyou");
        userModel6.setPhone("4105551212");
        userModel6.setReservationModelBarber(new HashSet<>());
        userModel6.setReservationModelClient(new HashSet<>());
        userModel6.setRole("Role");
        userModel6.setUpdated(null);

        CredentialModel credentialModel2 = new CredentialModel();
        credentialModel2.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel2.setGrantedAuthority("JaneDoe");
        credentialModel2.setId(123L);
        credentialModel2.setPassword("iloveyou");
        credentialModel2.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel2.setUser(userModel6);
        credentialModel2.setUsername("janedoe");

        UserModel userModel7 = new UserModel();
        userModel7.setAddress(addressModel2);
        userModel7.setCreated(LocalDate.ofEpochDay(1L));
        userModel7.setCredential(credentialModel2);
        userModel7.setDeleted(true);
        userModel7.setDob(LocalDate.ofEpochDay(1L));
        userModel7.setEmail("jane.doe@example.org");
        userModel7.setFname("Fname");
        userModel7.setId(123L);
        userModel7.setImageURL("https://example.org/example");
        userModel7.setLname("Lname");
        userModel7.setPassword("iloveyou");
        userModel7.setPhone("4105551212");
        userModel7.setReservationModelBarber(new HashSet<>());
        userModel7.setReservationModelClient(new HashSet<>());
        userModel7.setRole("Role");
        userModel7.setUpdated(LocalDate.ofEpochDay(1L));

        AddressModel addressModel3 = new AddressModel();
        addressModel3.setApartement("Apartement");
        addressModel3.setCity("Oxford");
        addressModel3.setId(123L);
        addressModel3.setState("MD");
        addressModel3.setStreet("Street");
        addressModel3.setUsers(new HashSet<>());
        addressModel3.setZip("21654");

        UserModel userModel8 = new UserModel();
        userModel8.setAddress(new AddressModel());
        userModel8.setCreated(null);
        userModel8.setCredential(new CredentialModel());
        userModel8.setDeleted(true);
        userModel8.setDob(null);
        userModel8.setEmail("jane.doe@example.org");
        userModel8.setFname("Fname");
        userModel8.setId(123L);
        userModel8.setImageURL("https://example.org/example");
        userModel8.setLname("Lname");
        userModel8.setPassword("iloveyou");
        userModel8.setPhone("4105551212");
        userModel8.setReservationModelBarber(new HashSet<>());
        userModel8.setReservationModelClient(new HashSet<>());
        userModel8.setRole("Role");
        userModel8.setUpdated(null);

        CredentialModel credentialModel3 = new CredentialModel();
        credentialModel3.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel3.setGrantedAuthority("JaneDoe");
        credentialModel3.setId(123L);
        credentialModel3.setPassword("iloveyou");
        credentialModel3.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel3.setUser(userModel8);
        credentialModel3.setUsername("janedoe");

        UserModel userModel9 = new UserModel();
        userModel9.setAddress(addressModel3);
        userModel9.setCreated(LocalDate.ofEpochDay(1L));
        userModel9.setCredential(credentialModel3);
        userModel9.setDeleted(true);
        userModel9.setDob(LocalDate.ofEpochDay(1L));
        userModel9.setEmail("jane.doe@example.org");
        userModel9.setFname("Fname");
        userModel9.setId(123L);
        userModel9.setImageURL("https://example.org/example");
        userModel9.setLname("Lname");
        userModel9.setPassword("iloveyou");
        userModel9.setPhone("4105551212");
        userModel9.setReservationModelBarber(new HashSet<>());
        userModel9.setReservationModelClient(new HashSet<>());
        userModel9.setRole("Role");
        userModel9.setUpdated(LocalDate.ofEpochDay(1L));

        HaircutModel haircutModel2 = new HaircutModel();
        haircutModel2.setDescription("The characteristics of someone or something");
        haircutModel2.setEstimatedTime("Estimated Time");
        haircutModel2.setId("42");
        haircutModel2.setImageURL("https://example.org/example");
        haircutModel2.setPrice(1);
        haircutModel2.setReservationModel(new HashSet<>());
        haircutModel2.setTitle("Dr");

        UserModel userModel10 = new UserModel();
        userModel10.setAddress(new AddressModel());
        userModel10.setCreated(null);
        userModel10.setCredential(new CredentialModel());
        userModel10.setDeleted(true);
        userModel10.setDob(null);
        userModel10.setEmail("jane.doe@example.org");
        userModel10.setFname("Fname");
        userModel10.setId(123L);
        userModel10.setImageURL("https://example.org/example");
        userModel10.setLname("Lname");
        userModel10.setPassword("iloveyou");
        userModel10.setPhone("4105551212");
        userModel10.setReservationModelBarber(new HashSet<>());
        userModel10.setReservationModelClient(new HashSet<>());
        userModel10.setRole("Role");
        userModel10.setUpdated(null);

        UserModel userModel11 = new UserModel();
        userModel11.setAddress(new AddressModel());
        userModel11.setCreated(null);
        userModel11.setCredential(new CredentialModel());
        userModel11.setDeleted(true);
        userModel11.setDob(null);
        userModel11.setEmail("jane.doe@example.org");
        userModel11.setFname("Fname");
        userModel11.setId(123L);
        userModel11.setImageURL("https://example.org/example");
        userModel11.setLname("Lname");
        userModel11.setPassword("iloveyou");
        userModel11.setPhone("4105551212");
        userModel11.setReservationModelBarber(new HashSet<>());
        userModel11.setReservationModelClient(new HashSet<>());
        userModel11.setRole("Role");
        userModel11.setUpdated(null);

        HaircutModel haircutModel3 = new HaircutModel();
        haircutModel3.setDescription("The characteristics of someone or something");
        haircutModel3.setEstimatedTime("Estimated Time");
        haircutModel3.setId("42");
        haircutModel3.setImageURL("https://example.org/example");
        haircutModel3.setPrice(1);
        haircutModel3.setReservationModel(new HashSet<>());
        haircutModel3.setTitle("Dr");

        LocationModel locationModel2 = new LocationModel();
        locationModel2.setId(123L);
        locationModel2.setLatitude(10.0d);
        locationModel2.setLongitude(10.0d);
        locationModel2.setReservationModel(new ReservationModel());

        ReservationTimeModel reservationTimeModel2 = new ReservationTimeModel();
        reservationTimeModel2.setHours(1);
        reservationTimeModel2.setId(123L);
        reservationTimeModel2.setMinutes(1);
        reservationTimeModel2.setReservationModel(new HashSet<>());

        ReservationModel reservationModel2 = new ReservationModel();
        reservationModel2.setBarber(userModel10);
        reservationModel2.setClient(userModel11);
        reservationModel2.setHaircut(haircutModel3);
        reservationModel2.setId(123L);
        reservationModel2.setLocation(locationModel2);
        reservationModel2.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationModel2.setReservationTime(reservationTimeModel2);
        reservationModel2.setStatus("Status");

        LocationModel locationModel3 = new LocationModel();
        locationModel3.setId(123L);
        locationModel3.setLatitude(10.0d);
        locationModel3.setLongitude(10.0d);
        locationModel3.setReservationModel(reservationModel2);

        ReservationTimeModel reservationTimeModel3 = new ReservationTimeModel();
        reservationTimeModel3.setHours(1);
        reservationTimeModel3.setId(123L);
        reservationTimeModel3.setMinutes(1);
        reservationTimeModel3.setReservationModel(new HashSet<>());

        ReservationModel reservationModel3 = new ReservationModel();
        reservationModel3.setBarber(userModel7);
        reservationModel3.setClient(userModel9);
        reservationModel3.setHaircut(haircutModel2);
        reservationModel3.setId(123L);
        reservationModel3.setLocation(locationModel3);
        reservationModel3.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationModel3.setReservationTime(reservationTimeModel3);
        reservationModel3.setStatus("Status");

        ArrayList<ReservationModel> reservationModelList = new ArrayList<>();
        reservationModelList.add(reservationModel3);
        reservationModelList.add(reservationModel1);
        when(this.reservationRepository.findAll()).thenReturn(reservationModelList);
        assertEquals(2, this.reservationServiceImpl.findAll().size());
        verify(this.reservationRepository).findAll();
    }

    @Test
    void testFindAll5() {
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
        userModel.setReservationModelBarber(new HashSet<>());
        userModel.setReservationModelClient(new HashSet<>());
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
        userModel1.setReservationModelBarber(new HashSet<>());
        userModel1.setReservationModelClient(new HashSet<>());
        userModel1.setRole("Role");
        userModel1.setUpdated(LocalDate.ofEpochDay(1L));

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setId(123L);
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setUsers(new HashSet<>());
        addressModel1.setZip("21654");

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
        userModel2.setReservationModelBarber(new HashSet<>());
        userModel2.setReservationModelClient(new HashSet<>());
        userModel2.setRole("Role");
        userModel2.setUpdated(null);

        CredentialModel credentialModel1 = new CredentialModel();
        credentialModel1.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel1.setGrantedAuthority("JaneDoe");
        credentialModel1.setId(123L);
        credentialModel1.setPassword("iloveyou");
        credentialModel1.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel1.setUser(userModel2);
        credentialModel1.setUsername("janedoe");

        UserModel userModel3 = new UserModel();
        userModel3.setAddress(addressModel1);
        userModel3.setCreated(LocalDate.ofEpochDay(1L));
        userModel3.setCredential(credentialModel1);
        userModel3.setDeleted(true);
        userModel3.setDob(LocalDate.ofEpochDay(1L));
        userModel3.setEmail("jane.doe@example.org");
        userModel3.setFname("Fname");
        userModel3.setId(123L);
        userModel3.setImageURL("https://example.org/example");
        userModel3.setLname("Lname");
        userModel3.setPassword("iloveyou");
        userModel3.setPhone("4105551212");
        userModel3.setReservationModelBarber(new HashSet<>());
        userModel3.setReservationModelClient(new HashSet<>());
        userModel3.setRole("Role");
        userModel3.setUpdated(LocalDate.ofEpochDay(1L));

        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setReservationModel(new HashSet<>());
        haircutModel.setTitle("Dr");

        UserModel userModel4 = new UserModel();
        userModel4.setAddress(new AddressModel());
        userModel4.setCreated(null);
        userModel4.setCredential(new CredentialModel());
        userModel4.setDeleted(true);
        userModel4.setDob(null);
        userModel4.setEmail("jane.doe@example.org");
        userModel4.setFname("Fname");
        userModel4.setId(123L);
        userModel4.setImageURL("https://example.org/example");
        userModel4.setLname("Lname");
        userModel4.setPassword("iloveyou");
        userModel4.setPhone("4105551212");
        userModel4.setReservationModelBarber(new HashSet<>());
        userModel4.setReservationModelClient(new HashSet<>());
        userModel4.setRole("Role");
        userModel4.setUpdated(null);

        UserModel userModel5 = new UserModel();
        userModel5.setAddress(new AddressModel());
        userModel5.setCreated(null);
        userModel5.setCredential(new CredentialModel());
        userModel5.setDeleted(true);
        userModel5.setDob(null);
        userModel5.setEmail("jane.doe@example.org");
        userModel5.setFname("Fname");
        userModel5.setId(123L);
        userModel5.setImageURL("https://example.org/example");
        userModel5.setLname("Lname");
        userModel5.setPassword("iloveyou");
        userModel5.setPhone("4105551212");
        userModel5.setReservationModelBarber(new HashSet<>());
        userModel5.setReservationModelClient(new HashSet<>());
        userModel5.setRole("Role");
        userModel5.setUpdated(null);

        HaircutModel haircutModel1 = new HaircutModel();
        haircutModel1.setDescription("The characteristics of someone or something");
        haircutModel1.setEstimatedTime("Estimated Time");
        haircutModel1.setId("42");
        haircutModel1.setImageURL("https://example.org/example");
        haircutModel1.setPrice(1);
        haircutModel1.setReservationModel(new HashSet<>());
        haircutModel1.setTitle("Dr");

        LocationModel locationModel = new LocationModel();
        locationModel.setId(123L);
        locationModel.setLatitude(10.0d);
        locationModel.setLongitude(10.0d);
        locationModel.setReservationModel(new ReservationModel());

        ReservationTimeModel reservationTimeModel = new ReservationTimeModel();
        reservationTimeModel.setHours(1);
        reservationTimeModel.setId(123L);
        reservationTimeModel.setMinutes(1);
        reservationTimeModel.setReservationModel(new HashSet<>());

        ReservationModel reservationModel = new ReservationModel();
        reservationModel.setBarber(userModel4);
        reservationModel.setClient(userModel5);
        reservationModel.setHaircut(haircutModel1);
        reservationModel.setId(123L);
        reservationModel.setLocation(locationModel);
        reservationModel.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationModel.setReservationTime(reservationTimeModel);
        reservationModel.setStatus("Status");

        LocationModel locationModel1 = new LocationModel();
        locationModel1.setId(123L);
        locationModel1.setLatitude(10.0d);
        locationModel1.setLongitude(10.0d);
        locationModel1.setReservationModel(reservationModel);

        ReservationTimeModel reservationTimeModel1 = new ReservationTimeModel();
        reservationTimeModel1.setHours(1);
        reservationTimeModel1.setId(123L);
        reservationTimeModel1.setMinutes(1);
        reservationTimeModel1.setReservationModel(new HashSet<>());

        ReservationTimeModel reservationTimeModel2 = new ReservationTimeModel();
        reservationTimeModel2.setHours(1);
        reservationTimeModel2.setId(123L);
        reservationTimeModel2.setMinutes(1);
        reservationTimeModel2.setReservationModel(new HashSet<>());
        ReservationModel reservationModel1 = mock(ReservationModel.class);
        when(reservationModel1.getClient()).thenThrow(new IllegalStateException("foo"));
        when(reservationModel1.getReservationTime()).thenReturn(reservationTimeModel2);
        doNothing().when(reservationModel1).setBarber(any());
        doNothing().when(reservationModel1).setClient(any());
        doNothing().when(reservationModel1).setHaircut(any());
        doNothing().when(reservationModel1).setId(any());
        doNothing().when(reservationModel1).setLocation(any());
        doNothing().when(reservationModel1).setReservationDate(any());
        doNothing().when(reservationModel1).setReservationTime(any());
        doNothing().when(reservationModel1).setStatus(any());
        reservationModel1.setBarber(userModel1);
        reservationModel1.setClient(userModel3);
        reservationModel1.setHaircut(haircutModel);
        reservationModel1.setId(123L);
        reservationModel1.setLocation(locationModel1);
        reservationModel1.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationModel1.setReservationTime(reservationTimeModel1);
        reservationModel1.setStatus("Status");

        ArrayList<ReservationModel> reservationModelList = new ArrayList<>();
        reservationModelList.add(reservationModel1);
        when(this.reservationRepository.findAll()).thenReturn(reservationModelList);
        assertThrows(IllegalStateException.class, () -> this.reservationServiceImpl.findAll());
        verify(this.reservationRepository).findAll();
        verify(reservationModel1).getReservationTime();
        verify(reservationModel1).getClient();
        verify(reservationModel1).setBarber(any());
        verify(reservationModel1).setClient(any());
        verify(reservationModel1).setHaircut(any());
        verify(reservationModel1).setId(any());
        verify(reservationModel1).setLocation(any());
        verify(reservationModel1).setReservationDate(any());
        verify(reservationModel1).setReservationTime(any());
        verify(reservationModel1).setStatus(any());
    }

    @Test
    void testFindById() {
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
        userModel.setReservationModelBarber(new HashSet<>());
        userModel.setReservationModelClient(new HashSet<>());
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
        userModel1.setReservationModelBarber(new HashSet<>());
        userModel1.setReservationModelClient(new HashSet<>());
        userModel1.setRole("Role");
        userModel1.setUpdated(LocalDate.ofEpochDay(1L));

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setId(123L);
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setUsers(new HashSet<>());
        addressModel1.setZip("21654");

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
        userModel2.setReservationModelBarber(new HashSet<>());
        userModel2.setReservationModelClient(new HashSet<>());
        userModel2.setRole("Role");
        userModel2.setUpdated(null);

        CredentialModel credentialModel1 = new CredentialModel();
        credentialModel1.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel1.setGrantedAuthority("JaneDoe");
        credentialModel1.setId(123L);
        credentialModel1.setPassword("iloveyou");
        credentialModel1.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel1.setUser(userModel2);
        credentialModel1.setUsername("janedoe");

        UserModel userModel3 = new UserModel();
        userModel3.setAddress(addressModel1);
        userModel3.setCreated(LocalDate.ofEpochDay(1L));
        userModel3.setCredential(credentialModel1);
        userModel3.setDeleted(true);
        userModel3.setDob(LocalDate.ofEpochDay(1L));
        userModel3.setEmail("jane.doe@example.org");
        userModel3.setFname("Fname");
        userModel3.setId(123L);
        userModel3.setImageURL("https://example.org/example");
        userModel3.setLname("Lname");
        userModel3.setPassword("iloveyou");
        userModel3.setPhone("4105551212");
        userModel3.setReservationModelBarber(new HashSet<>());
        userModel3.setReservationModelClient(new HashSet<>());
        userModel3.setRole("Role");
        userModel3.setUpdated(LocalDate.ofEpochDay(1L));

        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setReservationModel(new HashSet<>());
        haircutModel.setTitle("Dr");

        UserModel userModel4 = new UserModel();
        userModel4.setAddress(new AddressModel());
        userModel4.setCreated(null);
        userModel4.setCredential(new CredentialModel());
        userModel4.setDeleted(true);
        userModel4.setDob(null);
        userModel4.setEmail("jane.doe@example.org");
        userModel4.setFname("Fname");
        userModel4.setId(123L);
        userModel4.setImageURL("https://example.org/example");
        userModel4.setLname("Lname");
        userModel4.setPassword("iloveyou");
        userModel4.setPhone("4105551212");
        userModel4.setReservationModelBarber(new HashSet<>());
        userModel4.setReservationModelClient(new HashSet<>());
        userModel4.setRole("Role");
        userModel4.setUpdated(null);

        UserModel userModel5 = new UserModel();
        userModel5.setAddress(new AddressModel());
        userModel5.setCreated(null);
        userModel5.setCredential(new CredentialModel());
        userModel5.setDeleted(true);
        userModel5.setDob(null);
        userModel5.setEmail("jane.doe@example.org");
        userModel5.setFname("Fname");
        userModel5.setId(123L);
        userModel5.setImageURL("https://example.org/example");
        userModel5.setLname("Lname");
        userModel5.setPassword("iloveyou");
        userModel5.setPhone("4105551212");
        userModel5.setReservationModelBarber(new HashSet<>());
        userModel5.setReservationModelClient(new HashSet<>());
        userModel5.setRole("Role");
        userModel5.setUpdated(null);

        HaircutModel haircutModel1 = new HaircutModel();
        haircutModel1.setDescription("The characteristics of someone or something");
        haircutModel1.setEstimatedTime("Estimated Time");
        haircutModel1.setId("42");
        haircutModel1.setImageURL("https://example.org/example");
        haircutModel1.setPrice(1);
        haircutModel1.setReservationModel(new HashSet<>());
        haircutModel1.setTitle("Dr");

        LocationModel locationModel = new LocationModel();
        locationModel.setId(123L);
        locationModel.setLatitude(10.0d);
        locationModel.setLongitude(10.0d);
        locationModel.setReservationModel(new ReservationModel());

        ReservationTimeModel reservationTimeModel = new ReservationTimeModel();
        reservationTimeModel.setHours(1);
        reservationTimeModel.setId(123L);
        reservationTimeModel.setMinutes(1);
        reservationTimeModel.setReservationModel(new HashSet<>());

        ReservationModel reservationModel = new ReservationModel();
        reservationModel.setBarber(userModel4);
        reservationModel.setClient(userModel5);
        reservationModel.setHaircut(haircutModel1);
        reservationModel.setId(123L);
        reservationModel.setLocation(locationModel);
        reservationModel.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationModel.setReservationTime(reservationTimeModel);
        reservationModel.setStatus("Status");

        LocationModel locationModel1 = new LocationModel();
        locationModel1.setId(123L);
        locationModel1.setLatitude(10.0d);
        locationModel1.setLongitude(10.0d);
        locationModel1.setReservationModel(reservationModel);

        ReservationTimeModel reservationTimeModel1 = new ReservationTimeModel();
        reservationTimeModel1.setHours(1);
        reservationTimeModel1.setId(123L);
        reservationTimeModel1.setMinutes(1);
        reservationTimeModel1.setReservationModel(new HashSet<>());

        ReservationModel reservationModel1 = new ReservationModel();
        reservationModel1.setBarber(userModel1);
        reservationModel1.setClient(userModel3);
        reservationModel1.setHaircut(haircutModel);
        reservationModel1.setId(123L);
        reservationModel1.setLocation(locationModel1);
        reservationModel1.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationModel1.setReservationTime(reservationTimeModel1);
        reservationModel1.setStatus("Status");
        Optional<ReservationModel> ofResult = Optional.of(reservationModel1);
        when(this.reservationRepository.findById(any())).thenReturn(ofResult);
        Optional<ReservationDTO> actualFindByIdResult = this.reservationServiceImpl.findById(123L);
        assertTrue(actualFindByIdResult.isPresent());
        ReservationDTO getResult = actualFindByIdResult.get();
        assertEquals("Status", getResult.getStatus());
        UserDTO barber = getResult.getBarber();
        UserDTO client = getResult.getClient();
        assertEquals(barber, client);
        assertEquals(123L, getResult.getId().longValue());
        assertEquals("1970-01-02", getResult.getReservationDate().toString());
        HaircutDTO haircut = getResult.getHaircut();
        assertEquals(1, haircut.getPrice().intValue());
        ReservationTimeDTO reservationTime = getResult.getReservationTime();
        assertEquals(1, reservationTime.getMinutes().intValue());
        assertEquals(123L, reservationTime.getId());
        assertEquals(1, reservationTime.getHours().intValue());
        assertEquals("https://example.org/example", haircut.getImageURL());
        assertEquals("42", haircut.getId());
        assertEquals("Estimated Time", haircut.getEstimatedTime());
        assertEquals("The characteristics of someone or something", haircut.getDescription());
        LocationDTO location = getResult.getLocation();
        assertEquals(10.0d, location.getLatitude().doubleValue());
        assertEquals(123L, location.getId());
        assertEquals("Role", client.getRole());
        assertEquals("4105551212", client.getPhone());
        assertEquals("Lname", client.getLname());
        assertEquals("https://example.org/example", client.getImageURL());
        assertEquals(123L, client.getId().longValue());
        assertEquals("Fname", client.getFname());
        assertEquals("jane.doe@example.org", client.getEmail());
        assertEquals("1970-01-02", client.getDob().toString());
        assertEquals("Role", barber.getRole());
        assertFalse(client.isDeleted());
        assertFalse(barber.isDeleted());
        AddressDTO address = client.getAddress();
        assertEquals(address, barber.getAddress());
        assertEquals("4105551212", barber.getPhone());
        assertEquals("Lname", barber.getLname());
        assertEquals("https://example.org/example", barber.getImageURL());
        assertEquals(123L, barber.getId().longValue());
        assertEquals("Fname", barber.getFname());
        assertEquals("jane.doe@example.org", barber.getEmail());
        assertEquals("1970-01-02", barber.getDob().toString());
        assertEquals("Dr", haircut.getTitle());
        assertEquals(10.0d, location.getLongitude().doubleValue());
        assertEquals("MD", address.getState());
        assertEquals("Apartement", address.getApartement());
        assertEquals(123L, address.getId().longValue());
        assertEquals("Oxford", address.getCity());
        verify(this.reservationRepository).findById(any());
    }

    @Test
    void testFindById2() {
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
        userModel.setReservationModelBarber(new HashSet<>());
        userModel.setReservationModelClient(new HashSet<>());
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
        userModel1.setReservationModelBarber(new HashSet<>());
        userModel1.setReservationModelClient(new HashSet<>());
        userModel1.setRole("Role");
        userModel1.setUpdated(LocalDate.ofEpochDay(1L));

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setId(123L);
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setUsers(new HashSet<>());
        addressModel1.setZip("21654");

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
        userModel2.setReservationModelBarber(new HashSet<>());
        userModel2.setReservationModelClient(new HashSet<>());
        userModel2.setRole("Role");
        userModel2.setUpdated(null);

        CredentialModel credentialModel1 = new CredentialModel();
        credentialModel1.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel1.setGrantedAuthority("JaneDoe");
        credentialModel1.setId(123L);
        credentialModel1.setPassword("iloveyou");
        credentialModel1.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel1.setUser(userModel2);
        credentialModel1.setUsername("janedoe");

        UserModel userModel3 = new UserModel();
        userModel3.setAddress(addressModel1);
        userModel3.setCreated(LocalDate.ofEpochDay(1L));
        userModel3.setCredential(credentialModel1);
        userModel3.setDeleted(true);
        userModel3.setDob(LocalDate.ofEpochDay(1L));
        userModel3.setEmail("jane.doe@example.org");
        userModel3.setFname("Fname");
        userModel3.setId(123L);
        userModel3.setImageURL("https://example.org/example");
        userModel3.setLname("Lname");
        userModel3.setPassword("iloveyou");
        userModel3.setPhone("4105551212");
        userModel3.setReservationModelBarber(new HashSet<>());
        userModel3.setReservationModelClient(new HashSet<>());
        userModel3.setRole("Role");
        userModel3.setUpdated(LocalDate.ofEpochDay(1L));

        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setReservationModel(new HashSet<>());
        haircutModel.setTitle("Dr");

        UserModel userModel4 = new UserModel();
        userModel4.setAddress(new AddressModel());
        userModel4.setCreated(null);
        userModel4.setCredential(new CredentialModel());
        userModel4.setDeleted(true);
        userModel4.setDob(null);
        userModel4.setEmail("jane.doe@example.org");
        userModel4.setFname("Fname");
        userModel4.setId(123L);
        userModel4.setImageURL("https://example.org/example");
        userModel4.setLname("Lname");
        userModel4.setPassword("iloveyou");
        userModel4.setPhone("4105551212");
        userModel4.setReservationModelBarber(new HashSet<>());
        userModel4.setReservationModelClient(new HashSet<>());
        userModel4.setRole("Role");
        userModel4.setUpdated(null);

        UserModel userModel5 = new UserModel();
        userModel5.setAddress(new AddressModel());
        userModel5.setCreated(null);
        userModel5.setCredential(new CredentialModel());
        userModel5.setDeleted(true);
        userModel5.setDob(null);
        userModel5.setEmail("jane.doe@example.org");
        userModel5.setFname("Fname");
        userModel5.setId(123L);
        userModel5.setImageURL("https://example.org/example");
        userModel5.setLname("Lname");
        userModel5.setPassword("iloveyou");
        userModel5.setPhone("4105551212");
        userModel5.setReservationModelBarber(new HashSet<>());
        userModel5.setReservationModelClient(new HashSet<>());
        userModel5.setRole("Role");
        userModel5.setUpdated(null);

        HaircutModel haircutModel1 = new HaircutModel();
        haircutModel1.setDescription("The characteristics of someone or something");
        haircutModel1.setEstimatedTime("Estimated Time");
        haircutModel1.setId("42");
        haircutModel1.setImageURL("https://example.org/example");
        haircutModel1.setPrice(1);
        haircutModel1.setReservationModel(new HashSet<>());
        haircutModel1.setTitle("Dr");

        LocationModel locationModel = new LocationModel();
        locationModel.setId(123L);
        locationModel.setLatitude(10.0d);
        locationModel.setLongitude(10.0d);
        locationModel.setReservationModel(new ReservationModel());

        ReservationTimeModel reservationTimeModel = new ReservationTimeModel();
        reservationTimeModel.setHours(1);
        reservationTimeModel.setId(123L);
        reservationTimeModel.setMinutes(1);
        reservationTimeModel.setReservationModel(new HashSet<>());

        ReservationModel reservationModel = new ReservationModel();
        reservationModel.setBarber(userModel4);
        reservationModel.setClient(userModel5);
        reservationModel.setHaircut(haircutModel1);
        reservationModel.setId(123L);
        reservationModel.setLocation(locationModel);
        reservationModel.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationModel.setReservationTime(reservationTimeModel);
        reservationModel.setStatus("Status");

        LocationModel locationModel1 = new LocationModel();
        locationModel1.setId(123L);
        locationModel1.setLatitude(10.0d);
        locationModel1.setLongitude(10.0d);
        locationModel1.setReservationModel(reservationModel);

        ReservationTimeModel reservationTimeModel1 = new ReservationTimeModel();
        reservationTimeModel1.setHours(1);
        reservationTimeModel1.setId(123L);
        reservationTimeModel1.setMinutes(1);
        reservationTimeModel1.setReservationModel(new HashSet<>());

        ReservationTimeModel reservationTimeModel2 = new ReservationTimeModel();
        reservationTimeModel2.setHours(1);
        reservationTimeModel2.setId(123L);
        reservationTimeModel2.setMinutes(1);
        reservationTimeModel2.setReservationModel(new HashSet<>());
        ReservationModel reservationModel1 = mock(ReservationModel.class);
        when(reservationModel1.getClient()).thenThrow(new IllegalStateException("foo"));
        when(reservationModel1.getReservationTime()).thenReturn(reservationTimeModel2);
        doNothing().when(reservationModel1).setBarber(any());
        doNothing().when(reservationModel1).setClient(any());
        doNothing().when(reservationModel1).setHaircut(any());
        doNothing().when(reservationModel1).setId(any());
        doNothing().when(reservationModel1).setLocation(any());
        doNothing().when(reservationModel1).setReservationDate(any());
        doNothing().when(reservationModel1).setReservationTime(any());
        doNothing().when(reservationModel1).setStatus(any());
        reservationModel1.setBarber(userModel1);
        reservationModel1.setClient(userModel3);
        reservationModel1.setHaircut(haircutModel);
        reservationModel1.setId(123L);
        reservationModel1.setLocation(locationModel1);
        reservationModel1.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationModel1.setReservationTime(reservationTimeModel1);
        reservationModel1.setStatus("Status");
        Optional<ReservationModel> ofResult = Optional.of(reservationModel1);
        when(this.reservationRepository.findById(any())).thenReturn(ofResult);
        assertThrows(IllegalStateException.class, () -> this.reservationServiceImpl.findById(123L));
        verify(this.reservationRepository).findById(any());
        verify(reservationModel1).getReservationTime();
        verify(reservationModel1).getClient();
        verify(reservationModel1).setBarber(any());
        verify(reservationModel1).setClient(any());
        verify(reservationModel1).setHaircut(any());
        verify(reservationModel1).setId(any());
        verify(reservationModel1).setLocation(any());
        verify(reservationModel1).setReservationDate(any());
        verify(reservationModel1).setReservationTime(any());
        verify(reservationModel1).setStatus(any());
    }

    @Test
    void testSave() {
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
        Optional<UserDTO> ofResult = Optional.of(userDTO);
        when(this.userService.existsUserByEmail(any())).thenReturn(true);
        when(this.userService.findUserByEmail(any())).thenReturn(ofResult);

        ReservationTimeDTO reservationTimeDTO = new ReservationTimeDTO();
        reservationTimeDTO.setHours(1);
        reservationTimeDTO.setId(123L);
        reservationTimeDTO.setMinutes(1);
        Optional<ReservationTimeDTO> ofResult1 = Optional.of(reservationTimeDTO);
        when(this.reservationTimeService.save(any())).thenReturn(ofResult1);

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
        userModel.setReservationModelBarber(new HashSet<>());
        userModel.setReservationModelClient(new HashSet<>());
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
        userModel1.setReservationModelBarber(new HashSet<>());
        userModel1.setReservationModelClient(new HashSet<>());
        userModel1.setRole("Role");
        userModel1.setUpdated(LocalDate.ofEpochDay(1L));

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

        CredentialModel credentialModel2 = new CredentialModel();
        credentialModel2.setCreated(null);
        credentialModel2.setGrantedAuthority("JaneDoe");
        credentialModel2.setId(123L);
        credentialModel2.setPassword("iloveyou");
        credentialModel2.setUpdated(null);
        credentialModel2.setUser(new UserModel());
        credentialModel2.setUsername("janedoe");

        UserModel userModel2 = new UserModel();
        userModel2.setAddress(addressModel3);
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
        userModel2.setReservationModelBarber(new HashSet<>());
        userModel2.setReservationModelClient(new HashSet<>());
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
        userModel3.setAddress(addressModel2);
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
        userModel3.setReservationModelBarber(new HashSet<>());
        userModel3.setReservationModelClient(new HashSet<>());
        userModel3.setRole("Role");
        userModel3.setUpdated(LocalDate.ofEpochDay(1L));

        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setReservationModel(new HashSet<>());
        haircutModel.setTitle("Dr");

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
        userModel4.setReservationModelBarber(new HashSet<>());
        userModel4.setReservationModelClient(new HashSet<>());
        userModel4.setRole("Role");
        userModel4.setUpdated(LocalDate.ofEpochDay(1L));

        AddressModel addressModel5 = new AddressModel();
        addressModel5.setApartement("Apartement");
        addressModel5.setCity("Oxford");
        addressModel5.setId(123L);
        addressModel5.setState("MD");
        addressModel5.setStreet("Street");
        addressModel5.setUsers(new HashSet<>());
        addressModel5.setZip("21654");

        CredentialModel credentialModel5 = new CredentialModel();
        credentialModel5.setCreated(null);
        credentialModel5.setGrantedAuthority("JaneDoe");
        credentialModel5.setId(123L);
        credentialModel5.setPassword("iloveyou");
        credentialModel5.setUpdated(null);
        credentialModel5.setUser(new UserModel());
        credentialModel5.setUsername("janedoe");

        UserModel userModel5 = new UserModel();
        userModel5.setAddress(addressModel5);
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
        userModel5.setReservationModelBarber(new HashSet<>());
        userModel5.setReservationModelClient(new HashSet<>());
        userModel5.setRole("Role");
        userModel5.setUpdated(LocalDate.ofEpochDay(1L));

        HaircutModel haircutModel1 = new HaircutModel();
        haircutModel1.setDescription("The characteristics of someone or something");
        haircutModel1.setEstimatedTime("Estimated Time");
        haircutModel1.setId("42");
        haircutModel1.setImageURL("https://example.org/example");
        haircutModel1.setPrice(1);
        haircutModel1.setReservationModel(new HashSet<>());
        haircutModel1.setTitle("Dr");

        ReservationModel reservationModel = new ReservationModel();
        reservationModel.setBarber(new UserModel());
        reservationModel.setClient(new UserModel());
        reservationModel.setHaircut(new HaircutModel());
        reservationModel.setId(123L);
        reservationModel.setLocation(new LocationModel());
        reservationModel.setReservationDate(null);
        reservationModel.setReservationTime(new ReservationTimeModel());
        reservationModel.setStatus("Status");

        LocationModel locationModel = new LocationModel();
        locationModel.setId(123L);
        locationModel.setLatitude(10.0d);
        locationModel.setLongitude(10.0d);
        locationModel.setReservationModel(reservationModel);

        ReservationTimeModel reservationTimeModel = new ReservationTimeModel();
        reservationTimeModel.setHours(1);
        reservationTimeModel.setId(123L);
        reservationTimeModel.setMinutes(1);
        reservationTimeModel.setReservationModel(new HashSet<>());

        ReservationModel reservationModel1 = new ReservationModel();
        reservationModel1.setBarber(userModel4);
        reservationModel1.setClient(userModel5);
        reservationModel1.setHaircut(haircutModel1);
        reservationModel1.setId(123L);
        reservationModel1.setLocation(locationModel);
        reservationModel1.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationModel1.setReservationTime(reservationTimeModel);
        reservationModel1.setStatus("Status");

        LocationModel locationModel1 = new LocationModel();
        locationModel1.setId(123L);
        locationModel1.setLatitude(10.0d);
        locationModel1.setLongitude(10.0d);
        locationModel1.setReservationModel(reservationModel1);

        ReservationTimeModel reservationTimeModel1 = new ReservationTimeModel();
        reservationTimeModel1.setHours(1);
        reservationTimeModel1.setId(123L);
        reservationTimeModel1.setMinutes(1);
        reservationTimeModel1.setReservationModel(new HashSet<>());

        ReservationModel reservationModel2 = new ReservationModel();
        reservationModel2.setBarber(userModel1);
        reservationModel2.setClient(userModel3);
        reservationModel2.setHaircut(haircutModel);
        reservationModel2.setId(123L);
        reservationModel2.setLocation(locationModel1);
        reservationModel2.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationModel2.setReservationTime(reservationTimeModel1);
        reservationModel2.setStatus("Status");
        when(this.reservationRepository.save(any())).thenReturn(reservationModel2);

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(123L);
        locationDTO.setLatitude(10.0d);
        locationDTO.setLongitude(10.0d);
        Optional<LocationDTO> ofResult2 = Optional.of(locationDTO);
        when(this.locationService.save(any())).thenReturn(ofResult2);
        when(this.haircutService.existsHaircutById(any())).thenReturn(true);

        AddressDTO addressDTO1 = new AddressDTO();
        addressDTO1.setApartement("Apartement");
        addressDTO1.setCity("Oxford");
        addressDTO1.setId(123L);
        addressDTO1.setState("MD");
        addressDTO1.setStreet("Street");
        addressDTO1.setZip("21654");

        UserDTO userDTO1 = new UserDTO();
        userDTO1.setAddress(addressDTO1);
        userDTO1.setCreated(LocalDate.ofEpochDay(1L));
        userDTO1.setDeleted(true);
        userDTO1.setDob(LocalDate.ofEpochDay(1L));
        userDTO1.setEmail("jane.doe@example.org");
        userDTO1.setFname("Fname");
        userDTO1.setId(123L);
        userDTO1.setImageURL("https://example.org/example");
        userDTO1.setLname("Lname");
        userDTO1.setPhone("4105551212");
        userDTO1.setRole("Role");
        userDTO1.setUpdated(LocalDate.ofEpochDay(1L));

        AddressDTO addressDTO2 = new AddressDTO();
        addressDTO2.setApartement("Apartement");
        addressDTO2.setCity("Oxford");
        addressDTO2.setId(123L);
        addressDTO2.setState("MD");
        addressDTO2.setStreet("Street");
        addressDTO2.setZip("21654");

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setAddress(addressDTO2);
        userDTO2.setCreated(LocalDate.ofEpochDay(1L));
        userDTO2.setDeleted(true);
        userDTO2.setDob(LocalDate.ofEpochDay(1L));
        userDTO2.setEmail("jane.doe@example.org");
        userDTO2.setFname("Fname");
        userDTO2.setId(123L);
        userDTO2.setImageURL("https://example.org/example");
        userDTO2.setLname("Lname");
        userDTO2.setPhone("4105551212");
        userDTO2.setRole("Role");
        userDTO2.setUpdated(LocalDate.ofEpochDay(1L));

        HaircutDTO haircutDTO = new HaircutDTO();
        haircutDTO.setDescription("The characteristics of someone or something");
        haircutDTO.setEstimatedTime("Estimated Time");
        haircutDTO.setId("42");
        haircutDTO.setImageURL("https://example.org/example");
        haircutDTO.setPrice(1);
        haircutDTO.setTitle("Dr");

        LocationDTO locationDTO1 = new LocationDTO();
        locationDTO1.setId(123L);
        locationDTO1.setLatitude(10.0d);
        locationDTO1.setLongitude(10.0d);

        ReservationTimeDTO reservationTimeDTO1 = new ReservationTimeDTO();
        reservationTimeDTO1.setHours(1);
        reservationTimeDTO1.setId(123L);
        reservationTimeDTO1.setMinutes(1);

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setBarber(userDTO1);
        reservationDTO.setClient(userDTO2);
        reservationDTO.setHaircut(haircutDTO);
        reservationDTO.setId(123L);
        reservationDTO.setLocation(locationDTO1);
        reservationDTO.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationDTO.setReservationTime(reservationTimeDTO1);
        reservationDTO.setStatus("Status");
        Optional<ReservationDTO> actualSaveResult = this.reservationServiceImpl.save(reservationDTO);
        assertTrue(actualSaveResult.isPresent());
        ReservationDTO getResult = actualSaveResult.get();
        assertEquals("Status", getResult.getStatus());
        UserDTO barber = getResult.getBarber();
        UserDTO client = getResult.getClient();
        assertEquals(barber, client);
        assertEquals(123L, getResult.getId().longValue());
        LocationDTO location = getResult.getLocation();
        assertEquals(locationDTO1, location);
        HaircutDTO haircut = getResult.getHaircut();
        assertEquals(haircutDTO, haircut);
        assertEquals("1970-01-02", getResult.getReservationDate().toString());
        ReservationTimeDTO reservationTime = getResult.getReservationTime();
        assertEquals(reservationTimeDTO1, reservationTime);
        assertEquals(1, haircut.getPrice().intValue());
        assertEquals(1, reservationTime.getMinutes().intValue());
        assertEquals(123L, reservationTime.getId());
        assertEquals(1, reservationTime.getHours().intValue());
        assertEquals("https://example.org/example", haircut.getImageURL());
        assertEquals("42", haircut.getId());
        assertEquals("Estimated Time", haircut.getEstimatedTime());
        assertEquals("The characteristics of someone or something", haircut.getDescription());
        assertEquals(10.0d, location.getLatitude().doubleValue());
        assertEquals(123L, location.getId());
        assertEquals("Role", client.getRole());
        assertEquals("4105551212", client.getPhone());
        assertEquals("Lname", client.getLname());
        assertEquals("https://example.org/example", client.getImageURL());
        assertEquals(123L, client.getId().longValue());
        assertEquals("Fname", client.getFname());
        assertEquals("jane.doe@example.org", client.getEmail());
        assertEquals("1970-01-02", client.getDob().toString());
        AddressDTO address = client.getAddress();
        assertEquals(addressDTO1, address);
        assertEquals("Role", barber.getRole());
        assertFalse(client.isDeleted());
        assertFalse(barber.isDeleted());
        assertEquals(addressDTO1, barber.getAddress());
        assertEquals("4105551212", barber.getPhone());
        assertEquals("Lname", barber.getLname());
        assertEquals("https://example.org/example", barber.getImageURL());
        assertEquals(123L, barber.getId().longValue());
        assertEquals("Fname", barber.getFname());
        assertEquals("jane.doe@example.org", barber.getEmail());
        assertEquals("1970-01-02", barber.getDob().toString());
        assertEquals("Dr", haircut.getTitle());
        assertEquals(10.0d, location.getLongitude().doubleValue());
        assertEquals("MD", address.getState());
        assertEquals("Apartement", address.getApartement());
        assertEquals(123L, address.getId().longValue());
        assertEquals("Oxford", address.getCity());
        verify(this.userService).existsUserByEmail(any());
        verify(this.userService).findUserByEmail(any());
        verify(this.reservationTimeService).save(any());
        verify(this.reservationRepository).save(any());
        verify(this.locationService).save(any());
        verify(this.haircutService).existsHaircutById(any());
    }

    @Test
    void testSave2() {
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
        Optional<UserDTO> ofResult = Optional.of(userDTO);
        when(this.userService.existsUserByEmail(any())).thenReturn(true);
        when(this.userService.findUserByEmail(any())).thenReturn(ofResult);

        ReservationTimeDTO reservationTimeDTO = new ReservationTimeDTO();
        reservationTimeDTO.setHours(1);
        reservationTimeDTO.setId(123L);
        reservationTimeDTO.setMinutes(1);
        Optional<ReservationTimeDTO> ofResult1 = Optional.of(reservationTimeDTO);
        when(this.reservationTimeService.save(any())).thenReturn(ofResult1);
        when(this.reservationRepository.save(any())).thenThrow(new IllegalStateException("Non Traitée"));

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(123L);
        locationDTO.setLatitude(10.0d);
        locationDTO.setLongitude(10.0d);
        Optional<LocationDTO> ofResult2 = Optional.of(locationDTO);
        when(this.locationService.save(any())).thenReturn(ofResult2);
        when(this.haircutService.existsHaircutById(any())).thenReturn(true);

        AddressDTO addressDTO1 = new AddressDTO();
        addressDTO1.setApartement("Apartement");
        addressDTO1.setCity("Oxford");
        addressDTO1.setId(123L);
        addressDTO1.setState("MD");
        addressDTO1.setStreet("Street");
        addressDTO1.setZip("21654");

        UserDTO userDTO1 = new UserDTO();
        userDTO1.setAddress(addressDTO1);
        userDTO1.setCreated(LocalDate.ofEpochDay(1L));
        userDTO1.setDeleted(true);
        userDTO1.setDob(LocalDate.ofEpochDay(1L));
        userDTO1.setEmail("jane.doe@example.org");
        userDTO1.setFname("Fname");
        userDTO1.setId(123L);
        userDTO1.setImageURL("https://example.org/example");
        userDTO1.setLname("Lname");
        userDTO1.setPhone("4105551212");
        userDTO1.setRole("Role");
        userDTO1.setUpdated(LocalDate.ofEpochDay(1L));

        AddressDTO addressDTO2 = new AddressDTO();
        addressDTO2.setApartement("Apartement");
        addressDTO2.setCity("Oxford");
        addressDTO2.setId(123L);
        addressDTO2.setState("MD");
        addressDTO2.setStreet("Street");
        addressDTO2.setZip("21654");

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setAddress(addressDTO2);
        userDTO2.setCreated(LocalDate.ofEpochDay(1L));
        userDTO2.setDeleted(true);
        userDTO2.setDob(LocalDate.ofEpochDay(1L));
        userDTO2.setEmail("jane.doe@example.org");
        userDTO2.setFname("Fname");
        userDTO2.setId(123L);
        userDTO2.setImageURL("https://example.org/example");
        userDTO2.setLname("Lname");
        userDTO2.setPhone("4105551212");
        userDTO2.setRole("Role");
        userDTO2.setUpdated(LocalDate.ofEpochDay(1L));

        HaircutDTO haircutDTO = new HaircutDTO();
        haircutDTO.setDescription("The characteristics of someone or something");
        haircutDTO.setEstimatedTime("Estimated Time");
        haircutDTO.setId("42");
        haircutDTO.setImageURL("https://example.org/example");
        haircutDTO.setPrice(1);
        haircutDTO.setTitle("Dr");

        LocationDTO locationDTO1 = new LocationDTO();
        locationDTO1.setId(123L);
        locationDTO1.setLatitude(10.0d);
        locationDTO1.setLongitude(10.0d);

        ReservationTimeDTO reservationTimeDTO1 = new ReservationTimeDTO();
        reservationTimeDTO1.setHours(1);
        reservationTimeDTO1.setId(123L);
        reservationTimeDTO1.setMinutes(1);

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setBarber(userDTO1);
        reservationDTO.setClient(userDTO2);
        reservationDTO.setHaircut(haircutDTO);
        reservationDTO.setId(123L);
        reservationDTO.setLocation(locationDTO1);
        reservationDTO.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationDTO.setReservationTime(reservationTimeDTO1);
        reservationDTO.setStatus("Status");
        assertThrows(IllegalStateException.class, () -> this.reservationServiceImpl.save(reservationDTO));
        verify(this.userService).existsUserByEmail(any());
        verify(this.userService).findUserByEmail(any());
        verify(this.reservationTimeService).save(any());
        verify(this.reservationRepository).save(any());
        verify(this.locationService).save(any());
        verify(this.haircutService).existsHaircutById(any());
    }

    @Test
    void testUpdate() {
        ReservationRepository reservationRepository = mock(ReservationRepository.class);
        when(reservationRepository.findById(any()))
                .thenThrow(new IllegalStateException("Reservation with ID %s cannot found"));
        UserRepository userRepository = mock(UserRepository.class);
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        AddressServiceImpl addressService = new AddressServiceImpl(mock(AddressRepository.class));
        UserServiceImpl userService = new UserServiceImpl(userRepository, passwordEncoder, addressService,
                new CredentialServiceImpl(mock(CredentialRepository.class)));

        HaircutServiceImpl haircutService = new HaircutServiceImpl(mock(HaircutRepository.class));
        ReservationTimeServiceImpl reservationTimeService = new ReservationTimeServiceImpl(
                mock(ReservationTimeRepository.class));
        ReservationServiceImpl reservationServiceImpl = new ReservationServiceImpl(reservationRepository, userService,
                haircutService, reservationTimeService, new LocationServiceImpl(mock(LocationRepository.class)));

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

        AddressDTO addressDTO1 = new AddressDTO();
        addressDTO1.setApartement("Apartement");
        addressDTO1.setCity("Oxford");
        addressDTO1.setId(123L);
        addressDTO1.setState("MD");
        addressDTO1.setStreet("Street");
        addressDTO1.setZip("21654");

        UserDTO userDTO1 = new UserDTO();
        userDTO1.setAddress(addressDTO1);
        userDTO1.setCreated(LocalDate.ofEpochDay(1L));
        userDTO1.setDeleted(true);
        userDTO1.setDob(LocalDate.ofEpochDay(1L));
        userDTO1.setEmail("jane.doe@example.org");
        userDTO1.setFname("Fname");
        userDTO1.setId(123L);
        userDTO1.setImageURL("https://example.org/example");
        userDTO1.setLname("Lname");
        userDTO1.setPhone("4105551212");
        userDTO1.setRole("Role");
        userDTO1.setUpdated(LocalDate.ofEpochDay(1L));

        HaircutDTO haircutDTO = new HaircutDTO();
        haircutDTO.setDescription("The characteristics of someone or something");
        haircutDTO.setEstimatedTime("Estimated Time");
        haircutDTO.setId("42");
        haircutDTO.setImageURL("https://example.org/example");
        haircutDTO.setPrice(1);
        haircutDTO.setTitle("Dr");

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(123L);
        locationDTO.setLatitude(10.0d);
        locationDTO.setLongitude(10.0d);

        ReservationTimeDTO reservationTimeDTO = new ReservationTimeDTO();
        reservationTimeDTO.setHours(1);
        reservationTimeDTO.setId(123L);
        reservationTimeDTO.setMinutes(1);

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setBarber(userDTO);
        reservationDTO.setClient(userDTO1);
        reservationDTO.setHaircut(haircutDTO);
        reservationDTO.setId(123L);
        reservationDTO.setLocation(locationDTO);
        reservationDTO.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationDTO.setReservationTime(reservationTimeDTO);
        reservationDTO.setStatus("Status");
        assertThrows(IllegalStateException.class, () -> reservationServiceImpl.update(123L, reservationDTO));
        verify(reservationRepository).findById(any());
    }

    @Test
    void testDeleteReservationById() {
        doNothing().when(this.reservationRepository).deleteById(any());
        this.reservationServiceImpl.deleteReservationById(123L);
        verify(this.reservationRepository).deleteById(any());
    }

    @Test
    void testDeleteReservationById2() {
        doThrow(new IllegalStateException("foo")).when(this.reservationRepository).deleteById(any());
        assertThrows(IllegalStateException.class, () -> this.reservationServiceImpl.deleteReservationById(123L));
        verify(this.reservationRepository).deleteById(any());
    }

    @Test
    void testCount() {
        when(this.reservationRepository.findAll()).thenReturn(new ArrayList<>());
        assertEquals(0L, this.reservationServiceImpl.count());
        verify(this.reservationRepository).findAll();
    }

    @Test
    void testCount2() {
        when(this.reservationRepository.findAll()).thenThrow(new IllegalStateException("foo"));
        assertThrows(IllegalStateException.class, () -> this.reservationServiceImpl.count());
        verify(this.reservationRepository).findAll();
    }
}

