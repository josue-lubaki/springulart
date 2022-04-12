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
import ca.ghostteam.springulart.repository.ReservationRepository;
import ca.ghostteam.springulart.service.haircut.HaircutService;
import ca.ghostteam.springulart.service.location.LocationService;
import ca.ghostteam.springulart.service.mail.MailService;
import ca.ghostteam.springulart.service.reservationtime.ReservationTimeService;
import ca.ghostteam.springulart.service.user.UserService;
import ca.ghostteam.springulart.tools.UtilsReservation;

import java.time.LocalDate;

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
}

