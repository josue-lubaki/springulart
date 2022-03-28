package ca.ghostteam.springulart.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
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

import java.time.LocalDate;

import java.util.ArrayList;
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
    private ReservationDao reservationDao;

    @Autowired
    private ReservationServiceImpl reservationServiceImpl;

    @Test
    void testFindAll() {
        when(this.reservationDao.findAll()).thenReturn(new ArrayList<>());
        assertTrue(this.reservationServiceImpl.findAll().isEmpty());
        verify(this.reservationDao).findAll();
    }

    @Test
    void testFindAll2() {
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

        ArrayList<ReservationModel> reservationModelList = new ArrayList<>();
        reservationModelList.add(reservationModel);
        when(this.reservationDao.findAll()).thenReturn(reservationModelList);
        assertEquals(1, this.reservationServiceImpl.findAll().size());
        verify(this.reservationDao).findAll();
    }

    @Test
    void testFindAll3() {
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

        ReservationModel reservationModel1 = new ReservationModel();
        reservationModel1.setBarber(userDTO2);
        reservationModel1.setClient(userDTO3);
        reservationModel1.setHaircut(haircutModel1);
        reservationModel1.setId("42");
        reservationModel1.setLocation(new LocationModel(10.0d, 10.0d));
        reservationModel1.setReservationDate(LocalDate.ofEpochDay(1L));
        reservationModel1.setReservationTime(new ReservationTimeModel(1, 1));
        reservationModel1.setStatus("Status");

        ArrayList<ReservationModel> reservationModelList = new ArrayList<>();
        reservationModelList.add(reservationModel1);
        reservationModelList.add(reservationModel);
        when(this.reservationDao.findAll()).thenReturn(reservationModelList);
        assertEquals(2, this.reservationServiceImpl.findAll().size());
        verify(this.reservationDao).findAll();
    }

    @Test
    void testFindById() {
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
        LocationModel locationModel = new LocationModel(10.0d, 10.0d);

        reservationModel.setLocation(locationModel);
        reservationModel.setReservationDate(LocalDate.ofEpochDay(1L));
        ReservationTimeModel reservationTimeModel = new ReservationTimeModel(1, 1);

        reservationModel.setReservationTime(reservationTimeModel);
        reservationModel.setStatus("Status");
        Optional<ReservationModel> ofResult = Optional.of(reservationModel);
        when(this.reservationDao.findById((String) any())).thenReturn(ofResult);
        Optional<ReservationDTO> actualFindByIdResult = this.reservationServiceImpl.findById("42");
        assertTrue(actualFindByIdResult.isPresent());
        ReservationDTO getResult = actualFindByIdResult.get();
        UserDTO barber = getResult.getBarber();
        assertEquals(userDTO1, barber);
        assertEquals("Status", getResult.getStatus());
        assertEquals(barber, getResult.getClient());
        assertEquals("42", getResult.getId());
        assertSame(locationModel, getResult.getLocation());
        assertSame(haircutModel, getResult.getHaircut());
        assertEquals("1970-01-02", getResult.getReservationDate().toString());
        assertSame(reservationTimeModel, getResult.getReservationTime());
        verify(this.reservationDao).findById((String) any());
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
        Optional<ReservationModel> ofResult = Optional.of(reservationModel);
        when(this.reservationDao.save((ReservationModel) any())).thenReturn(ofResult);

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
        Optional<ReservationDTO> actualSaveResult = this.reservationServiceImpl.save(reservationDTO);
        assertTrue(actualSaveResult.isPresent());
        ReservationDTO getResult = actualSaveResult.get();
        assertEquals(userDTO2, getResult.getBarber());
        assertEquals("Status", getResult.getStatus());
        assertEquals(userDTO2, getResult.getClient());
        assertEquals("42", getResult.getId());
        assertEquals(locationModel, getResult.getLocation());
        assertEquals(haircutModel1, getResult.getHaircut());
        assertEquals("1970-01-02", getResult.getReservationDate().toString());
        assertEquals(reservationTimeModel, getResult.getReservationTime());
        verify(this.reservationDao).save((ReservationModel) any());
    }

    @Test
    void testUpdate() {
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
        Optional<ReservationModel> ofResult = Optional.of(reservationModel);
        when(this.reservationDao.update((String) any(), (ReservationModel) any())).thenReturn(ofResult);

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
        Optional<ReservationDTO> actualUpdateResult = this.reservationServiceImpl.update("42", reservationDTO);
        assertTrue(actualUpdateResult.isPresent());
        ReservationDTO getResult = actualUpdateResult.get();
        assertEquals(userDTO2, getResult.getBarber());
        assertEquals("Status", getResult.getStatus());
        assertEquals(userDTO2, getResult.getClient());
        assertEquals("42", getResult.getId());
        assertEquals(locationModel, getResult.getLocation());
        assertEquals(haircutModel1, getResult.getHaircut());
        assertEquals("1970-01-02", getResult.getReservationDate().toString());
        assertEquals(reservationTimeModel, getResult.getReservationTime());
        verify(this.reservationDao).update((String) any(), (ReservationModel) any());
    }

    @Test
    void testDeleteById() {
        doNothing().when(this.reservationDao).deleteById((String) any());
        this.reservationServiceImpl.deleteById("42");
        verify(this.reservationDao).deleteById((String) any());
    }
}

