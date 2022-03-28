package ca.ghostteam.springulart.controller.reservation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ghostteam.springulart.dto.ReservationDTO;
import ca.ghostteam.springulart.dto.UserDTO;
import ca.ghostteam.springulart.model.AddressModel;
import ca.ghostteam.springulart.model.HaircutModel;
import ca.ghostteam.springulart.model.LocationModel;
import ca.ghostteam.springulart.model.ReservationModel;
import ca.ghostteam.springulart.model.ReservationTimeModel;
import ca.ghostteam.springulart.dao.ReservationDao;
import ca.ghostteam.springulart.dao.impl.InMemoryReservationImpl;
import ca.ghostteam.springulart.service.ReservationService;
import ca.ghostteam.springulart.service.impl.ReservationServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ReservationManagementController.class})
@ExtendWith(SpringExtension.class)
class ReservationManagementControllerTest {
    @Autowired
    private ReservationManagementController reservationManagementController;

    @MockBean
    private ReservationService reservationService;

    @Test
    void testCreateReservation() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of ReservationManagementController.
        //   Add a package-visible constructor or a factory method for testing which
        //   (ideally) takes no arguments, and does not throw, return null or return
        //   a subtype.
        //   See https://diff.blue/R008

        ReservationManagementController reservationManagementController = new ReservationManagementController(
                new ReservationServiceImpl(new InMemoryReservationImpl()));

        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setZip("21654");

        UserDTO userDTO = new UserDTO();
        userDTO.setAddress(addressModel);
        userDTO.setCreated(LocalDate.ofEpochDay(1L));
        userDTO.setDeleted(true);
        userDTO.setDob(LocalDate.ofEpochDay(1L));
        userDTO.setEmail("jane.doe@example.org");
        userDTO.setFname("Fname");
        userDTO.setId(1);
        userDTO.setImageURL("https://example.org/example");
        userDTO.setLname("Lname");
        userDTO.setPhone("4105551212");
        userDTO.setRole("Role");
        userDTO.setUpdated(LocalDate.ofEpochDay(1L));

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setZip("21654");

        UserDTO userDTO1 = new UserDTO();
        userDTO1.setAddress(addressModel1);
        userDTO1.setCreated(LocalDate.ofEpochDay(1L));
        userDTO1.setDeleted(true);
        userDTO1.setDob(LocalDate.ofEpochDay(1L));
        userDTO1.setEmail("jane.doe@example.org");
        userDTO1.setFname("Fname");
        userDTO1.setId(1);
        userDTO1.setImageURL("https://example.org/example");
        userDTO1.setLname("Lname");
        userDTO1.setPhone("4105551212");
        userDTO1.setRole("Role");
        userDTO1.setUpdated(LocalDate.ofEpochDay(1L));

        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setTitle("Dr");

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setBarber(userDTO);
        reservationDTO.setClient(userDTO1);
        reservationDTO.setHaircut(haircutModel);
        reservationDTO.setId("42");
        LocationModel locationModel = new LocationModel(10.0d, 10.0d);

        reservationDTO.setLocation(locationModel);
        reservationDTO.setReservationDate(LocalDate.ofEpochDay(1L));
        ReservationTimeModel reservationTimeModel = new ReservationTimeModel(1, 1);

