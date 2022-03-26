package ca.ghostteam.springulart.controller.user;

import static org.mockito.Mockito.when;

import ca.ghostteam.springulart.dto.SignupDTO;
import ca.ghostteam.springulart.model.AddressModel;
import ca.ghostteam.springulart.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserManagementController.class})
@ExtendWith(SpringExtension.class)
class UserManagementControllerTest {
    @Autowired
    private UserManagementController userManagementController;

    @MockBean
    private UserService userService;

    @Test
    void testDeleteUser() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/management/api/v1/users/{userId}",
                123);
        MockMvcBuilders.standaloneSetup(this.userManagementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteUser2() throws Exception {
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/management/api/v1/users/{userId}",
                123);
        deleteResult.secure(true);
        MockMvcBuilders.standaloneSetup(this.userManagementController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetUser() throws Exception {
        when(this.userService.findAllUsers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/management/api/v1/users/{userId}",
                "Uri Vars", "Uri Vars");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userManagementController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testGetUsers() throws Exception {
        when(this.userService.findAllUsers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/management/api/v1/users");
        MockMvcBuilders.standaloneSetup(this.userManagementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetUsers2() throws Exception {
        when(this.userService.findAllUsers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/management/api/v1/users");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.userManagementController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testRegisterNewUser() throws Exception {
        when(this.userService.findAllUsers()).thenReturn(new ArrayList<>());

        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("Apartement");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("Street");
        addressModel.setZip("21654");

        SignupDTO signupDTO = new SignupDTO();
        signupDTO.setAddress(addressModel);
        signupDTO.setDob("Dob");
        signupDTO.setEmail("jane.doe@example.org");
        signupDTO.setFname("Fname");
        signupDTO.setImageURL("https://example.org/example");
        signupDTO.setLname("Lname");
        signupDTO.setPassword("iloveyou");
        signupDTO.setPhone("4105551212");
        signupDTO.setRole("Role");
        String content = (new ObjectMapper()).writeValueAsString(signupDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/management/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.userManagementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

