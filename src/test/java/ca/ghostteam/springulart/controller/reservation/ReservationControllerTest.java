package ca.ghostteam.springulart.controller.reservation;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import ca.ghostteam.springulart.bean.JwtConfig;
import ca.ghostteam.springulart.dto.ReservationDTO;
import ca.ghostteam.springulart.dto.UserDTO;
import ca.ghostteam.springulart.model.AddressModel;
import ca.ghostteam.springulart.model.HaircutModel;
import ca.ghostteam.springulart.model.LocationModel;
import ca.ghostteam.springulart.model.ReservationTimeModel;
import ca.ghostteam.springulart.security.jwt.filter.JwtTokenVerifier;
import ca.ghostteam.springulart.service.ReservationService;
import ca.ghostteam.springulart.service.UserService;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

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

@ContextConfiguration(classes = {ReservationController.class, JwtConfig.class})
@ExtendWith(SpringExtension.class)
class ReservationControllerTest {
    @MockBean
    private HttpServletRequest httpServletRequest;

    @Autowired
    private JwtConfig jwtConfig;

    @MockBean
    private JwtTokenVerifier jwtTokenVerifier;

    @Autowired
    private ReservationController reservationController;

    @MockBean
    private ReservationService reservationService;

    @MockBean
    private UserService userService;

    @Test
    void testGetAllReservations() throws Exception {
        when(this.reservationService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/reservations");
        MockMvcBuilders.standaloneSetup(this.reservationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllReservations2() throws Exception {
        when(this.reservationService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/reservations");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.reservationController)
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/reservations/{id}", "42");
        MockMvcBuilders.standaloneSetup(this.reservationController)
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

