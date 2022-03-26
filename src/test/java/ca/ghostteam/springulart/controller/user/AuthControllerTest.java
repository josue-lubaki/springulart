package ca.ghostteam.springulart.controller.user;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import ca.ghostteam.springulart.bean.JwtConfig;
import ca.ghostteam.springulart.dto.AuthDTO;
import ca.ghostteam.springulart.dto.SignupDTO;
import ca.ghostteam.springulart.dto.UserDTO;
import ca.ghostteam.springulart.dto.UserDetailsDTO;
import ca.ghostteam.springulart.model.AddressModel;
import ca.ghostteam.springulart.model.CredentialModel;
import ca.ghostteam.springulart.model.UserModel;
import ca.ghostteam.springulart.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AuthController.class, JwtConfig.class})
@ExtendWith(SpringExtension.class)
class AuthControllerTest {
    @Autowired
    private AuthController authController;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtConfig jwtConfig;

    @MockBean
    private UserService userService;

    @Test
    void testAuthenticateUser() throws Exception {
        when(this.userService.loadUserByUsername((String) any())).thenReturn(new UserDetailsDTO());

        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setCreated(LocalDate.ofEpochDay(1L));
        credentialModel.setGrantedAuthority("JaneDoe");
        credentialModel.setId(1);
        credentialModel.setPassword("iloveyou");
        credentialModel.setUpdated(LocalDate.ofEpochDay(1L));
        credentialModel.setUsername("janedoe");

        AddressModel addressModel = new AddressModel();
        addressModel.setApartement("?");
        addressModel.setCity("Oxford");
        addressModel.setState("MD");
        addressModel.setStreet("?");
        addressModel.setZip("21654");

        UserModel userModel = new UserModel();
        userModel.setAddress(addressModel);
        userModel.setCreated(LocalDate.ofEpochDay(1L));
        userModel.setDeleted(true);
        userModel.setDob(LocalDate.ofEpochDay(1L));
        userModel.setEmail("jane.doe@example.org");
        userModel.setFname("?");
        userModel.setId(1);
        userModel.setImageURL("https://example.org/example");
        userModel.setLname("?");
        userModel.setPassword("iloveyou");
        userModel.setPhone("4105551212");
        userModel.setRole("?");
        userModel.setUpdated(LocalDate.ofEpochDay(1L));

        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setCredentials(credentialModel);
        userDetailsDTO.setToken("ABC123");
        userDetailsDTO.setUserModel(userModel);
        TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(userDetailsDTO,
                "Credentials");

        when(this.authenticationManager.authenticate((org.springframework.security.core.Authentication) any()))
                .thenReturn(testingAuthenticationToken);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/auth/login");
        postResult.accept("https://example.org/example");

        AuthDTO authDTO = new AuthDTO();
        authDTO.setPassword("iloveyou");
        authDTO.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(authDTO);
        MockHttpServletRequestBuilder requestBuilder = postResult.contentType(MediaType.APPLICATION_JSON).content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.authController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

    @Test
    void testRegisterUser() throws Exception {
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
        Optional<UserDTO> ofResult = Optional.of(userDTO);
        when(this.userService.findUserByEmail((String) any())).thenReturn(false);
        when(this.userService.saveUser((SignupDTO) any())).thenReturn(ofResult);

        AddressModel addressModel1 = new AddressModel();
        addressModel1.setApartement("Apartement");
        addressModel1.setCity("Oxford");
        addressModel1.setState("MD");
        addressModel1.setStreet("Street");
        addressModel1.setZip("21654");

        SignupDTO signupDTO = new SignupDTO();
        signupDTO.setAddress(addressModel1);
        signupDTO.setDob("Dob");
        signupDTO.setEmail("jane.doe@example.org");
        signupDTO.setFname("Fname");
        signupDTO.setImageURL("https://example.org/example");
        signupDTO.setLname("Lname");
        signupDTO.setPassword("iloveyou");
        signupDTO.setPhone("4105551212");
        signupDTO.setRole("Role");
        String content = (new ObjectMapper()).writeValueAsString(signupDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"fname\":\"Fname\",\"lname\":\"Lname\",\"email\":\"jane.doe@example.org\",\"imageURL\":\"https://example.org"
                                        + "/example\",\"phone\":\"4105551212\",\"role\":\"Role\",\"dob\":[1970,1,2],\"address\":{\"street\":\"Street\",\"apartement"
                                        + "\":\"Apartement\",\"zip\":\"21654\",\"city\":\"Oxford\",\"state\":\"MD\"},\"created\":[1970,1,2],\"updated\":[1970,1,2]"
                                        + ",\"deleted\":true}"));
    }
}

