package ca.ghostteam.springulart.service.reservation.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
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
import ca.ghostteam.springulart.repository.ReservationRepository;
import ca.ghostteam.springulart.service.haircut.HaircutService;
import ca.ghostteam.springulart.service.location.LocationService;
import ca.ghostteam.springulart.service.mail.MailService;
import ca.ghostteam.springulart.service.reservationtime.ReservationTimeService;
import ca.ghostteam.springulart.service.user.UserService;
import ca.ghostteam.springulart.tools.UtilsReservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
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
    private MailService mailService;

    @MockBean
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationServiceImpl reservationServiceImpl;

    @MockBean
    private ReservationTimeService reservationTimeService;

    @MockBean
    private UserService userService;

    @MockBean
    private UtilsReservation utilsReservation;

    @Test
    void testUpdate() {
        when(this.reservationRepository.findById((Long) any()))
                .thenThrow(new IllegalStateException("Reservation with ID %s cannot found"));

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
        assertThrows(IllegalStateException.class, () -> this.reservationServiceImpl.update(123L, reservationDTO));
        verify(this.reservationRepository).findById((Long) any());
    }

    @Test
    void testDeleteReservationById() {
        when(this.utilsReservation.converterModelToDTO((ReservationModel) any()))
                .thenThrow(new IllegalStateException("foo"));

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
        Optional<ReservationModel> ofResult = Optional.of(reservationModel1);
        when(this.reservationRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalStateException.class, () -> this.reservationServiceImpl.deleteReservationById(123L));
        verify(this.utilsReservation).converterModelToDTO((ReservationModel) any());
        verify(this.reservationRepository).findById((Long) any());
    }
}

