package ca.ghostteam.springulart.dao.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import ca.ghostteam.springulart.dto.UserDTO;
import ca.ghostteam.springulart.model.AddressModel;
import ca.ghostteam.springulart.model.HaircutModel;
import ca.ghostteam.springulart.model.LocationModel;
import ca.ghostteam.springulart.model.ReservationModel;
import ca.ghostteam.springulart.model.ReservationTimeModel;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {InMemoryReservationImpl.class})
@ExtendWith(SpringExtension.class)
class InMemoryReservationImplTest {
    @Autowired
    private InMemoryReservationImpl inMemoryReservationImpl;

    @Test
    void testFindById() {
        assertFalse(this.inMemoryReservationImpl.findById("42").isPresent());
    }

    @Test
    void testSave() {
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
        assertTrue(this.inMemoryReservationImpl.save(reservationModel).isPresent());
    }

    @Test
    void testSave2() {
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
        ReservationModel reservationModel = mock(ReservationModel.class);
        doNothing().when(reservationModel).setBarber((UserDTO) any());
        doNothing().when(reservationModel).setClient((UserDTO) any());
        doNothing().when(reservationModel).setHaircut((HaircutModel) any());
        doNothing().when(reservationModel).setId((String) any());
        doNothing().when(reservationModel).setLocation((LocationModel) any());
        doNothing().when(reservationModel).setReservationDate((LocalDate) any());
        doNothing().when(reservationModel).setReservationTime((ReservationTimeModel) any());
        doNothing().when(reservationModel).setStatus((String) any());
        reservationModel.setBarber(userDTO);
        reservationModel.setClient(userDTO1);
        reservationModel.setHaircut(haircutModel);
        reservationModel.setId("42");
        reservationModel.setLocation(new LocationModel(10.0d, 10.0d));
        reservationModel.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationModel.setReservationTime(new ReservationTimeModel(1, 1));
        reservationModel.setStatus("Status");
        assertTrue(this.inMemoryReservationImpl.save(reservationModel).isPresent());
        verify(reservationModel).setBarber((UserDTO) any());
        verify(reservationModel).setClient((UserDTO) any());
        verify(reservationModel).setHaircut((HaircutModel) any());
        verify(reservationModel, atLeast(1)).setId((String) any());
        verify(reservationModel).setLocation((LocationModel) any());
        verify(reservationModel).setReservationDate((LocalDate) any());
        verify(reservationModel).setReservationTime((ReservationTimeModel) any());
        verify(reservationModel).setStatus((String) any());
    }

    @Test
    void testConstructor() {
        assertTrue((new InMemoryReservationImpl()).findAll().isEmpty());
    }
}

