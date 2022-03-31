package ca.ghostteam.springulart.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    private LocationServiceImpl locationServiceImpl;

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
        userModel.setReservationModelBarber(new HashSet<>());
        userModel.setReservationModelClient(new HashSet<>());
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
        userModel1.setReservationModelBarber(new HashSet<>());
        userModel1.setReservationModelClient(new HashSet<>());
        userModel1.setRole("Role");
        userModel1.setUpdated(LocalDate.ofEpochDay(1L));

        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setReservationModel(new HashSet<>());
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
        reservationTimeModel.setReservationModel(new HashSet<>());

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
        Optional<LocationDTO> actualFindByIdResult = this.locationServiceImpl.findById(123L);
        assertTrue(actualFindByIdResult.isPresent());
        LocationDTO getResult = actualFindByIdResult.get();
        assertEquals(123L, getResult.getId());
        assertEquals(10.0d, getResult.getLongitude().doubleValue());
        assertEquals(10.0d, getResult.getLatitude().doubleValue());
        verify(this.locationRepository).findById(any());
    }

    @Test
    void testSave() {
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

        LocationModel locationModel2 = new LocationModel();
        locationModel2.setId(123L);
        locationModel2.setLatitude(10.0d);
        locationModel2.setLongitude(10.0d);
        locationModel2.setReservationModel(reservationModel1);
        when(this.locationRepository.save(any())).thenReturn(locationModel2);

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(123L);
        locationDTO.setLatitude(10.0d);
        locationDTO.setLongitude(10.0d);
        Optional<LocationDTO> actualSaveResult = this.locationServiceImpl.save(locationDTO);
        assertTrue(actualSaveResult.isPresent());
        LocationDTO getResult = actualSaveResult.get();
        assertEquals(123L, getResult.getId());
        assertEquals(10.0d, getResult.getLongitude().doubleValue());
        assertEquals(10.0d, getResult.getLatitude().doubleValue());
        verify(this.locationRepository).save(any());
    }

    @Test
    void testUpdate() {
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
        userModel.setReservationModelBarber(new HashSet<>());
        userModel.setReservationModelClient(new HashSet<>());
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
        userModel1.setReservationModelBarber(new HashSet<>());
        userModel1.setReservationModelClient(new HashSet<>());
        userModel1.setRole("Role");
        userModel1.setUpdated(LocalDate.ofEpochDay(1L));

        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setReservationModel(new HashSet<>());
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
        reservationTimeModel.setReservationModel(new HashSet<>());

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

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(123L);
        locationDTO.setLatitude(10.0d);
        locationDTO.setLongitude(10.0d);
        Optional<LocationDTO> actualUpdateResult = this.locationServiceImpl.update(123L, locationDTO);
        assertTrue(actualUpdateResult.isPresent());
        LocationDTO getResult = actualUpdateResult.get();
        assertEquals(123L, getResult.getId());
        assertEquals(10.0d, getResult.getLongitude().doubleValue());
        assertEquals(10.0d, getResult.getLatitude().doubleValue());
        verify(this.locationRepository).findById(any());
        verify(this.locationRepository).updateLocationById(any(), any(), any());
    }
}