        reservationDTO.setReservationTime(reservationTimeModel);
        reservationDTO.setStatus("Status");
        ReservationDTO actualCreateReservationResult = reservationManagementController.createReservation(reservationDTO);
        UserDTO barber = actualCreateReservationResult.getBarber();
        assertEquals(userDTO1, barber);
        assertEquals("Status", actualCreateReservationResult.getStatus());
        assertEquals(barber, actualCreateReservationResult.getClient());
        assertSame(locationModel, actualCreateReservationResult.getLocation());
        assertSame(haircutModel, actualCreateReservationResult.getHaircut());
        assertEquals("1970-01-02", actualCreateReservationResult.getReservationDate().toString());
        assertSame(reservationTimeModel, actualCreateReservationResult.getReservationTime());
    }

    @Test
    void testCreateReservation3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of ReservationManagementController.
        //   Add a package-visible constructor or a factory method for testing which
        //   (ideally) takes no arguments, and does not throw, return null or return
        //   a subtype.
        //   See https://diff.blue/R008

        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setZip("21654");

        UserDTO userDTO = new UserDTO();
        userDTO.setAddress(addressModel);
        userDTO.setCreated(LocalDate.ofEpochDay(1L));
        userDTO.setDeleted(true);
        userDTO.setDob(LocalDate.ofEpochDay(1L));
        userDTO.setEmail("jane.doe@example.org");
        userDTO.setFname("Fname");
        userDTO.setId(1);
        userDTO.setImageURL("https://example.org/example");
        userDTO.setLname("Lname");
        userDTO.setPhone("4105551212");
        userDTO.setRole("Role");
        userDTO.setUpdated(LocalDate.ofEpochDay(1L));

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setZip("21654");

        UserDTO userDTO1 = new UserDTO();
        userDTO1.setAddress(addressModel1);
        userDTO1.setCreated(LocalDate.ofEpochDay(1L));
        userDTO1.setDeleted(true);
        userDTO1.setDob(LocalDate.ofEpochDay(1L));
        userDTO1.setEmail("jane.doe@example.org");
        userDTO1.setFname("Fname");
        userDTO1.setId(1);
        userDTO1.setImageURL("https://example.org/example");
        userDTO1.setLname("Lname");
        userDTO1.setPhone("4105551212");
        userDTO1.setRole("Role");
        userDTO1.setUpdated(LocalDate.ofEpochDay(1L));

        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setTitle("Dr");

        ReservationModel reservationModel = new ReservationModel();
        reservationModel.setBarber(userDTO);
        reservationModel.setClient(userDTO1);
        reservationModel.setHaircut(haircutModel);
        reservationModel.setId("42");
        reservationModel.setLocation(new LocationModel(10.0d, 10.0d));
        reservationModel.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationModel.setReservationTime(new ReservationTimeModel(1, 1));
        reservationModel.setStatus("Status");
        ReservationDao reservationDao = mock(ReservationDao.class);
        when(reservationDao.save((ReservationModel) any())).thenReturn(Optional.of(reservationModel));
        ReservationManagementController reservationManagementController = new ReservationManagementController(
                new ReservationServiceImpl(reservationDao));

        AddressModel addressModel2 = new AddressModel();
        addressModel2.setApartement("Apartement");
        addressModel2.setCity("Oxford");
        addressModel2.setState("MD");
        addressModel2.setStreet("Street");
        addressModel2.setZip("21654");

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setAddress(addressModel2);
        userDTO2.setCreated(LocalDate.ofEpochDay(1L));
        userDTO2.setDeleted(true);
        userDTO2.setDob(LocalDate.ofEpochDay(1L));
        userDTO2.setEmail("jane.doe@example.org");
        userDTO2.setFname("Fname");
        userDTO2.setId(1);
        userDTO2.setImageURL("https://example.org/example");
        userDTO2.setLname("Lname");
        userDTO2.setPhone("4105551212");
        userDTO2.setRole("Role");
        userDTO2.setUpdated(LocalDate.ofEpochDay(1L));

        AddressModel addressModel3 = new AddressModel();
        addressModel3.setApartement("Apartement");
        addressModel3.setCity("Oxford");
        addressModel3.setState("MD");
        addressModel3.setStreet("Street");
        addressModel3.setZip("21654");

        UserDTO userDTO3 = new UserDTO();
        userDTO3.setAddress(addressModel3);
        userDTO3.setCreated(LocalDate.ofEpochDay(1L));
        userDTO3.setDeleted(true);
        userDTO3.setDob(LocalDate.ofEpochDay(1L));
        userDTO3.setEmail("jane.doe@example.org");
        userDTO3.setFname("Fname");
        userDTO3.setId(1);
        userDTO3.setImageURL("https://example.org/example");
        userDTO3.setLname("Lname");
        userDTO3.setPhone("4105551212");
        userDTO3.setRole("Role");
        userDTO3.setUpdated(LocalDate.ofEpochDay(1L));

        HaircutModel haircutModel1 = new HaircutModel();
        haircutModel1.setDescription("The characteristics of someone or something");
        haircutModel1.setEstimatedTime("Estimated Time");
        haircutModel1.setId("42");
        haircutModel1.setImageURL("https://example.org/example");
        haircutModel1.setPrice(1);
        haircutModel1.setTitle("Dr");

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setBarber(userDTO2);
        reservationDTO.setClient(userDTO3);
        reservationDTO.setHaircut(haircutModel1);
        reservationDTO.setId("42");
        LocationModel locationModel = new LocationModel(10.0d, 10.0d);

        reservationDTO.setLocation(locationModel);
        reservationDTO.setReservationDate(LocalDate.ofEpochDay(1L));
        ReservationTimeModel reservationTimeModel = new ReservationTimeModel(1, 1);

        reservationDTO.setReservationTime(reservationTimeModel);
        reservationDTO.setStatus("Status");
        ReservationDTO actualCreateReservationResult = reservationManagementController.createReservation(reservationDTO);
        assertEquals(userDTO2, actualCreateReservationResult.getBarber());
        assertEquals("Status", actualCreateReservationResult.getStatus());
        assertEquals(userDTO2, actualCreateReservationResult.getClient());
        assertEquals("42", actualCreateReservationResult.getId());
        assertEquals(locationModel, actualCreateReservationResult.getLocation());
        assertEquals(haircutModel1, actualCreateReservationResult.getHaircut());
        assertEquals("1970-01-02", actualCreateReservationResult.getReservationDate().toString());
        assertEquals(reservationTimeModel, actualCreateReservationResult.getReservationTime());
        verify(reservationDao).save((ReservationModel) any());
    }

    @Test
    void testCreateReservation4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of ReservationManagementController.
        //   Add a package-visible constructor or a factory method for testing which
        //   (ideally) takes no arguments, and does not throw, return null or return
        //   a subtype.
        //   See https://diff.blue/R008

        ReservationDao reservationDao = mock(ReservationDao.class);
        when(reservationDao.save((ReservationModel) any())).thenReturn(Optional.empty());
        ReservationManagementController reservationManagementController = new ReservationManagementController(
                new ReservationServiceImpl(reservationDao));

        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setZip("21654");

        UserDTO userDTO = new UserDTO();
        userDTO.setAddress(addressModel);
        userDTO.setCreated(LocalDate.ofEpochDay(1L));
        userDTO.setDeleted(true);
        userDTO.setDob(LocalDate.ofEpochDay(1L));
        userDTO.setEmail("jane.doe@example.org");
        userDTO.setFname("Fname");
        userDTO.setId(1);
        userDTO.setImageURL("https://example.org/example");
        userDTO.setLname("Lname");
        userDTO.setPhone("4105551212");
        userDTO.setRole("Role");
        userDTO.setUpdated(LocalDate.ofEpochDay(1L));

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setZip("21654");

        UserDTO userDTO1 = new UserDTO();
        userDTO1.setAddress(addressModel1);
        userDTO1.setCreated(LocalDate.ofEpochDay(1L));
        userDTO1.setDeleted(true);
        userDTO1.setDob(LocalDate.ofEpochDay(1L));
        userDTO1.setEmail("jane.doe@example.org");
        userDTO1.setFname("Fname");
        userDTO1.setId(1);
        userDTO1.setImageURL("https://example.org/example");
        userDTO1.setLname("Lname");
        userDTO1.setPhone("4105551212");
        userDTO1.setRole("Role");
        userDTO1.setUpdated(LocalDate.ofEpochDay(1L));

        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setTitle("Dr");

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setBarber(userDTO);
        reservationDTO.setClient(userDTO1);
        reservationDTO.setHaircut(haircutModel);
        reservationDTO.setId("42");
        reservationDTO.setLocation(new LocationModel(10.0d, 10.0d));
        reservationDTO.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationDTO.setReservationTime(new ReservationTimeModel(1, 1));
        reservationDTO.setStatus("Status");
        assertThrows(IllegalStateException.class, () -> reservationManagementController.createReservation(reservationDTO));
        verify(reservationDao).save((ReservationModel) any());
    }

    @Test
    void testUpdateReservation2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of ReservationManagementController.
        //   Add a package-visible constructor or a factory method for testing which
        //   (ideally) takes no arguments, and does not throw, return null or return
        //   a subtype.
        //   See https://diff.blue/R008

        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setZip("21654");

        UserDTO userDTO = new UserDTO();
        userDTO.setAddress(addressModel);
        userDTO.setCreated(LocalDate.ofEpochDay(1L));
        userDTO.setDeleted(true);
        userDTO.setDob(LocalDate.ofEpochDay(1L));
        userDTO.setEmail("jane.doe@example.org");
        userDTO.setFname("Fname");
        userDTO.setId(1);
        userDTO.setImageURL("https://example.org/example");
        userDTO.setLname("Lname");
        userDTO.setPhone("4105551212");
        userDTO.setRole("Role");
        userDTO.setUpdated(LocalDate.ofEpochDay(1L));

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setZip("21654");

        UserDTO userDTO1 = new UserDTO();
        userDTO1.setAddress(addressModel1);
        userDTO1.setCreated(LocalDate.ofEpochDay(1L));
        userDTO1.setDeleted(true);
        userDTO1.setDob(LocalDate.ofEpochDay(1L));
        userDTO1.setEmail("jane.doe@example.org");
        userDTO1.setFname("Fname");
        userDTO1.setId(1);
        userDTO1.setImageURL("https://example.org/example");
        userDTO1.setLname("Lname");
        userDTO1.setPhone("4105551212");
        userDTO1.setRole("Role");
        userDTO1.setUpdated(LocalDate.ofEpochDay(1L));

        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setTitle("Dr");

        ReservationModel reservationModel = new ReservationModel();
        reservationModel.setBarber(userDTO);
        reservationModel.setClient(userDTO1);
        reservationModel.setHaircut(haircutModel);
        reservationModel.setId("42");
        reservationModel.setLocation(new LocationModel(10.0d, 10.0d));
        reservationModel.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationModel.setReservationTime(new ReservationTimeModel(1, 1));
        reservationModel.setStatus("Status");
        InMemoryReservationImpl inMemoryReservationImpl = mock(InMemoryReservationImpl.class);
        when(inMemoryReservationImpl.update((String) any(), (ReservationModel) any()))
                .thenReturn(Optional.of(reservationModel));
        ReservationManagementController reservationManagementController = new ReservationManagementController(
                new ReservationServiceImpl(inMemoryReservationImpl));

        AddressModel addressModel2 = new AddressModel();
        addressModel2.setApartement("Apartement");
        addressModel2.setCity("Oxford");
        addressModel2.setState("MD");
        addressModel2.setStreet("Street");
        addressModel2.setZip("21654");

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setAddress(addressModel2);
        userDTO2.setCreated(LocalDate.ofEpochDay(1L));
        userDTO2.setDeleted(true);
        userDTO2.setDob(LocalDate.ofEpochDay(1L));
        userDTO2.setEmail("jane.doe@example.org");
        userDTO2.setFname("Fname");
        userDTO2.setId(1);
        userDTO2.setImageURL("https://example.org/example");
        userDTO2.setLname("Lname");
        userDTO2.setPhone("4105551212");
        userDTO2.setRole("Role");
        userDTO2.setUpdated(LocalDate.ofEpochDay(1L));

        AddressModel addressModel3 = new AddressModel();
        addressModel3.setApartement("Apartement");
        addressModel3.setCity("Oxford");
        addressModel3.setState("MD");
        addressModel3.setStreet("Street");
        addressModel3.setZip("21654");

        UserDTO userDTO3 = new UserDTO();
        userDTO3.setAddress(addressModel3);
        userDTO3.setCreated(LocalDate.ofEpochDay(1L));
        userDTO3.setDeleted(true);
        userDTO3.setDob(LocalDate.ofEpochDay(1L));
        userDTO3.setEmail("jane.doe@example.org");
        userDTO3.setFname("Fname");
        userDTO3.setId(1);
        userDTO3.setImageURL("https://example.org/example");
        userDTO3.setLname("Lname");
        userDTO3.setPhone("4105551212");
        userDTO3.setRole("Role");
        userDTO3.setUpdated(LocalDate.ofEpochDay(1L));

        HaircutModel haircutModel1 = new HaircutModel();
        haircutModel1.setDescription("The characteristics of someone or something");
        haircutModel1.setEstimatedTime("Estimated Time");
        haircutModel1.setId("42");
        haircutModel1.setImageURL("https://example.org/example");
        haircutModel1.setPrice(1);
        haircutModel1.setTitle("Dr");

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setBarber(userDTO2);
        reservationDTO.setClient(userDTO3);
        reservationDTO.setHaircut(haircutModel1);
        reservationDTO.setId("42");
        LocationModel locationModel = new LocationModel(10.0d, 10.0d);

        reservationDTO.setLocation(locationModel);
        reservationDTO.setReservationDate(LocalDate.ofEpochDay(1L));
        ReservationTimeModel reservationTimeModel = new ReservationTimeModel(1, 1);

        reservationDTO.setReservationTime(reservationTimeModel);
        reservationDTO.setStatus("Status");
        ReservationDTO actualUpdateReservationResult = reservationManagementController.updateReservation("42",
                reservationDTO);
        assertEquals(userDTO2, actualUpdateReservationResult.getBarber());
        assertEquals("Status", actualUpdateReservationResult.getStatus());
        assertEquals(userDTO2, actualUpdateReservationResult.getClient());
        assertEquals("42", actualUpdateReservationResult.getId());
        assertEquals(locationModel, actualUpdateReservationResult.getLocation());
        assertEquals(haircutModel1, actualUpdateReservationResult.getHaircut());
        assertEquals("1970-01-02", actualUpdateReservationResult.getReservationDate().toString());
        assertEquals(reservationTimeModel, actualUpdateReservationResult.getReservationTime());
        verify(inMemoryReservationImpl).update((String) any(), (ReservationModel) any());
    }

    @Test
    void testUpdateReservation3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of ReservationManagementController.
        //   Add a package-visible constructor or a factory method for testing which
        //   (ideally) takes no arguments, and does not throw, return null or return
        //   a subtype.
        //   See https://diff.blue/R008

        InMemoryReservationImpl inMemoryReservationImpl = mock(InMemoryReservationImpl.class);
        when(inMemoryReservationImpl.update((String) any(), (ReservationModel) any())).thenReturn(Optional.empty());
        ReservationManagementController reservationManagementController = new ReservationManagementController(
                new ReservationServiceImpl(inMemoryReservationImpl));

        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setZip("21654");

        UserDTO userDTO = new UserDTO();
        userDTO.setAddress(addressModel);
        userDTO.setCreated(LocalDate.ofEpochDay(1L));
        userDTO.setDeleted(true);
        userDTO.setDob(LocalDate.ofEpochDay(1L));
        userDTO.setEmail("jane.doe@example.org");
        userDTO.setFname("Fname");
        userDTO.setId(1);
        userDTO.setImageURL("https://example.org/example");
        userDTO.setLname("Lname");
        userDTO.setPhone("4105551212");
        userDTO.setRole("Role");
        userDTO.setUpdated(LocalDate.ofEpochDay(1L));

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setZip("21654");

        UserDTO userDTO1 = new UserDTO();
        userDTO1.setAddress(addressModel1);
        userDTO1.setCreated(LocalDate.ofEpochDay(1L));
        userDTO1.setDeleted(true);
        userDTO1.setDob(LocalDate.ofEpochDay(1L));
        userDTO1.setEmail("jane.doe@example.org");
        userDTO1.setFname("Fname");
        userDTO1.setId(1);
        userDTO1.setImageURL("https://example.org/example");
        userDTO1.setLname("Lname");
        userDTO1.setPhone("4105551212");
        userDTO1.setRole("Role");
        userDTO1.setUpdated(LocalDate.ofEpochDay(1L));

        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setTitle("Dr");

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setBarber(userDTO);
        reservationDTO.setClient(userDTO1);
        reservationDTO.setHaircut(haircutModel);
        reservationDTO.setId("42");
        reservationDTO.setLocation(new LocationModel(10.0d, 10.0d));
        reservationDTO.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationDTO.setReservationTime(new ReservationTimeModel(1, 1));
        reservationDTO.setStatus("Status");
        assertThrows(IllegalStateException.class,
                () -> reservationManagementController.updateReservation("42", reservationDTO));
        verify(inMemoryReservationImpl).update((String) any(), (ReservationModel) any());
    }

    @Test
    void testDeleteReservation() throws Exception {
        doNothing().when(this.reservationService).deleteById((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/management/api/v1/reservations/{id}",
                "42");
        MockMvcBuilders.standaloneSetup(this.reservationManagementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteReservation2() throws Exception {
        doNothing().when(this.reservationService).deleteById((String) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/management/api/v1/reservations/{id}",
                "42");
        deleteResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.reservationManagementController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetAllReservations() throws Exception {
        when(this.reservationService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/management/api/v1/reservations");
        MockMvcBuilders.standaloneSetup(this.reservationManagementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllReservations2() throws Exception {
        when(this.reservationService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/management/api/v1/reservations");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.reservationManagementController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetReservationById() throws Exception {
        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setZip("21654");

        UserDTO userDTO = new UserDTO();
        userDTO.setAddress(addressModel);
        userDTO.setCreated(LocalDate.ofEpochDay(1L));
        userDTO.setDeleted(true);
        userDTO.setDob(LocalDate.ofEpochDay(1L));
        userDTO.setEmail("jane.doe@example.org");
        userDTO.setFname("Fname");
        userDTO.setId(1);
        userDTO.setImageURL("https://example.org/example");
        userDTO.setLname("Lname");
        userDTO.setPhone("4105551212");
        userDTO.setRole("Role");
        userDTO.setUpdated(LocalDate.ofEpochDay(1L));

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setZip("21654");

        UserDTO userDTO1 = new UserDTO();
        userDTO1.setAddress(addressModel1);
        userDTO1.setCreated(LocalDate.ofEpochDay(1L));
        userDTO1.setDeleted(true);
        userDTO1.setDob(LocalDate.ofEpochDay(1L));
        userDTO1.setEmail("jane.doe@example.org");
        userDTO1.setFname("Fname");
        userDTO1.setId(1);
        userDTO1.setImageURL("https://example.org/example");
        userDTO1.setLname("Lname");
        userDTO1.setPhone("4105551212");
        userDTO1.setRole("Role");
        userDTO1.setUpdated(LocalDate.ofEpochDay(1L));

        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setDescription("The characteristics of someone or something");
        haircutModel.setEstimatedTime("Estimated Time");
        haircutModel.setId("42");
        haircutModel.setImageURL("https://example.org/example");
        haircutModel.setPrice(1);
        haircutModel.setTitle("Dr");

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setBarber(userDTO);
        reservationDTO.setClient(userDTO1);
        reservationDTO.setHaircut(haircutModel);
        reservationDTO.setId("42");
        reservationDTO.setLocation(new LocationModel(10.0d, 10.0d));
        reservationDTO.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationDTO.setReservationTime(new ReservationTimeModel(1, 1));
        reservationDTO.setStatus("Status");
        Optional<ReservationDTO> ofResult = Optional.of(reservationDTO);
        when(this.reservationService.findById((String) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/management/api/v1/reservations/{id}",
                "42");
        MockMvcBuilders.standaloneSetup(this.reservationManagementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":\"42\",\"reservationDate\":[1970,1,2],\"reservationTime\":{\"hours\":1,\"minutes\":1},\"haircut\":{\"id\":\"42"
                                        + "\",\"imageURL\":\"https://example.org/example\",\"price\":1,\"title\":\"Dr\",\"estimatedTime\":\"Estimated"
                                        + " Time\",\"description\":\"The characteristics of someone or something\"},\"status\":\"Status\",\"client\":{\"id\""
                                        + ":1,\"fname\":\"Fname\",\"lname\":\"Lname\",\"email\":\"jane.doe@example.org\",\"imageURL\":\"https://example.org"
                                        + "/example\",\"phone\":\"4105551212\",\"role\":\"Role\",\"dob\":[1970,1,2],\"address\":{\"street\":\"Street\",\"apartement"
                                        + "\":\"Apartement\",\"zip\":\"21654\",\"city\":\"Oxford\",\"state\":\"MD\"},\"created\":[1970,1,2],\"updated\":[1970,1,2]"
                                        + ",\"deleted\":true},\"barber\":{\"id\":1,\"fname\":\"Fname\",\"lname\":\"Lname\",\"email\":\"jane.doe@example.org\","
                                        + "\"imageURL\":\"https://example.org/example\",\"phone\":\"4105551212\",\"role\":\"Role\",\"dob\":[1970,1,2],\"address"
                                        + "\":{\"street\":\"Street\",\"apartement\":\"Apartement\",\"zip\":\"21654\",\"city\":\"Oxford\",\"state\":\"MD\"},\"created\""
                                        + ":[1970,1,2],\"updated\":[1970,1,2],\"deleted\":true},\"location\":{\"latitude\":10.0,\"longitude\":10.0}}"));
    }
}

