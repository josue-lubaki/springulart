package ca.ghostteam.springulart.service.location;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ghostteam.springulart.dto.LocationDTO;
import ca.ghostteam.springulart.model.AddressModel;
import ca.ghostteam.springulart.model.CredentialModel;
import ca.ghostteam.springulart.model.HaircutModel;
import ca.ghostteam.springulart.model.LocationModel;
import ca.ghostteam.springulart.model.ReservationModel;
import ca.ghostteam.springulart.model.ReservationTimeModel;
import ca.ghostteam.springulart.model.UserModel;
import ca.ghostteam.springulart.repository.LocationRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import ca.ghostteam.springulart.service.location.impl.LocationServiceImpl;
import ca.ghostteam.springulart.tools.UtilsLocation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {LocationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class LocationServiceImplTest {
    @MockBean
    private LocationRepository locationRepository;

    @Autowired
    private LocationService locationServiceImpl;

    @MockBean
    private UtilsLocation utilsLocation;

    @Test
    void testFindById() {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(123L);
        locationDTO.setLatitude(10.0d);
        locationDTO.setLongitude(10.0d);
        when(this.utilsLocation.converterModelToDTO(any())).thenReturn(locationDTO);

        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setId(123L);
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setUsers(new HashSet<>());
        addressModel.setZip("21654");

        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setCreated(null);
        credentialModel.setGrantedAuthority("JaneDoe");
        credentialModel.setId(123L);
        credentialModel.setPassword("iloveyou");
        credentialModel.setUpdated(null);
        credentialModel.setUser(new UserModel());
        credentialModel.setUsername("janedoe");

        UserModel userModel = new UserModel();
        userModel.setAddress(addressModel);
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

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setId(123L);
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setUsers(new HashSet<>());
        addressModel1.setZip("21654");

        CredentialModel credentialModel1 = new CredentialModel();
        credentialModel1.setCreated(null);
        credentialModel1.setGrantedAuthority("JaneDoe");
        credentialModel1.setId(123L);
        credentialModel1.setPassword("iloveyou");
        credentialModel1.setUpdated(null);
        credentialModel1.setUser(new UserModel());
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

        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setReservationModel(new ArrayList<>());
        haircutModel.setTitle("Dr");

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
        reservationTimeModel.setReservationModel(new ArrayList<>());

        ReservationModel reservationModel1 = new ReservationModel();
        reservationModel1.setBarber(userModel);
        reservationModel1.setClient(userModel1);
        reservationModel1.setHaircut(haircutModel);
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
        Optional<LocationModel> ofResult = Optional.of(locationModel1);
        when(this.locationRepository.findById(any())).thenReturn(ofResult);
        assertTrue(this.locationServiceImpl.findById(123L).isPresent());
        verify(this.utilsLocation).converterModelToDTO(any());
        verify(this.locationRepository).findById(any());
    }

    @Test
    void testSave() {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(123L);
        locationDTO.setLatitude(10.0d);
        locationDTO.setLongitude(10.0d);

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
        userModel2.setReservationModelBarber(new ArrayList<>());
        userModel2.setReservationModelClient(new ArrayList<>());
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
        userModel3.setReservationModelBarber(new ArrayList<>());
        userModel3.setReservationModelClient(new ArrayList<>());
        userModel3.setRole("Role");
        userModel3.setUpdated(LocalDate.ofEpochDay(1L));

        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setReservationModel(new ArrayList<>());
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
        userModel4.setReservationModelBarber(new ArrayList<>());
        userModel4.setReservationModelClient(new ArrayList<>());
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
        userModel5.setReservationModelBarber(new ArrayList<>());
        userModel5.setReservationModelClient(new ArrayList<>());
        userModel5.setRole("Role");
        userModel5.setUpdated(null);

        HaircutModel haircutModel1 = new HaircutModel();
        haircutModel1.setDescription("The characteristics of someone or something");
        haircutModel1.setEstimatedTime("Estimated Time");
        haircutModel1.setId("42");
        haircutModel1.setImageURL("https://example.org/example");
        haircutModel1.setPrice(1);
        haircutModel1.setReservationModel(new ArrayList<>());
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
        reservationTimeModel.setReservationModel(new ArrayList<>());

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
        reservationTimeModel1.setReservationModel(new ArrayList<>());

        ReservationModel reservationModel1 = new ReservationModel();
        reservationModel1.setBarber(userModel1);
        reservationModel1.setClient(userModel3);
        reservationModel1.setHaircut(haircutModel);
        reservationModel1.setId(123L);
        reservationModel1.setLocation(locationModel1);
        reservationModel1.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationModel1.setReservationTime(reservationTimeModel1);
        reservationModel1.setStatus("Status");

        LocationModel locationModel2 = new LocationModel();
        locationModel2.setId(123L);
        locationModel2.setLatitude(10.0d);
        locationModel2.setLongitude(10.0d);
        locationModel2.setReservationModel(reservationModel1);
        when(this.utilsLocation.converterModelToDTO(any())).thenReturn(locationDTO);
        when(this.utilsLocation.converterDtoToModel(any())).thenReturn(locationModel2);

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
        userModel6.setReservationModelBarber(new ArrayList<>());
        userModel6.setReservationModelClient(new ArrayList<>());
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
        userModel7.setReservationModelBarber(new ArrayList<>());
        userModel7.setReservationModelClient(new ArrayList<>());
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
        userModel8.setReservationModelBarber(new ArrayList<>());
        userModel8.setReservationModelClient(new ArrayList<>());
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
        userModel9.setReservationModelBarber(new ArrayList<>());
        userModel9.setReservationModelClient(new ArrayList<>());
        userModel9.setRole("Role");
        userModel9.setUpdated(LocalDate.ofEpochDay(1L));

        HaircutModel haircutModel2 = new HaircutModel();
        haircutModel2.setDescription("The characteristics of someone or something");
        haircutModel2.setEstimatedTime("Estimated Time");
        haircutModel2.setId("42");
        haircutModel2.setImageURL("https://example.org/example");
        haircutModel2.setPrice(1);
        haircutModel2.setReservationModel(new ArrayList<>());
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
        userModel10.setReservationModelBarber(new ArrayList<>());
        userModel10.setReservationModelClient(new ArrayList<>());
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
        userModel11.setReservationModelBarber(new ArrayList<>());
        userModel11.setReservationModelClient(new ArrayList<>());
        userModel11.setRole("Role");
        userModel11.setUpdated(null);

        HaircutModel haircutModel3 = new HaircutModel();
        haircutModel3.setDescription("The characteristics of someone or something");
        haircutModel3.setEstimatedTime("Estimated Time");
        haircutModel3.setId("42");
        haircutModel3.setImageURL("https://example.org/example");
        haircutModel3.setPrice(1);
        haircutModel3.setReservationModel(new ArrayList<>());
        haircutModel3.setTitle("Dr");

        LocationModel locationModel3 = new LocationModel();
        locationModel3.setId(123L);
        locationModel3.setLatitude(10.0d);
        locationModel3.setLongitude(10.0d);
        locationModel3.setReservationModel(new ReservationModel());

        ReservationTimeModel reservationTimeModel2 = new ReservationTimeModel();
        reservationTimeModel2.setHours(1);
        reservationTimeModel2.setId(123L);
        reservationTimeModel2.setMinutes(1);
        reservationTimeModel2.setReservationModel(new ArrayList<>());

        ReservationModel reservationModel2 = new ReservationModel();
        reservationModel2.setBarber(userModel10);
        reservationModel2.setClient(userModel11);
        reservationModel2.setHaircut(haircutModel3);
        reservationModel2.setId(123L);
        reservationModel2.setLocation(locationModel3);
        reservationModel2.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationModel2.setReservationTime(reservationTimeModel2);
        reservationModel2.setStatus("Status");

        LocationModel locationModel4 = new LocationModel();
        locationModel4.setId(123L);
        locationModel4.setLatitude(10.0d);
        locationModel4.setLongitude(10.0d);
        locationModel4.setReservationModel(reservationModel2);

        ReservationTimeModel reservationTimeModel3 = new ReservationTimeModel();
        reservationTimeModel3.setHours(1);
        reservationTimeModel3.setId(123L);
        reservationTimeModel3.setMinutes(1);
        reservationTimeModel3.setReservationModel(new ArrayList<>());

        ReservationModel reservationModel3 = new ReservationModel();
        reservationModel3.setBarber(userModel7);
        reservationModel3.setClient(userModel9);
        reservationModel3.setHaircut(haircutModel2);
        reservationModel3.setId(123L);
        reservationModel3.setLocation(locationModel4);
        reservationModel3.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationModel3.setReservationTime(reservationTimeModel3);
        reservationModel3.setStatus("Status");

        LocationModel locationModel5 = new LocationModel();
        locationModel5.setId(123L);
        locationModel5.setLatitude(10.0d);
        locationModel5.setLongitude(10.0d);
        locationModel5.setReservationModel(reservationModel3);
        when(this.locationRepository.save(any())).thenReturn(locationModel5);

        LocationDTO locationDTO1 = new LocationDTO();
        locationDTO1.setId(123L);
        locationDTO1.setLatitude(10.0d);
        locationDTO1.setLongitude(10.0d);
        assertTrue(this.locationServiceImpl.save(locationDTO1).isPresent());
        verify(this.utilsLocation).converterModelToDTO(any());
        verify(this.utilsLocation).converterDtoToModel(any());
        verify(this.locationRepository).save(any());
    }

    @Test
    void testUpdate() {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(123L);
        locationDTO.setLatitude(10.0d);
        locationDTO.setLongitude(10.0d);
        when(this.utilsLocation.converterModelToDTO(any())).thenReturn(locationDTO);

        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setId(123L);
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setUsers(new HashSet<>());
        addressModel.setZip("21654");

        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setCreated(null);
        credentialModel.setGrantedAuthority("JaneDoe");
        credentialModel.setId(123L);
        credentialModel.setPassword("iloveyou");
        credentialModel.setUpdated(null);
        credentialModel.setUser(new UserModel());
        credentialModel.setUsername("janedoe");

        UserModel userModel = new UserModel();
        userModel.setAddress(addressModel);
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

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setId(123L);
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setUsers(new HashSet<>());
        addressModel1.setZip("21654");

        CredentialModel credentialModel1 = new CredentialModel();
        credentialModel1.setCreated(null);
        credentialModel1.setGrantedAuthority("JaneDoe");
        credentialModel1.setId(123L);
        credentialModel1.setPassword("iloveyou");
        credentialModel1.setUpdated(null);
        credentialModel1.setUser(new UserModel());
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

        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setReservationModel(new ArrayList<>());
        haircutModel.setTitle("Dr");

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
        reservationTimeModel.setReservationModel(new ArrayList<>());

        ReservationModel reservationModel1 = new ReservationModel();
        reservationModel1.setBarber(userModel);
        reservationModel1.setClient(userModel1);
        reservationModel1.setHaircut(haircutModel);
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
        Optional<LocationModel> ofResult = Optional.of(locationModel1);
        when(this.locationRepository.findById(any())).thenReturn(ofResult);
        doNothing().when(this.locationRepository).updateLocationById(any(), any(), any());

        LocationDTO locationDTO1 = new LocationDTO();
        locationDTO1.setId(123L);
        locationDTO1.setLatitude(10.0d);
        locationDTO1.setLongitude(10.0d);
        assertTrue(this.locationServiceImpl.update(123L, locationDTO1).isPresent());
        verify(this.utilsLocation).converterModelToDTO(any());
        verify(this.locationRepository).findById(any());
        verify(this.locationRepository).updateLocationById(any(), any(), any());
    }
}

